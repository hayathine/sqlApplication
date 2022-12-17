package model;

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
}
