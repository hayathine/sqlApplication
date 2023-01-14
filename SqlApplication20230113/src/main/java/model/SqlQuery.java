package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;



public class SqlQuery {
	//	SQL接続情報
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost/shopdb?useSSL=false";
	private static final String USER_ID = "ryo";
	private static final String USER_PASS = "password";

	static Connection conn = null;
	static ResultSet rs = null;
	static Statement statement_name = null;
	/**
	 * 全ての商品情報をitemBeanにセットする
	 * @return
	 */
	public static List<ItemBean> queryItemList() {
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

	/**
	 * 注文明細テーブルのデータをすべて取得する。
	 * @return
	 */
	public static List<OrderDetailBean> selectOrderDetail(String orderDetailId) {
	//		商品一覧取得SQL文
			final String  ITEM_LIST_STATEMENT = 
					"SELECT ORDER_DETAIL_ID, NAME, NUM, O.ITEM_CODE, CANCEL FROM ORDER_DETAIL AS O"
					+ " JOIN ITEM ON ITEM.ITEM_CODE=O.ITEM_CODE "
					+ " WHERE ORDER_DETAIL_ID = " + orderDetailId + ";";
	//		リストを作成する。
			List<OrderDetailBean> orderDetailList = new ArrayList<OrderDetailBean>();
	//		アイテム毎にitemBeanインスタンスを作成
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
				statement_name = conn.createStatement();
				rs = statement_name.executeQuery(ITEM_LIST_STATEMENT);
						while(rs.next()){
							OrderDetailBean orderDetailBean = new OrderDetailBean();
							orderDetailBean.setOrderDetailId(rs.getInt("ORDER_DETAIL_ID"));
							orderDetailBean.setItemName(rs.getString("NAME"));
							orderDetailBean.setNum(rs.getInt("NUM"));
							orderDetailBean.setItemCode(rs.getInt("O.ITEM_CODE"));
							orderDetailBean.setCancel(rs.getInt("CANCEL"));
							orderDetailList.add(orderDetailBean);
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
			return orderDetailList;
		}

	public static List<OrderBean> selectAllOrderLog() {
	//		商品一覧取得SQL文
			final String  ITEM_LIST_STATEMENT = 
					"SELECT * FROM ORDER_LOG; ";
	//		PurchasedLogBean型でリストを作成する。
			List<OrderBean> orderList = new ArrayList<OrderBean>();
	//		アイテム毎にitemBeanインスタンスを作成
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
				statement_name = conn.createStatement();
				rs = statement_name.executeQuery(ITEM_LIST_STATEMENT);
						while(rs.next()){
							OrderBean orderBean = new OrderBean();
							orderBean.setLogId(rs.getInt("ORDER_DETAIL_ID"));
							orderBean.setPrice(rs.getInt("PRICE"));
							orderBean.setDate(rs.getString("DATE"));
							orderBean.setCancel(rs.getInt("CANCEL"));
							orderList.add(orderBean);
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
			return orderList;
		}

	/**
	 * CODEをキーにしてORDERテーブルからNAMEとPRICEを取得する
	 * @param code
	 * @return
	 */
	public static String[] selectItemInf(int code) {
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
		 * 注文明細テーブルへID,商品コード、数量をインサート
		 */
		public static void insertOrderDetail(int orderDetailId, int itemCode, int num) {
			int cnt;
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
				statement_name = conn.createStatement();
	//				インサート用のSQL文
					String insert_order_detail =
							"INSERT INTO ORDER_DETAIL(ORDER_DETAIL_ID, ITEM_CODE, NUM)"
									+ " VALUES (" + orderDetailId+ "," + itemCode + "," + num + ");";
	//				一種類の商品を登録する
					cnt = statement_name.executeUpdate(insert_order_detail);
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
	
		/**
		 * カートの商品を注文履歴テーブルに登録
		 */
		public static void insertOrder(String date,int price) {
			int cnt;
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
				statement_name = conn.createStatement();
	//				インサート用のSQL文
					String insert_order =
							"INSERT INTO ORDER_LOG(DATE, PRICE)"
									+ " VALUES ('" + date + "'," + price +  ");";
	//				一種類の商品を登録する
					cnt = statement_name.executeUpdate(insert_order);
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

	/**
	 * キャンセルフラグを立てる
	 * @param code
	 * @return
	 */
	public static void updateCancelFlag(String table, String orderDetailId ) {
		int cnt;
		String INSERT_CANCEL_FLAG= "UPDATE " + table + " SET CANCEL = 1"
				+ " WHERE ORDER_DETAIL_ID =" + orderDetailId + ";" ;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			cnt = statement_name.executeUpdate(INSERT_CANCEL_FLAG);
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

	public static boolean trueCancelFlag(String orderDetailId) {
		boolean flag = false;
		String SELCET_CANCEL_FLAG= "SELECT CANCEL FROM ORDER_DETAIL "
				+ "WHERE ORDER_DETAIL_ID = " +orderDetailId + " ;" ;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			rs = statement_name.executeQuery(SELCET_CANCEL_FLAG); 
			ArrayList<Integer> flagList = new ArrayList<>();
			while(rs.next()) {
				flagList.add(rs.getInt("CANCEL"));
			}
			if (!flagList.contains(0)) {
				 flag = true;
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
		return flag;
	}

	/**
	 * キャンセルフラグを立てる
	 * @param code
	 * @return
	 */
	public static void updateCancelFlag(String table, String orderDetailId, String itemCode) {
		int cnt;
		String INSERT_CANCEL_FLAG= "UPDATE " + table + " SET CANCEL = 1"
				+ " WHERE ORDER_DETAIL_ID =" + orderDetailId + " AND ITEM_CODE =  " + itemCode + ";" ;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			cnt = statement_name.executeUpdate(INSERT_CANCEL_FLAG);
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

	/**
	 * 注文したい商品をDBから取り出しリストを作成する
	 */
	public static List<SearchBean> filterItem(String inputName) {
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
	 * 注文日から注文明細番号を取得
	 */
	public static int fetchOrderDetailId(String date) {
	int orderDetailId = 0;	
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			String fetchId = "SELECT ORDER_DETAIL_ID FROM ORDER_LOG"
					+ " WHERE DATE='"+ date +"'; " ;
			rs = statement_name.executeQuery(fetchId);
			rs.next();
			orderDetailId = rs.getInt("ORDER_DETAIL_ID");
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
		return orderDetailId;
	}
	
	/**
	 * ITEMマスタから価格を取得
	 *
	 */
	public static int fetchItemPrice(int itemCode) {
		int itemPrice = 0;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			String fetchItemPrice = "SELECT PRICE FROM ITEM"
					+ " WHERE ITEM_CODE='"+ itemCode+"'; " ;
			rs = statement_name.executeQuery(fetchItemPrice);
			if(!rs.next()) {
				return 0;
			}
			itemPrice = rs.getInt("PRICE");
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
		return itemPrice;
	}

	/**
	 * 注文した商品の数量変更
	 * @param code
	 * @param num
	 */
	public static void updateOrderDetailNum(String orderId,int num, String itemCode) {
		String UPDEATE_NUM_STATEMRNT = "UPDATE ORDER_DETAIL SET NUM = " + num + ""
				+ " WHERE ORDER_DETAIL_ID =" + orderId + " AND ITEM_CODE =" + itemCode + ";";
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
				e3.printStackTrace();
			}
		}
	}

	/**
	 * 注文結合テーブルにオートインクリメントしたIDを挿入
	 *  @param original_1
	 * @param original_2
	 */
	public static void insertAutoIncrement(String original_1, String original_2) {
		String JOINT_STATEMENT= "INSERT INTO JOINT_ORDER(ORIGINAL_CODE_1, ORIGINAL_CODE_2) "
				+ "VALUES(" + original_1 + "," + original_2 +" );";   // 新規番号のインクリメントをどうするか
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			rs = statement_name.executeQuery(JOINT_STATEMENT);
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

	/**
	 * 結合番号を作成し、JOINT_ORDERテーブルに挿入
	 * 結合番号の接頭はA
	 * @param original_1
	 * @param original_2
	 */
	public static void jointOrder() {
		String JOINT_STATEMENT= "INSERT INTO JOINT_ORDER(JOINTED_ORDER) "
				+ "VALUES(A + LAST_INSERT_ID() );"; 
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			rs = statement_name.executeQuery(JOINT_STATEMENT);
			rs.next();
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
