package model;

import java.io.Serializable;

public class PurchasedLogBean implements Serializable{
	int logId;
	String name;
	int price;
	int num;
	String date;
	public PurchasedLogBean() {
	}
	public PurchasedLogBean(int logId,String name,int price,int num , String date) {
		this.logId = logId;
		this.name = name;
		this.price = price;
		this.num = num;
		this.date = date;
	}
	public void setLogId(int LOG_ID) {
		this.logId = LOG_ID;
	}
	public int getLogId() {
		return logId;
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
	public void setDate(String DATE) {
		this.date = DATE;
	}
	public String getDate() {
		return date;
	}
}
