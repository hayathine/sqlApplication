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
		final String  ITEM_LIST_STATEMENT = "SELECT CODE,NAME,PRICE FROM ITEM;";
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
						itemBean.setCode(rs.getInt("CODE"));
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
//		groupbyで商品ごとにまとめる
		final String  ITEM_LIST_STATEMENT = 
				"SELECT ORDER_ID,ITEM.CODE,NAME,PRICE,NUM,DATE FROM ITEM "
				+ "JOIN CART ON ITEM.CODE=CART.CODE; ";
//		ItemBean型でリストを作成する。
		List<CartBean> cartList = new ArrayList<CartBean>();
//		アイテム毎にitemBeanインスタンスを作成
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			rs = statement_name.executeQuery(ITEM_LIST_STATEMENT);
					while(rs.next()){
						CartBean cartBean= new CartBean();
						cartBean.setOrderId(rs.getInt("ORDER_ID"));
						cartBean.setCode(rs.getInt("ITEM.CODE"));
						cartBean.setName(rs.getString("NAME"));
						cartBean.setPrice(rs.getInt("PRICE"));
						cartBean.setNum(rs.getInt("NUM"));
						cartBean.setDate(rs.getString("DATE"));
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
			String SELECT_ORDER_INF= "SELECT NAME,PRICE FROM ITEM WHERE  CODE = " + code  +"; " ;
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
	 * 注文情報をCARTテーブルに登録
	 * @param cart
	 */
	public void insertCartItem(CartBean cart) {
//		   インサート用のSQL文
		String INSERT_BASE_STATEMENT = "INSERT into cart(CODE,NUM,DATE) VALUES (' ";
		int code = cart.getCode();
		int num = cart.getNum();
		String date = cart.getDate();
		int cnt;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			INSERT_BASE_STATEMENT += code + " ' , ' "  + num +" ' , ' " + date +  " ' );";
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
	 * 注文した商品の数量変更
	 * @param code
	 * @param num
	 */
	public void updateCartNum(int orderId,int num) {
		String UPDEATE_NUM_STATEMRNT = "UPDATE CART SET NUM = '" + num + "'WHERE ORDER_ID ='" + orderId + "';";
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
		String DLETE_STATEMENT = "DELETE FROM CART WHERE ORDER_ID = '" + code +"';";
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
	 * 購入した商品をDBに登録
	 */
	public void insertPurchacedItem() {
		
	}
	/**
	 * 注文したい商品を検索する
	 */
	public void filterItem() {
		
	}
}
