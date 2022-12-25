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

@WebServlet("/OrderLogController")
public class OrderLogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OrderLogController() {
        super();
    }
    /**
     * CartBeanに商品の数量と代金をセットして注文確認画面を表示
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
//		cartオブジェクトの取得
		CartBean cartBean = (CartBean)session.getAttribute("cart");
//		cartBeanに数量と代金をセット
		int num = Integer.parseInt(request.getParameter("num"));
		cartBean.setNum(num);
		CartLogic cartLogic = new CartLogic();
		cartBean.setTotalPrice(cartLogic.calcTotalPrice(num, cartBean.getPrice()));
//		セッションにカートオブジェクトをセット
//		session.setAttribute("cart", cartBean);
//		CARTテーブルにインサート
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/orderLog.jsp");
		rd.forward(request, response);
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
