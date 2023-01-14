package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CartLogic {
	String name;
	int code,price,num;
	static int totalPrice;
	public CartLogic() {
	}
	
	/**
	 * cartList内の全商品の価格を計算
	 * @param cartList
	 * @return
	 */
	public static int calcTotalPrice(List<CartBean> cartList) {
		int totalPrice = 0;
		//		伝票代金を計算
		for (CartBean cart:cartList) {
//			商品価格を商品テーブルから取ってくる
			int itemPrice = SqlQuery.fetchItemPrice(cart.getItemCode());  // ITEMマスタから価格を取得
			cart.setPrice(itemPrice);
			int price = CartLogic.calcItemUnitPrice(cart);   //		cartList内の全商品代金を税込みで計算
			totalPrice += price;
		}
		return totalPrice;
	}
	
	public static int inTaxPrice(int totalPrice) {
		return totalPrice * (int)Math.round(SettingApp.getTaxRate()); // 税率をかけて丸める
	}
	
	/**
	 *商品単位で代金を計算する
	 * @return
	 */
	public static int calcItemUnitPrice(CartBean cartBean) {
		 totalPrice = cartBean.getNum() * cartBean.getPrice();
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
