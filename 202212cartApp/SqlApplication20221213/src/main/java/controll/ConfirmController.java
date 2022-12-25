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
import model.SqlQuery;

/**
 * Servlet implementation class ConfirmController
 */
@WebServlet("/ConfirmController")
public class ConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
//		セッションからCartオブジェクトを取り出す。
		CartBean cartBean = (CartBean)session.getAttribute("cart");
//		注文情報をORDERテーブルにインサートする。
		SqlQuery sqlQuery = new SqlQuery();
		sqlQuery.insert(cartBean);
		
		RequestDispatcher rd = request.getRequestDispatcher("/IndexController");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
