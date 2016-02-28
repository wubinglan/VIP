package wangzezhen.vip.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *会员接口
 * @author 王泽振
 *2016年2月25日 上午10:39:29
 */
public interface IVipManager {
	//会员集合
	Map<Integer, Card> CARDS = new HashMap<Integer, Card>();
	//消费集合
	Map<Integer,List<PayDetails>> PAYDETAILS = new HashMap<Integer,List<PayDetails>>();
	//登陆
	boolean isLogin(String username,String password);
	//办卡("添加到会员集合中")
	Card createCard(Customer customer);
	//浏览所有会员
	void showInfo();
	//根据卡号查询
	Card findCardById(int id);
	//消费
	void pay(int id);
	//打印客户消费明细
	void printDetails(int id) throws Exception;
	//打印所有明细
	void showAll( ) throws Exception;
	//保存所有消费记录
	void save( ) throws Exception;
	void saveCard( ) throws Exception;
	//导入所有消费记录
	void load( ) throws Exception;
}
