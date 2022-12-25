package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CartLogic {
	String name;
	int code,price,num,totalPrice;
	public CartLogic() {
	}
	/**
	 *代金を計算する
	 * @return
	 */
	public int calcTotalPrice(int num ,int price) {
		 totalPrice = num * price;
		 return totalPrice;
	}
	public String getNowDateTime() {
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar orderdate = Calendar.getInstance();
		return df.format(orderdate.getTime());
	}
}
