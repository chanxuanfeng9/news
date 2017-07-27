package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Util.ServerUtil;
import entuty.User;

public class Server {
	private ServerSocket server;

	public Server() {
		try {
			server = new ServerSocket(5896);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 服务器启动方法
	public void start() {

		while (true) { // 实现一直监听
			try {
				System.out.println("服务器正在建立连接......");
				// 监听
				Socket socket = server.accept();
				Thread thread = new Thread(new MyThread(socket));
				// 启动线程
				thread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class MyThread implements Runnable {
		private Socket socket;

		public MyThread(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			ObjectInputStream ois = null;
			ObjectOutputStream oos = null;

			try {

				// 获取服务器的对象输入流
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
				// 获取客户端发来的数据
				Object data = ois.readObject();
				// 判断接收到的数据是否是User对象
				if (data instanceof User) {
					User user = (User) data;
					if (user.getFlag() == 0) {
						// 登陆操作
						Boolean bool = ServerUtil.login(user);
						oos.writeObject(bool);
						oos.flush();
					}
					if (user.getFlag() == 1) {
						// 注册操作
						Boolean bool = ServerUtil.register(user);
						oos.writeObject(bool);
						oos.flush();
					}
				}
				//判断接收到的数据是否是小说类型
				if(data instanceof Integer){
					Integer num=(Integer)data;
					
				}
				// 获取服务器的对象输出流
				oos = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
}
