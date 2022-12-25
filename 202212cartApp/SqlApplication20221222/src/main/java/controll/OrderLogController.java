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
import model.OrderLogBean;

@WebServlet("/OrderLogController")
public class OrderLogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OrderLogController() {
        super();
    }
    /**
     * 注文履歴の情報を取得してorderLogにセット
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
//		OrderLogオブジェクトの取得
		OrderLogBean orderLog= (OrderLogBean)session.getAttribute("orderLog");
//		orderLogにパラメータをセット
		int num = Integer.parseInt(request.getParameter("num"));
		CartBean cartBean = new CartBean();
		cartBean.setNum(num);
		CartLogic cartLogic = new CartLogic();
		cartBean.setTotalPrice(cartLogic.calcTotalPrice(num, cartBean.getPrice()));
//		セッションにorderLogオブジェクトをセット
		session.setAttribute("orderLog", orderLog);
//		CARTテーブルにインサート
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/orderLog.jsp");
		rd.forward(request, response);
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
