package model;

import java.io.Serializable;

public class SearchBean implements Serializable{
	int itemCode;
	String name;
	int price ;
	public SearchBean() {
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
