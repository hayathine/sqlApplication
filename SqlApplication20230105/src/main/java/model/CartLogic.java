package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CartLogic {
	String name;
	int code,price,num;
	static int totalPrice;
	public CartLogic() {
	}
	/**
	 *代金を計算する
	 * @return
	 */
	public static int calcTotalPrice(int num ,int price) {
		 totalPrice = num * price;
		 return totalPrice;
	}
	/**
	 * 現在時刻を取得する
	 * @return
	 */
	public static String getNowDateTime() {
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar orderdate = Calendar.getInstance();
		return df.format(orderdate.getTime());
	}
}
