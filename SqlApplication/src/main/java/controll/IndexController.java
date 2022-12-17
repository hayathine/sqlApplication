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
@WebServlet("/IndexController")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public IndexController() {
        super();
    }

    /**
     * 商品一覧を表示
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		SQLへ接続して商品一覧のSELECT文を実行。
		List<ItemBean> itemList = new ArrayList<ItemBean>();
		SqlQuery sqlQuery = new SqlQuery();
//		SqlQueryでItemBeanをセットし、リストに格納
		itemList = sqlQuery.queryItemList();
//		ItemListBeanに商品情報をセット
		ItemListBean itemListBean = new ItemListBean();
		itemListBean.setItemList(itemList);

//		requestスコープにitemListBean,cartBeanをセットアトリビュート
		request.setAttribute("itemList", itemListBean);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		rd.forward(request, response);
	} 

	/**
	 * 選択された商品の注文ページを表示
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
//		”戻る”ボタンで戻ってきた場合codeオブジェクトから商品コードを取り出す
		int orderCode;
		if(session.getAttribute("code") != null) {
			orderCode = (int)session.getAttribute("code");			
		}else {
//		requestから商品コードを取得。
			orderCode = Integer.parseInt(request.getParameter("orderCode"));			
		}
//		商品コード情報をセッションスコープで保持
		session.setAttribute("code", orderCode);
		
//		codeをキーに選択した商品の名前と価格を取得
		SqlQuery sqlQuery = new SqlQuery();
		String[] orderInf = sqlQuery.selectItemInf(orderCode);
		String name = orderInf[0];
		int price = Integer.parseInt(orderInf[1]);
//		cartBeanに名前と価格をセット
		CartBean cartBean = new CartBean();
		cartBean.setCode(orderCode);
		cartBean.setName(name);
		cartBean.setPrice(price);
//		セッションにカートオブジェクトをセット
		session.setAttribute("cart", cartBean);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/order.jsp");
		rd.forward(request, response);
	}

}
