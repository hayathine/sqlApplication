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
import model.OrderLogBean;
import model.SearchBean;
import model.SqlQuery;

@WebServlet("/MenuController")
public class MenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MenuController() {
        super();
    }

    /**
     * 商品一覧を表示
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlQuery sqlQuery = new SqlQuery();
//		商品検索　
		if(request.getParameter("searchExecute") != null) {
			String inputName = request.getParameter("inputName");
			List<SearchBean> searchList= new ArrayList<SearchBean>();
			searchList = sqlQuery.filterItem(inputName);
			request.setAttribute("searchList", searchList);
		}
		
//		削除ボタンが押された場合の処理
		if(request.getParameter("deleteOrderId") != null) {
			Integer deleteOrderId =  Integer.parseInt(request.getParameter("deleteOrderId"));
			sqlQuery.deleteCartItem(deleteOrderId);
		}
//		数量変更ボタンが押された場合の処理
		if(request.getParameter("updateOrderId") != null) {
			int updateNum = Integer.parseInt(request.getParameter("updateNum"));
			int updateOrderId = Integer.parseInt(request.getParameter("updateOrderId")); 
			sqlQuery.updateCartNum(updateOrderId, updateNum);
		}
//		SqlQueryでItemBeanをセットし、リストに格納
		List<ItemBean> itemList = new ArrayList<ItemBean>();
		itemList = sqlQuery.queryItemList();

//		注文中の商品の表示
		List<CartBean> cartList = new ArrayList<CartBean>();
		if(!sqlQuery.queryCartList().isEmpty()) {
			cartList = sqlQuery.queryCartList();
			request.setAttribute("cartList", cartList);
		}
		
//		注文確定ボタン実行時、 **未完成
//		CARTテーブルのデータをORDER_LOGテーブルにインサート
//		CARTテーブルのデータはデリート
		if(request.getParameter("selectOrderLog") != null) {
			int itemNum = sqlQuery.queryItemNum();
//			注文確定日時を取得
			CartLogic cartLogic = new CartLogic();
			String date =cartLogic.getNowDateTime();
			sqlQuery.insertOrderLog(itemNum, date);
		}
		
//		requestスコープにitemListをセットアトリビュート
		request.setAttribute("itemList", itemList);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/menu.jsp");
		rd.forward(request, response);
	} 

	/**
	 * 選択された商品の注文ページを表示
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
