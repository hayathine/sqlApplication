package controll;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SqlQuery;
import model.OrderDetailBean;

/**
 * Servlet implementation class OederDetailController
 */
@WebServlet("/OrderDetailController")
public class OrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OrderDetailController() {
        super();
    }

    /**
     * 注文明細画面の表示
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String orderDetailId = request.getParameter("orderDetailId");
	List<OrderDetailBean> orderDetailList = SqlQuery.selectOrderDetail(orderDetailId); //	注文明細テーブルからデータを取得
	request.setAttribute("orderDetailList", orderDetailList); //	リクエストにorderDetailListをセット
	RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/orderDetail.jsp");
	rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//		数量変更ボタンが押された場合、伝票内の商品のキャンセルフラグを立てる。
		if(request.getParameter("updateOrderItem") != null) {
			int updateNum = Integer.parseInt(request.getParameter("updateNum"));
			String[] update = request.getParameter("updateOrderItem").split(",");
			SqlQuery.updateOrderDetailNum(update[0], updateNum, update[1]);
			List<OrderDetailBean> orderDetailList = SqlQuery.selectOrderDetail(update[0]); //	注文明細テーブルからデータを取得
			request.setAttribute("orderDetailList", orderDetailList); //	リクエストにorderDetailListをセット
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/orderDetail.jsp");
			rd.forward(request, response);
		}

// 		キャンセルボタンが押された場合、伝票内の商品にキャンセルフラグを立てる。
		if(request.getParameter("cancelItem") != null) {
			String[] deleteNum  = request.getParameter("cancelItem").split(",");
			SqlQuery.updateCancelFlag("ORDER_DETAIL", deleteNum[0], deleteNum[1]);  // テーブル名と注文番号からキャンセルフラグを立てる
//			同じ伝票内の商品がすべてキャンセルされた場合、伝票自体のキャンセルフラグも立てる
			if (SqlQuery.trueCancelFlag(deleteNum[0])) {
				SqlQuery.updateCancelFlag("ORDER_LOG", deleteNum[0]);
			} 
			List<OrderDetailBean> orderDetailList = SqlQuery.selectOrderDetail(deleteNum[0]); //	注文明細テーブルからデータを取得
			request.setAttribute("orderDetailList", orderDetailList); //	リクエストにorderDetailListをセット
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/orderDetail.jsp");
			rd.forward(request, response);
		}
		
		}
}
