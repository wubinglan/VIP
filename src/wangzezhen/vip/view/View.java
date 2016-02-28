package wangzezhen.vip.view;

import java.io.File;
import java.util.Scanner;

import wangzezhen.vip.domain.Card;
import wangzezhen.vip.domain.Customer;
import wangzezhen.vip.domain.IVipManager;
import wangzezhen.vip.sevice.VipSevice;

/**
 * 窗口类
 * @author 王泽振
 *2016年2月25日 上午11:23:57
 */
public class View {
	
	public static void main(String[] args) {
//			boolean bool = true;
			Scanner in = new Scanner(System.in);
			int i = 3;
			boolean flag = false;
			VipSevice flag1 = new VipSevice(); 
			System.out.println("欢迎你的使用，你有"+i+"次登陆机会");
			while(!flag){
				
				flag = login();
				if(!flag){
					--i;
					System.out.println("登陆失败，你还有"+i+"次登陆机会");
				}
				
				
			}
			if(flag){
				IVipManager manager=new VipSevice();
				File file = new File("vippay.ser");
				File fe = new File("vipCard.ser");
				if(file.exists()|fe.exists()){
					System.out.println("您存在旧的记录，是否导入?YES:Y,NO:N");
					String str = in.next();
					if(str.equalsIgnoreCase("y")){
						try {
							manager.load();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				int m = 0;
				do {
					System.out.println("=============美美美发店会员管理======");
					System.out.println("1:办卡  2：浏览会员信息   3查询单个会员信息  4消费 5查询个人消费明细 6 查询所有会员消费明细7保存消费记录8读取历史纪录9退出");
					System.out.println("请选择你要进行的操作");
                    m=in.nextInt();
                    switch (m) {
					case 1:
						
						System.out.println("请输入您的身份证：");
						String idno=in.next();
						System.out.println("请输入手机号码：");
						String mobile=in.next();
						Customer customer=new Customer(idno, mobile);
					    Card card=manager.createCard(customer);
						System.out.println("您的卡号是："+card.getId());
						
						break;
						
					case 2:
						manager.showInfo();
						break;
						
					case 3:
//						VipSevice flag1 = new VipSevice(); 
						flag = flag1.flag();
						if(flag){
							System.out.println("请输入您要查询的卡号");
							int id = in.nextInt();
							if(manager.findCardById(id) != null){
								System.out.println(manager.findCardById(id));
							}else{
								System.out.println("您查找的卡号不存在");
							}
						}else{
							System.out.println("目前还没有会员信息");
						}
						
						break;
						
					case 4://消费
//						VipSevice flag2 = new VipSevice(); 
						flag = flag1.flag();
						if(flag){
							System.out.println("请输入卡号");
							int id = in.nextInt();
							manager.pay(id);
						}else{
							System.out.println("目前还没有会员信息");
						}
						break;
					case 5:
						flag = flag1.flag1();
						if(flag){
							System.out.println("请输入卡号");
							int id = in.nextInt();
							try {
								manager.printDetails(id);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else{
							System.out.println("目前还没有消费信息");
						}
						break;
					case 6:
						flag = flag1.flag1();
						if(flag){
							try {
								manager.showAll();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else{
							System.out.println("目前还没有消费信息");
						}
						break;
					case 7:
						
						try {
							manager.save();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("保存消费记录成功");
						break;
					case 8:
						System.out.println("加载旧的记录");
						try {
							manager.load();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						manager.showInfo();
						flag = flag1.flag1();
						if(flag){
							try {
								manager.showAll();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						break;
					case 9 :
						
						try {
							manager.saveCard();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							manager.save();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
						System.out.println("谢谢使用,保存成功");
						System.exit(0);
						break;
					}
				} while (m <= 9);
			}
		
	}
	
	private static boolean login(){
		Scanner in = new Scanner(System.in);
		IVipManager manager=new VipSevice();
		System.out.println("请输入账号：");
		String username=in.next();
		System.out.println("请输入密码：");
		String password=in.next();
		boolean flag =manager.isLogin(username, password);
		return flag;
	}
}
