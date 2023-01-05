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

import model.CartBean;
import model.CartLogic;
import model.PurchasedLogBean;
import model.SqlQuery;

@WebServlet("/PurchasedLogController")
public class PurchasedLogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PurchasedLogController() {
        super();
    }
    /**
     * 注文履歴画面に移動して履歴を表示。
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SqlQuery sqlQuery = new SqlQuery();
//		注文日時を取得
		String purchasedDate = CartLogic.getNowDateTime();
		
//		CARTテーブルの商品コード、数量,日時を取得
		List<CartBean> cartList = new ArrayList<CartBean>();
		cartList = sqlQuery.queryCartList();
//		注文履歴テーブルへカート内にあった商品をインサート
		for(int i = 0 ; i<cartList.size();i++) {
		sqlQuery.insertPurchasedLog(cartList.get(i).getItemCode(), cartList.get(i).getNum(), purchasedDate);
		}
//		CARTテーブル内の情報を全て消去
		sqlQuery.deleteAllCartItem();

		RequestDispatcher rd = request.getRequestDispatcher("/MenuController");
		rd.forward(request, response);
	}
	/**
	 * 注文確定ボタンが押された時、注文履歴テーブルへインサート
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		PURCHASED_LOGテーブルからデータを取得
		SqlQuery sqlQuery = new SqlQuery();
		List<PurchasedLogBean> purchasedLogList = sqlQuery.selectAllPurchasedLog();
//		リクエストにpurchasedLogListをセット
		request.setAttribute("purchasedLogList", purchasedLogList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/purchasedLog.jsp");
		rd.forward(request, response);
	}

}
