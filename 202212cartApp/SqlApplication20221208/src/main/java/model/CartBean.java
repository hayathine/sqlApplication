package model;

import java.io.Serializable;

public class CartBean implements Serializable{
	String code,name,price,num;
	int totalPrice;
	public CartBean() {
	}
	public CartBean(String code,String name,String price , String num , int totalPrice) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.num = num;
		this.totalPrice = totalPrice;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPrice() {
		return price;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getNum() {
		return num;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int gettotalPrice() {
		return totalPrice;
	}
}
