package model;

import java.io.Serializable;

public class ItemBean implements Serializable{
	int code;
	String name;
	int price ;
	public ItemBean() {
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
	public String getName () {
		return name;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	
}
