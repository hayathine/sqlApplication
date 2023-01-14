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

@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CartController() {
        super();
    }
    /**
     * カートセッションリストに新規注文情報を追加
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		List<CartBean> cartList = new ArrayList<CartBean>();  //	cartListオブジェクトの取得
		if(session.getAttribute("cartList") != null) {
		cartList = (List<CartBean>)session.getAttribute("cartList");
		}
		CartBean cartBean = (CartBean)session.getAttribute("cart");  //	cartオブジェクトの取得
		int num = Integer.parseInt(request.getParameter("num"));  // 	cartBeanに数量,代金をセット
		cartBean.setNum(num);
		cartBean.setTotalPrice(CartLogic.calcItemUnitPrice(cartBean));  //	代金の計算
//		カートリストに新規注文を追加
		cartList.add(cartBean);
		session.setAttribute("cartList", cartList);
		RequestDispatcher rd = request.getRequestDispatcher("/MenuController");
		rd.forward(request, response);
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
