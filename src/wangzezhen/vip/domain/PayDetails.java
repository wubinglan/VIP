package wangzezhen.vip.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 消费明细
 * @author 王泽振
 *2016年2月25日 上午10:51:00
 */
public class PayDetails implements Serializable{

	private Card card;
	private Date pay_time;
	private int pay_money;
	
	/**
	 * 构造
	 */
	public PayDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PayDetails(Card card, Date pay_time, int pay_money) {
		super();
		this.card = card;
		this.pay_time = pay_time;
		this.pay_money = pay_money;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public int getPay_money() {
		return pay_money;
	}

	public void setPay_money(int pay_money) {
		this.pay_money = pay_money;
	}

	@Override
	public String toString() {
		 return "卡号："+this.getCard().getId()+"身份证："+this.getCard().getCustomer().getId_card()+
				"手机："+this.getCard().getCustomer().getMobile()+"消费日期："+this.pay_time+"金额："+this.pay_money+"余额"+this.card.getBalance();
		
	}
	
	
}
