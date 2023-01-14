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

@WebServlet("/NewOrderController")
public class NewOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewOrderController() {
		super();
	}

	/**
	 * 注文画面の表示
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		//		”戻る”ボタンで戻ってきた場合codeオブジェクトから商品コードを取り出す
		int cartCode;
		String backAction = request.getParameter("back");
		if (backAction != null) {
			cartCode = (int) session.getAttribute("cartCode");
		} else {
			cartCode = Integer.parseInt(request.getParameter("cartCode"));	//	requestから注文コードを取得。
		}
		session.setAttribute("cartCode", cartCode);  //	商品コード情報をセッションスコープで保持
		//		codeをキーに選択した商品の名前、価格を取得
		String[] orderInf = SqlQuery.selectItemInf(cartCode);

		//		cartBeanに名前、価格をセット
		String name = orderInf[0];
		int price = Integer.parseInt(orderInf[1]);
		CartBean cartBean = new CartBean();
		cartBean.setItemCode(cartCode);
		cartBean.setName(name);
		cartBean.setPrice(price);

		session.setAttribute("cart", cartBean);  //	セッションにカートオブジェクトをセット
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/newOrder.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
