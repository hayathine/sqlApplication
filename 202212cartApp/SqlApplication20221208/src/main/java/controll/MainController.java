package controll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.CartBean;
import model.CartLogic;
import model.ItemBean;
import model.ItemListBean;
import model.SqlQuery;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CartBean cartBean = (CartBean)session.getAttribute("cart");
//		SqlQueryからItemListBeanをセット
		List<ItemBean> itemList = new ArrayList<ItemBean>();
//		SQLへ接続して商品一覧のSELECT文を実行。
//		ItemListBeanに商品情報をセット
		SqlQuery sqlQuery = new SqlQuery();
		ItemListBean itemListBean = new ItemListBean();
		itemListBean = sqlQuery.queryItemList();
//		この順序の意味は？
		String action = request.getParameter("action");
		if(action == null) {
			cartBean = new CartBean();
		}
//		requestスコープにitemListBean,cartBeanをセットアトリビュート
		request.setAttribute("itemList", itemListBean);
		request.setAttribute("cart", cartBean);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/order.jsp");
		rd.forward(request, response);
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html utf-8");
		HttpSession session = request.getSession(true);
		CartBean cart = (CartBean)session.getAttribute("cart");
		SqlQuery sqlQuery = new SqlQuery();
//		requestにdecisionが送られたら注文をインサートする
		String decision = request.getParameter("decision");
		if(decision != null) {
//			OREDERテーブルにcartをinsertする
			sqlQuery.insert(cart);
		}
		
//		requestから注文情報を取得。
		String code,num,name,price;
		code = request.getParameter("code");
		num = request.getParameter("num");
//		ORDERテーブルから注文した商品の名前と価格を取得しcartBeanにセット
		String[] nameAndPrice = sqlQuery.selectOrderItem(code);
		name = nameAndPrice[0];
		price = nameAndPrice[1];
		
//		CartLogicからcartBeanに注文情報をセット
		CartLogic cartLogic = new CartLogic(code,name,price,num);
		CartBean cartBean = cartLogic.execute();
		
		session.setAttribute("cart", cartBean);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/confirm.jsp");
		rd.forward(request, response);
		
		
//		商品登録
		
//		商品削除
		

		
//		カート内一覧表示
	}

}
