package model;

import java.io.Serializable;

public class CartBean implements Serializable{
	int orderId;
	int code;
	String name;
	int price;
	int num;
	int totalPrice;
	String date;
	public CartBean() {
	}
	public CartBean(int orderId,int code,String name,int price , int num , int totalPrice , String date) {
		this.orderId = orderId;
		this.code = code;
		this.name = name;
		this.price = price;
		this.num = num;
		this.totalPrice = totalPrice;
		this.date = date;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderId() {
		return orderId;
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
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
}
