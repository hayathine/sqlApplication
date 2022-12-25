package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemListBean implements Serializable{
	List<ItemBean> itemList = new ArrayList<ItemBean>();
	public ItemListBean() {
	}
	public ItemListBean(List<ItemBean> itemList) {
		this.itemList = itemList;
	}
	public void setItemList(List<ItemBean> itemList) {
		this.itemList = itemList;
	}
	public List<ItemBean> getItemList(){
		return itemList;
	}
}
