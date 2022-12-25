package model;

import java.io.Serializable;

public class CartBean implements Serializable{
	int code;
	String name;
	int price;
	int num;
	int totalPrice;
	public CartBean() {
	}
	public CartBean(int code,String name,int price , int num , int totalPrice) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.num = num;
		this.totalPrice = totalPrice;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
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
	public int gettotalPrice() {
		return totalPrice;
	}
}
