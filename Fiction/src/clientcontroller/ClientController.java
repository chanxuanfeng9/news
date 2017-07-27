package clientcontroller;

import java.util.Scanner;

import socket.Client;
import entuty.User;

public class ClientController {
	private static Scanner input = new Scanner(System.in);

	// 初始化登陆界面
	public boolean init() {
		System.out.println("------------欢迎进入在线迷你小说阅读系统----------");
		System.out.println("1.登录\n2.注册\n3.退出");
		System.out.print("请选择您的操作:");
		// 获取用户选择序号
		int num = input.nextInt();
		switch (num) {
		case 1:
			// 登陆操作
			return login();
		case 2:
			// 注册操作
			return register();
		case 3:
			// 退出系统
			System.out.println("程序已退出!");
			System.exit(1);
		}
		return false;
	}

	// 登陆操作、
	public boolean login() {
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
		System.out.println("登录操作");
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
		System.out.print("请输入用户名:");
		String name = input.next();
		System.out.print("请输入用户密码:");
		String pwd = input.next();
		// 创建对象，并初始化
		User user = new User();
		user.setName(name);
		user.setPassword(pwd);
		user.setFlag(0);
		// 创建一个客户端socket对象
		Client client = new Client();
		// 把数据发送到服务器并返回服务器的回复信息
		Object message = client.sendData(user);
		if (message instanceof Boolean) {
			Boolean bool = (Boolean) message;
			if (bool) {
				System.out.println("登录成功!");
				
			} else {
				System.out.println("登录失败!");
			}
			return bool;
		} else {
			System.out.println("服务器异常，请稍后再试。");
			return false;
			
		}
	}

	// 注册操作
	public boolean register() {
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
		System.out.println("登录操作");
		System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
		System.out.print("请输入用户名:");
		String name = input.next();
		String pwd1 = null;
		do {
			System.out.print("请输入密码:");
			pwd1 = input.next();
			System.out.print("请再次输入密码:");
			String pwd2 = input.next();
			if (!(pwd1.equals(pwd2))) {
				System.out.println("两次输入的密码不一致，请重新输入！");
			} else {
				break;
			}
		} while (true);
		// 创建对象
		User user = new User();
		user.setName(name);
		user.setPassword(pwd1);
		user.setFlag(1);
		// 创建一个客户端socket对象
		Client client = new Client();
		// 把数据发送到服务器并返回服务器的回复信息
		Object message = client.sendData(user);
		if (message instanceof Boolean) {
			Boolean bool = (Boolean) message;
			if (bool) {
				System.out.println("注册成功!");			
			} else {
				System.out.println("注册失败,你输入的用户名已被使用，请重新输入！");	
			}
		}
		return false;
	}
	
	//加载小说信息列表的方法
	public int loadFictionlist(){
		System.out.println("---------------------------------");
		System.out.println("----------小说类型列表---------");
		System.out.println("---------------------------------");
		System.out.println("1.魔幻     2.言情    3.武侠       0.返回上一级");
		//输入选择
		int num=input.nextInt();		
		return num;		
	}
	
	//加载被指定类型后的小说详细信息的方法
	public void loadFictionInfo( int fictiontype){
		
	}
	
	
	public static void main(String[] args) {
		ClientController client = new ClientController();
		//定义一个标记
		int flag=0;
		if(flag==0){
			boolean bool=client.init();
			if(bool){
				//登陆成功
				flag=1;
			}
		}
		if(flag==1){
			
		}
		
	}
}
