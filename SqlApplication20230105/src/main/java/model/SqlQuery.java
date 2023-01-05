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
	public List<PurchasedLogBean> selectAllPurchasedLog() {
	//		商品一覧取得SQL文
			final String  ITEM_LIST_STATEMENT = 
					"SELECT P.LOG_ID,ITEM.NAME,ITEM.PRICE,P.NUM,P.DATE FROM PURCHASED_LOG AS P "
					+ "JOIN ITEM ON P.ITEM_CODE=ITEM.ITEM_CODE;";
	//		PurchasedLogBean型でリストを作成する。
			List<PurchasedLogBean> purchasedLogList = new ArrayList<PurchasedLogBean>();
	//		アイテム毎にitemBeanインスタンスを作成
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
				statement_name = conn.createStatement();
				rs = statement_name.executeQuery(ITEM_LIST_STATEMENT);
						while(rs.next()){
							PurchasedLogBean purchasedLogBean = new PurchasedLogBean();
							purchasedLogBean.setLogId(rs.getInt("P.LOG_ID"));
							purchasedLogBean.setName(rs.getString("ITEM.NAME"));
							purchasedLogBean.setPrice(rs.getInt("ITEM.PRICE"));
							purchasedLogBean.setNum(rs.getInt("P.NUM"));
							purchasedLogBean.setDate(rs.getString("P.DATE"));
							purchasedLogList.add(purchasedLogBean);
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
			return purchasedLogList;
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
			String INSERT_BASE_STATEMENT = 
					"INSERT INTO CART(CART_ID,ITEM_CODE,NUM)"
					+ "VALUE(" + cart.getCartId() + "," + cart.getItemCode() + "," + cart.getNum() + ");";
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
		 * カートの商品を注文履歴テーブルに登録
		 */
		public void insertPurchasedLog(int itemCode,int num, String date) {
			int cnt;
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
				statement_name = conn.createStatement();
	//				インサート用のSQL文
					String insert_purchsed_log =
							"INSERT INTO PURCHASED_LOG(ITEM_CODE,NUM,DATE)"
									+ " VALUES (" + itemCode + "," + num +",'" + date +"');";
	//				一種類の商品を登録する
					cnt = statement_name.executeUpdate(insert_purchsed_log);
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
		public void deleteAllCartItem() {
			int cnt;
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
				statement_name = conn.createStatement();
	//				取得した情報をカート内から削除する
					cnt = statement_name.executeUpdate("DELETE FROM CART; ");
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
	 * 注文した商品の数量追加
	 * @param cartId
	 * @param num
	 * @param beforeItemNum
	 */
	public void updateCartNum(int cartItem,int num,int beforeItemNum) {
		int afterItemNum = beforeItemNum + num;
		String UPDEATE_NUM_STATEMRNT = "UPDATE CART SET NUM = " + afterItemNum+ " WHERE ITEM_CODE =" + cartItem + ";";
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
		String DLETE_STATEMENT = "DELETE FROM CART WHERE ITEM_CODE = '" + code +"';";
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
	 * カート内に既にある商品を注文したか確認 ＊＊未完成
	 */
	public int fetchItemNum(int itemCode) {
		int orderedNum = 0;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			String fetchNum = "SELECT NUM FROM CART"
					+ " WHERE ITEM_CODE='"+ itemCode+"'; " ;
			rs = statement_name.executeQuery(fetchNum);
			rs.next();
			orderedNum = rs.getInt("NUM");
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
		return orderedNum;
	}
}
