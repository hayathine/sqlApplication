package model;

import java.io.Serializable;

public class ItemBean implements Serializable{
	int code;
	String name;
	String price ;
	String[] items; 
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
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPrice() {
		return price;
	}
	
}
