package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class SqlQuery {
	//	SQL接続情報
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost/shopdb?useSSL=false";
	private final String USER_ID = "ryo";
	private final String USER_PASS = "password";

	Connection conn = null;
	ResultSet rs = null;
	Statement statement_name = null;
	/**
	 * 全ての商品情報をitemBeanにセットする
	 * @return
	 */
	public List<ItemBean> queryItemList() {
//		商品一覧取得SQL文
		final String  ITEM_LIST_STATEMENT = "SELECT ITEM_CODE,NAME,PRICE FROM ITEM;";
//		ItemBean型でリストを作成する。
		List<ItemBean> itemList = new ArrayList<ItemBean>();
//		アイテム毎にitemBeanインスタンスを作成
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			rs = statement_name.executeQuery(ITEM_LIST_STATEMENT);
					while(rs.next()){
						ItemBean itemBean = new ItemBean();
						itemBean.setCode(rs.getInt("ITEM_CODE"));
						itemBean.setName(rs.getString("NAME"));
						itemBean.setPrice(rs.getInt("PRICE"));
						itemList.add(itemBean);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (rs != null) {rs.close();}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		return itemList;
	}
	public List<CartBean> queryCartList() {
//		商品一覧取得SQL文
		final String  ITEM_LIST_STATEMENT = 
				"SELECT CART_ID,ITEM.ITEM_CODE,NAME,PRICE,NUM FROM ITEM "
				+ "JOIN CART ON ITEM.ITEM_CODE=CART.ITEM_CODE; ";
//		リストを作成する。
		List<CartBean> cartList = new ArrayList<CartBean>();
//		アイテム毎にインスタンスを作成
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			rs = statement_name.executeQuery(ITEM_LIST_STATEMENT);
					while(rs.next()){
						CartBean cartBean= new CartBean();
						cartBean.setCartId(rs.getInt("CART_ID"));
						cartBean.setItemCode(rs.getInt("ITEM.ITEM_CODE"));
						cartBean.setName(rs.getString("NAME"));
						cartBean.setPrice(rs.getInt("PRICE"));
						cartBean.setNum(rs.getInt("NUM"));
						cartList.add(cartBean);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (rs != null) {rs.close();}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		return cartList;
	} 
	/**
	 * 注文履歴を取得
	 * @return
	 */
	public List<OrderLogBean> queryOrderLog() {
//		商品一覧取得SQL文
		final String  ORDER_LOG_STATEMENT = 
				"SELECT LOG_ID,NAME,PRICE,NUM,DATE FROM ORDER_LOG "
				+ "JOIN ITEM ON ORDER_LOG.ITEM_ID=ITEM.CODE; ";
//		Bean型でリストを作成する。
		List<OrderLogBean> orderLog = new ArrayList<OrderLogBean>();
//		アイテム毎にitemBeanインスタンスを作成
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			rs = statement_name.executeQuery(ORDER_LOG_STATEMENT);
					while(rs.next()){
						OrderLogBean orderLogBean= new OrderLogBean();
						orderLogBean.setLogId(rs.getInt("LOG_ID"));
						orderLogBean.setName(rs.getString("NAME"));
						orderLogBean.setPrice(rs.getInt("PRICE"));
						orderLogBean.setNum(rs.getInt("NUM"));
						orderLogBean.setDate(rs.getString("DATE"));
						orderLog.add(orderLogBean);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (rs != null) {rs.close();}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		return orderLog;
	} 
	/**
	 * CODEをキーにしてORDERテーブルからNAMEとPRICEを取得する
	 * @param code
	 * @return
	 */
	public String[] selectItemInf(int code) {
		 String[] orderInf = new String[2];
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			String SELECT_ORDER_INF= "SELECT NAME,PRICE FROM ITEM WHERE  ITEM_CODE = " + code  +"; " ;
			rs = statement_name.executeQuery(SELECT_ORDER_INF);
			rs.next();
			orderInf = new String[]{rs.getString("NAME"),rs.getString("PRICE")};
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (rs != null) {rs.close();}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		return orderInf;
	}
	/**
	 * 注文情報をCARTテーブルに登録 **未完成
	 * @param cart
	 */
	public void insertCartItem(CartBean cart) {
//		   インサート用のSQL文
		int itemCode = cart.getItemCode();
		int num = cart.getNum();
		int cnt;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
//			MERGEを使ってカート内にある商品を注文した場合、追加し、ない場合は新規で登録する。
			String INSERT_BASE_STATEMENT = 
					"MERGE INTO CART"
					+ "USING (SELECT *";
			cnt = statement_name.executeUpdate(INSERT_BASE_STATEMENT);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (rs != null) {rs.close();}
			} catch (SQLException e3) {
				// TODO: handle exception
				e3.printStackTrace();
			}
		}
	}
	/**
	 * カート内の商品の種類数を取得
	 * @return
	 */
	public int queryItemNum() {
		int itemNum = 0;
		int cnt;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
//			カート内の商品の種類数を取得
			cnt = statement_name.executeUpdate("SELECT COUNT(ITEM_CODE) as itemNum FROM CART GROUP BY ITEM_CODE;");
			itemNum = rs.getInt("itemNum");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (rs != null) {rs.close();}
			} catch (SQLException e3) {
				// TODO: handle exception
				e3.printStackTrace();
			}
		}
		return itemNum;
	}
	/**
	 * カートの商品を全て注文履歴テーブルに登録
	 */
	public void insertOrderLog(int itemNum,String date) {
		int cnt;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
//			全てのカート内商品を注文履歴に登録する
			for (int i = 0 ; i < itemNum ; i++) {
//				カート内から商品を一種類取得する
				String SELECT_CART_ITEM = 
						"SELECT ITEM_CODE,SUM(NUM) FROM CART "
						+ "GROUP BY ITEM_CODE;";
//				取得した情報をカート内から削除する
				String DELETE_CART_ITEM = 
						"DELETE FROM CART GROUP BY ITEM_CODE HAVING 0; ";
//				インサート用のSQL文
				String INSERT_ORDER_LOG =
						"INSERT INTO ORDERLOG(ITEM_CODE,NUM,DATE)"
								+ " VALUES ('  ') ";
//				一種類の商品を登録する
				cnt = statement_name.executeUpdate(INSERT_ORDER_LOG);
//				登録した商品をカート内から消去する。
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (rs != null) {rs.close();}
			} catch (SQLException e3) {
				// TODO: handle exception
				e3.printStackTrace();
			}
		}
	}
	/**
	 * 注文した商品の数量変更
	 * @param code
	 * @param num
	 */
	public void updateCartNum(int orderId,int num) {
		String UPDEATE_NUM_STATEMRNT = "UPDATE CART SET NUM = '" + num + "'WHERE CART_ID ='" + orderId + "';";
		int cnt;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			cnt = statement_name.executeUpdate(UPDEATE_NUM_STATEMRNT);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (rs != null) {rs.close();}
			} catch (SQLException e3) {
				// TODO: handle exception
				e3.printStackTrace();
			}
		}
	}
	/**
	 * 注文した商品の削除
	 * @param code
	 */
	public void deleteCartItem(int code) {
		String DLETE_STATEMENT = "DELETE FROM CART WHERE CART_ID = '" + code +"';";
		int cnt;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			cnt = statement_name.executeUpdate(DLETE_STATEMENT);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (rs != null) {rs.close();}
			} catch (SQLException e3) {
				// TODO: handle exception
				e3.printStackTrace();
			}
		}
	}

	/**
	 * 注文したい商品をDBから取り出しリストを作成する
	 */
	public List<SearchBean> filterItem(String inputName) {
		int cnt;
		List<SearchBean> searchList = new ArrayList<SearchBean>();
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			String filterItem = "SELECT ITEM_CODE,NAME,PRICE FROM ITEM WHERE NAME LIKE '%" + inputName+ "%'; " ;
			rs = statement_name.executeQuery(filterItem);
			while(rs.next()) {
				SearchBean searchBeen = new SearchBean();
				searchBeen.setItemCode(rs.getInt("ITEM_CODE"));
				searchBeen.setName(rs.getString("NAME"));
				searchBeen.setPrice(rs.getInt("PRICE"));
				searchList.add(searchBeen);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (rs != null) {rs.close();}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		return searchList;
	}
	/**
	 * カート内に既にある商品が注文されたか確認 ＊＊未完成
	 */
	public void exsistsCartItem(int itemCode) {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			String filterItem = "SELECT ITEM_CODE FROM CART"
					+ " WHERE EXISTS("
					+ "SELECT '; " ;
			rs = statement_name.executeQuery(filterItem);
			while(rs.next()) {
				SearchBean searchBeen = new SearchBean();
				searchBeen.setItemCode(rs.getInt("ITEM_CODE"));
				searchBeen.setName(rs.getString("NAME"));
				searchBeen.setPrice(rs.getInt("PRICE"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (conn != null) {conn.close();}
				if (rs != null) {rs.close();}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
	}
}
