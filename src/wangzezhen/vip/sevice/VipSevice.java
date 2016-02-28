package wangzezhen.vip.sevice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;














import wangzezhen.vip.domain.Card;
import wangzezhen.vip.domain.Customer;
import wangzezhen.vip.domain.IVipManager;
import wangzezhen.vip.domain.PayDetails;

public class VipSevice implements IVipManager{
	private final Scanner in = new Scanner(System.in);
	
	 int a ;
	 int i = 0;
	
	@Override
	public boolean isLogin(String username, String password) {
		//登录：读取文件文件中账号和密码
		File file = new File("admin.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String[] arr =new String[2];
			String line = br.readLine();
			while(line != null){
				arr = line.split(" ");
				String name=arr[0];
				String pwd=arr[1];
				if(name.equals(username) && pwd.equals(password)){
					return true;
				}else{
					return false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Card createCard(Customer customer) {
		System.out.println("请输入押金：");
		int money=in.nextInt();
		if(money <100){
			System.out.println("要存入大于一百的金额");
			money=in.nextInt();
		}
		Card card = new Card(customer, money, new Date(), money);
		CARDS.put(card.getId(), card);
		System.out.println("办卡成功, 当前有"+CARDS.size()+"个会员");
		
		return card;
	}

	//打印会员记录
		private void print() throws Exception{
			
				Writer w = new FileWriter(new Date().getTime()+"客户信息.txt");
				for(Iterator<Card>it = CARDS.values().iterator();it.hasNext();){
					Card ca =it.next();
					w.write(ca.toString());
					w.write("顾客身份证："+ca.getCustomer().getId_card()+"， 顾客电话："+ca.getCustomer().getMobile());
					w.write("\n卡号"+ca.getId()+", 押金:" + ca.getMoney()
							+ ", 开卡时间:" + ca.getOpen_time() + ", 余额:" + ca.getBalance());
					w.write("\n========================================================\n");
				}
				w.close();
			
		}
		
	@Override
	public void showInfo() {
		//		浏览所有会员
		//迭代
		boolean flag = flag2();
		if(!CARDS.isEmpty()){
			for(Iterator<Card> it = CARDS.values().iterator();it.hasNext();){
				System.out.println(it.next());
			}
			if(flag){
				try {
					this.print();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			System.out.println("还没有会员");
		}
	}

	@Override
	public Card findCardById(int id){
		// 根据卡号查询
		
		
		
		boolean flag = flag2();
		if(flag){
				Writer w = null;
				try {
				 w = new FileWriter(new Date().getTime()+" 卡号是"+id+"的会员信息.txt");
				 for(Iterator<Card> it = CARDS.values().iterator();it.hasNext();){
						if(it.next().getId() == id){
							Card cd = CARDS.get(id);
							w.write(cd.toString());
							
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						w.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
			for(Iterator<Card> it = CARDS.values().iterator();it.hasNext();){
				if(it.next().getId() == id){
					Card cd = CARDS.get(id);
					
					return cd;
				}
			}
			
			return null;
	}

	@Override
	public void pay(int id) {
		// 消费
		//得到卡号对应的消费集合
		List<PayDetails> ps = PAYDETAILS.get(id);
		if(ps == null) ps = new ArrayList<PayDetails>(20); 
		Card ca = this.findCardById(id);
		if(ca!=null){
			System.out.println("请输入消费金额");
			int money = in.nextInt();
			if(money<=ca.getBalance()){
				ca.setBalance(ca.getBalance()-money);
				PayDetails pd =	new PayDetails(ca, new Date(), money);
				ps.add(pd);//添加当前的消费记录
				PAYDETAILS.put(id, ps);//添加到总的消费记录
			}else{
				System.out.println("余额不足");
			}
		}else{
			System.out.println("卡号不存在");
		}
	}

	@Override
	public void printDetails(int id) throws Exception {
		// 根据卡号，查询该卡的所有消费记录
		boolean flag = flag2();
		if(flag){
			Writer w = new FileWriter(new Date().getTime()+" 卡号是"+id+"的消费信息.txt");
			List<PayDetails> ps = PAYDETAILS.get(id);
			if(ps.size() != 0){
				for(Iterator<PayDetails> it = ps.iterator();it.hasNext();){
					
					PayDetails pd = it.next();
					w.write(pd.toString());
				}
				w.close();
			}
		}
		
		List<PayDetails> ps = PAYDETAILS.get(id);
		if(ps.size() != 0){
			for(Iterator<PayDetails> it = ps.iterator();it.hasNext();){
				
				PayDetails pd = it.next();
				System.out.println(pd);
			}
		}else{
			System.out.println("该卡号暂时没有消费记录");
		}
	}
	
	
	@Override
	public void showAll() throws Exception {
		// 打印所有明细
		boolean flag = flag2();
		if(flag){
			Writer w = new FileWriter(new Date().getTime()+"消费信息.txt");
			for(Iterator<Integer> it =PAYDETAILS.keySet().iterator();it.hasNext();){
				int id = it.next();
				List<PayDetails> list = PAYDETAILS.get(id);
				for(PayDetails p :list){
					w.write(p.toString());
				}
				w.write("========================================================");
			}
			w.close();
		}
		for(Iterator<Integer> it =PAYDETAILS.keySet().iterator();it.hasNext();){
			int id = it.next();
			List<PayDetails> list = PAYDETAILS.get(id);
			for(PayDetails p :list){
				System.out.println(p);
			}
			System.out.println("=================================================");
		}
	}

	@Override
	public void save() throws Exception {
		// 序列化

		if(this.flag1()){
			File file = new File("vippay.ser");
			ObjectOutputStream out = new ObjectOutputStream(
										new FileOutputStream(file));
			out.writeObject(PAYDETAILS);
			out.close();
		}else{
			System.out.println("还没有消费记录");
		}
	}
	
	@Override
	public void saveCard() throws Exception {
		// 序列化会员
		if(this.flag()){
			File file = new File("vipCard.ser");
			ObjectOutputStream out = new ObjectOutputStream(
										new FileOutputStream(file));
			out.writeObject(CARDS);
			out.close();
		}else{
			System.out.println("还没有会员记录");
		}
	}

	@Override
	public void load() throws Exception {
		// 反序列化
		File file = new File("vippay.ser");
		File fe = new File("vipCard.ser");
		if(file.exists()){
			ObjectInputStream in = new ObjectInputStream(
										new FileInputStream(file));
			PAYDETAILS.putAll((Map<Integer, List<PayDetails>>) in.readObject());
			in.close();
		}else{
			System.out.println("没有保存过的消费记录");
		}
		if(fe.exists()){
			ObjectInputStream in = new ObjectInputStream(
										new FileInputStream(fe));
			CARDS.putAll((Map<Integer,Card>) in.readObject());
			in.close();
		}else{
			System.out.println("没有保存过的消费记录");
		}
		
	}
	
	public boolean flag(){
		if(!CARDS.isEmpty()){
			return true;
		}
		return false;
	}
	
	public boolean flag1(){
		if(!PAYDETAILS.isEmpty()){
			return true;
		}
		return false;
	}

	private boolean flag2(){
		a = i;
		i = CARDS.size();
		if(a<(CARDS.size())){
			return true;
		}
		return false;
		
	}

}
