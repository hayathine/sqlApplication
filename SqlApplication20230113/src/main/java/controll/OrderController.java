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


import model.CartBean;
import model.CartLogic;
import model.OrderBean;
import model.SettingApp;
import model.SqlQuery;

@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OrderController() {
        super();
    }
    /**
	 * 注文確定ボタンが押された時、注文履歴テーブルへインサート
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderDate = CartLogic.getNowDateTime();  //	注文日時を取得
//		CartListオブジェクトをセッションから取り出す
		HttpSession session = request.getSession(true);
		List<CartBean> cartList = new ArrayList<CartBean>();
		cartList = (List<CartBean>)session.getAttribute("cartList");
		int price = CartLogic.calcTotalPrice(cartList);
		int taxInPrice = (int)Math.round(price * SettingApp.getTaxRate());  // 税込み代金を計算
		SqlQuery.insertOrder(orderDate, taxInPrice);	// 	注文テーブルへ注文明細、注文日、税込み代金をインサート
		int orderDetailId = SqlQuery.fetchOrderDetailId(orderDate);  // 注文日から注文明細番号を取得
		for (int i = 0; i < cartList.size(); i++) {
			SqlQuery.insertOrderDetail(orderDetailId, cartList.get(i).getItemCode(), cartList.get(i).getNum());  // 注文明細テーブルへID,商品コード、数量をインサート
		}
		cartList.clear(); // カート情報をクリア
		RequestDispatcher rd = request.getRequestDispatcher("/MenuController");
		rd.forward(request, response);
	}
	/**
	 * 注文履歴画面の表示。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 		キャンセルボタンが押された場合、キャンセルフラグを立てる。
		if(request.getParameter("cancel") != null) {
			String orderLogId = request.getParameter("cancel");
			SqlQuery.updateCancelFlag("ORDER_LOG", orderLogId);  // テーブル名と注文番号からキャンセルフラグを立てる
			SqlQuery.updateCancelFlag("ORDER_DETAIL", orderLogId);
		}

		List<OrderBean> orderList = SqlQuery.selectAllOrderLog();  // ORDERテーブルからデータを取得
		request.setAttribute("orderList", orderList);  //  リクエストにorderListをセット
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/order.jsp");
		rd.forward(request, response);
	}

}
