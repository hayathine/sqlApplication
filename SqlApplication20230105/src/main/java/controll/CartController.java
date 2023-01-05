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

@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CartController() {
        super();
    }
    /**
     * CartBeanに商品の数量と代金をセットして注文確認画面を表示
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		SqlQuery sqlQuery =new SqlQuery();
//		cartオブジェクトの取得
		CartBean cartBean = (CartBean)session.getAttribute("cart");
//		cartBeanに数量,代金をセット
		int num = Integer.parseInt(request.getParameter("num"));
		cartBean.setNum(num);
		cartBean.setTotalPrice(CartLogic.calcTotalPrice(num, cartBean.getPrice()));
//		カート内に新しく商品を追加する場合INSERT
		int beforeItemNum = sqlQuery.fetchItemNum(cartBean.getItemCode());
		if(beforeItemNum == 0) {
			sqlQuery.insertCartItem(cartBean);
		}else {
//		カート内に既にある商品をカートに入れる場合　UPDATE
			sqlQuery.updateCartNum(cartBean.getItemCode(),num,beforeItemNum);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/MenuController");
		rd.forward(request, response);
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
