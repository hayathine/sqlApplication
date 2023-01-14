package model;

import java.io.Serializable;

public class OrderBean implements Serializable{
	int logId;
	int price;
	String date;
	int userId;
	int cancel;
	public OrderBean() {
	}
	public OrderBean(int logId,int price,int cancel , String date, int userId) {
		this.logId = logId;
		this.price = price;
		this.date = date;
		this.cancel = cancel;
		this.userId = userId;
	}
	
	public void setLogId(int LOG_ID) {
		this.logId = LOG_ID;
	}

	public int getLogId() {
		return logId;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setDate(String DATE) {
		this.date = DATE;
	}

	public String getDate() {
		return date;
	}

	public void  setCancel(int cancel) {
		this.cancel = cancel;
	}

	public int getCancel() {
		return cancel;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

}
