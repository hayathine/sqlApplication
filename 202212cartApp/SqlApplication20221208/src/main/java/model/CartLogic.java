package model;

public class CartLogic {
	String code,name,price,num;
	int totalPrice;
	public CartLogic() {
	}
	public CartLogic(String code , String name , String price , String num) {
		this.code = code;
		 this.name = name;
		 this.price = price;
		 this.num = num;
	}
//	ORDERテーブルに必要なCODE,NUMのセットと注文確認画面に必要なname,price,totalPriceをCartBeanにセットする
	public CartBean execute() {
		 totalPrice = Integer.parseInt(num) * Integer.parseInt(price);
		 CartBean cartBean = new CartBean(code, name, price, num, totalPrice);
		 return cartBean;
	}
}
