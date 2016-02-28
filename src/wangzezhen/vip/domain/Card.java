package wangzezhen.vip.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * 
 * @author 王泽振
 *2016年2月25日 上午10:35:43
 */
public class Card implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -369420971393078755L;
	private Customer customer;//顾客
	//	卡号
	private int id;
	//	押金
	private int money;
	//开卡时间   
	private Date open_time;
	//余额
	private int balance;
	/**
	 * 构造
	 */
	public Card() {
		super();
		this.id = this.creaId();
	}
	public Card(Customer customer, int money, Date open_time, int balance) {
		this();
		this.customer = customer;
		this.money = money;
		this.open_time = open_time;
		this.balance = balance;
	}
	
	/**
	 * 封装
	 * @return
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public Date getOpen_time() {
		return open_time;
	}
	
	public void setOpen_time(Date open_time) {
		this.open_time = open_time;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getId() {
		return id;
	}
	
	private int creaId(){
		
		return new Random().nextInt(100)+1;
		
	}
	@Override
	public String toString() {
		return "顾客:" + customer + ", 卡号:" + id + ", 押金:" + money
				+ ", 开卡时间:" + open_time + ", 余额:" + balance;
	}
	
	
}
