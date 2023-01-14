package model;

import java.io.Serializable;

public class CartBean implements Serializable{
	int cartId;
	int itemCode;
	String name;
	int price;
	int num;
	int totalPrice;
	public CartBean() {
	}
	public CartBean(int cartId,int itemCode,String name,int price , int num , int totalPrice ) {
		this.cartId = cartId;
		this.itemCode = itemCode;
		this.name = name;
		this.price = price;
		this.num = num;
		this.totalPrice = totalPrice;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getCartId() {
		return cartId;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public int getItemCode() {
		return itemCode;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getNum() {
		return num;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
}
