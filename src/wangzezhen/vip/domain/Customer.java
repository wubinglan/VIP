package wangzezhen.vip.domain;

import java.io.Serializable;

public class Customer implements Serializable{

	//	身份证   
	private String id_card;
	//电话
	private String mobile;
	/**
	 * 构造
	 */
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String id_card, String mobile) {
		super();
		this.id_card = id_card;
		this.mobile = mobile;
	}
	
	/**
	 * 封装
	 * @return
	 */
	public String getId_card() {
		return id_card;
	}
	
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return " 身份证："+this.id_card+", 手机："+this.mobile;
	}
	
}
