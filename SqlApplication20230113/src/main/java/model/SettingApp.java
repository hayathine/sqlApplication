package model;

public class SettingApp {
	final static int MAX_ORDER_NUM = 50;
	final static Double TAX_RATE = 1.1;
	
	/**
	 * 注文数量の最大値を設定
	 * @return
	 */
	public  static int getMaxOrderNum() {
		return MAX_ORDER_NUM;
	}
	
	/**
	 * 税率を設定
	 * @return
	 */
	public static Double getTaxRate() {
		return TAX_RATE;
	}
}
