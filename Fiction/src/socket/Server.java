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

	// ��������������
	public void start() {

		while (true) { // ʵ��һֱ����
			try {
				System.out.println("���������ڽ�������......");
				// ����
				Socket socket = server.accept();
				Thread thread = new Thread(new MyThread(socket));
				// �����߳�
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

				// ��ȡ�������Ķ���������
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
				// ��ȡ�ͻ��˷���������
				Object data = ois.readObject();
				// �жϽ��յ��������Ƿ���User����
				if (data instanceof User) {
					User user = (User) data;
					if (user.getFlag() == 0) {
						// ��½����
						Boolean bool = ServerUtil.login(user);
						oos.writeObject(bool);
						oos.flush();
					}
					if (user.getFlag() == 1) {
						// ע�����
						Boolean bool = ServerUtil.register(user);
						oos.writeObject(bool);
						oos.flush();
					}
				}
				//�жϽ��յ��������Ƿ���С˵����
				if(data instanceof Integer){
					Integer num=(Integer)data;
					
				}
				// ��ȡ�������Ķ��������
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
