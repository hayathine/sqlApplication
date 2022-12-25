package controll;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartBean;
import model.CartLogic;
import model.SqlQuery;

@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OrderController() {
        super();
    }
    /**
     * CartBeanに商品の数量と代金をセットして注文画面を表示
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession(true);
			
	//		”戻る”ボタンで戻ってきた場合codeオブジェクトから商品コードを取り出す
		int orderCode;
		String backAction = request.getParameter("back");
		if(backAction != null) {
			orderCode = (int)session.getAttribute("orderCode");			
		}else {
	//		requestから注文コードを取得。
			orderCode = Integer.parseInt(request.getParameter("orderCode"));			
		}
	//		商品コード情報をセッションスコープで保持
		session.setAttribute("orderCode", orderCode);
			
	//		codeをキーに選択した商品の名前、価格を取得
		SqlQuery sqlQuery = new SqlQuery();
		String[] orderInf = sqlQuery.selectItemInf(orderCode);
		
	//		cartBeanに名前、価格をセット
		String name = orderInf[0];
		int price = Integer.parseInt(orderInf[1]);
		CartBean cartBean = new CartBean();
		cartBean.setCode(orderCode);
		cartBean.setName(name);
		cartBean.setPrice(price);
		
	//		セッションにカートオブジェクトをセット
		session.setAttribute("cart", cartBean);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/order.jsp");
		rd.forward(request, response);
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
