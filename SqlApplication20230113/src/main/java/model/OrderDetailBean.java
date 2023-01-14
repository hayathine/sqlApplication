package model;

public class OrderDetailBean {
	int orderDetailId;
	int itemCode;
	int num;
	String itemName;
	int cancel;
	public OrderDetailBean() {
	}
	public OrderDetailBean(int orderDetailId, int itemCode, int num, String itemName,int cancel) {
		this.orderDetailId = orderDetailId;
		this.itemCode = itemCode;
		this.num = num;
		this.itemName = itemName;
		this.cancel = cancel;
	}
	
	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public int getItemCode() {
		return itemCode;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}
	
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	public int getCancel() {
		return cancel;
	}
}
