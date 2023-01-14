package controll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CartBean;
import model.CartLogic;
import model.ItemBean;
import model.OrderBean;
import model.SearchBean;
import model.SqlQuery;

@WebServlet("/MenuController")
public class MenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MenuController() {
        super();
    }

    /**
     * 商品一覧の表示
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		商品検索ボタンが押された場合
		if(request.getParameter("searchExecute") != null) {
			String inputName = request.getParameter("inputName");
			List<SearchBean> searchList= new ArrayList<SearchBean>();
			searchList = SqlQuery.filterItem(inputName);
			request.setAttribute("searchList", searchList);
		}
//		削除ボタンが押された場合
		if(request.getParameter("deleteCartItem") != null) {
			HttpSession session = request.getSession(true);
			List<CartBean > cartList = (List<CartBean>)session.getAttribute("cartList");
			int deleteCartIndex =  Integer.parseInt(request.getParameter("deleteCartItem"));
			cartList.remove(deleteCartIndex);
		}
//		数量変更ボタンが押された場合
		if(request.getParameter("updateCartItem") != null) {
			HttpSession session = request.getSession(true);
			List<CartBean > cartList = (List<CartBean>)session.getAttribute("cartList");
			int updateNum = Integer.parseInt(request.getParameter("updateNum"));
			int updateCartIndex = Integer.parseInt(request.getParameter("updateCartItem")); 
			cartList.get(updateCartIndex).setNum(updateNum); // 変更後の数量をカート内商品にセット
		}
//		SqlQueryでItemBeanをセットし、リストに格納
		List<ItemBean> itemList = new ArrayList<ItemBean>(); 
		itemList = SqlQuery.queryItemList();
		request.setAttribute("itemList", itemList);  //	requestスコープにitemListをセットアトリビュート
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/menu.jsp");
		rd.forward(request, response);
	} 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
