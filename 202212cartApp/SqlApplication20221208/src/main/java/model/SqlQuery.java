package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;


public class SqlQuery {
	//	SQL接続情報
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost/shopdb?useSSL=false";
	private final String USER_ID = "ryo";
	private final String USER_PASS = "password";

	Connection conn = null;
	ResultSet rs = null;
	Statement statement_name = null;
	ItemListBean itemListBean = new ItemListBean();
//	商品情報を全てDBから取得する
	public ItemListBean queryItemList() {
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
						itemBean.setPrice(rs.getString("PRICE"));
						itemList.add(itemBean);
//					配列で商品情報をset
					 itemListBean = new ItemListBean(itemList);
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
		return itemListBean;
	}
//	ORDERテーブルにCODEをキーにしてNAMEとPRICEを取得する
	public String[] selectOrderItem(String code) {
		String SELECT_ORDER_ITEM;
		String[] nameAndPrice = new String[2];
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			SELECT_ORDER_ITEM= "SELECT NAME,PRICE FROM ITEM WHERE CODE = " + code  +"; " ;
			rs = statement_name.executeQuery(SELECT_ORDER_ITEM);
			rs.next();
			nameAndPrice = new String[]{rs.getString("NAME"),rs.getString("PRICE")};
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
		return nameAndPrice;
	}
//	注文情報をORDERテーブルに登録と情報の保持
	public void insert(CartBean cart) {
//		   インサート用のSQL文
		String INSERT_BASE_STATEMENT = "INSERT into ORDER(CODE,NUM) VALUES (' ";
		String code,num;
		code = cart.getCode();
		num = cart.getNum();
		int cnt;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			statement_name = conn.createStatement();
			INSERT_BASE_STATEMENT += code + " ' , ' "  + num + " ' );";
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
}
