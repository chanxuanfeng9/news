package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket socket;
	
	public Client(){
		//创建一个客户端的socket对象
		try {
			socket=new Socket("localhost",5896);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	//定义方法发送数据11
	public Object sendData(Object data){		
		ObjectOutputStream oos=null;
		ObjectInputStream ois=null;
		try {
			//获取用来发送数据的输出流
			oos=new ObjectOutputStream(socket.getOutputStream());
			//发送数据
			oos.writeObject(data);
			//刷新
			oos.flush();
			//获取输入流接收服务器的信息
			ois=new ObjectInputStream(socket.getInputStream());
			//读取数据并返回给调用者
			return ois.readObject();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(ois!=null){
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(oos!=null){
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;		
		
	}
}
