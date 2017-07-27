package Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import entuty.FictionInfo;
import entuty.User;

public class ServerUtil {
	// 登陆操作22222222
	public static boolean login(User user) {
		// 创建SAXReader对象
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read("src/account.xml");
			// 获取根元素
			Element root = doc.getRootElement();
			// 获取所有账户信息
			List<Element> accounts = root.elements();

			// 遍历所有账户信息
			for (Element account : accounts) {
				// 判断用户名和密码是否正确
				if (account.attributeValue("name").equals(user.getName())) {
					if (account.attributeValue("password").equals(user.getPassword())) {
						return true;
					} else {
						return false;
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 注册操作
	public static boolean register(User user) {
		SAXReader reader = new SAXReader();
		XMLWriter writer = null;
		try {
			Document doc = reader.read(new File("src/account.xml"));
			// 获取根元素
			Element root = doc.getRootElement();
			// 获取所有账户信息
			List<Element> accounts = root.elements();
			// 判断用户名和密码是否正确
			for (Element account : accounts) {
				if (!account.attributeValue("name").equals(user.getName())) {
					// 在根元素下创建一个新的子元素名字叫user
					Element account1 = root.addElement("user");
					// 给元素添加属性及属性值
					account1.addAttribute("name", user.getName());
					account1.addAttribute("password", user.getPassword());
					// 获取xml输出流
					writer = new XMLWriter(OutputFormat.createPrettyPrint());
					// 设置XML输出流的目的文件
					writer.setOutputStream(new FileOutputStream(new File("src/account.xml")));
					// 开始输出
					writer.write(doc);
					// 刷新
					writer.flush();
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println("注册时操作XML文档失败!");
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	//加载小说类型
	public static List<FictionInfo> loadFictionList(Integer num){
		//創建集合存儲小說信息
		List<FictionInfo> fictioninfo=new ArrayList<FictionInfo>();
		SAXReader reader = new SAXReader();		
		try {
			Document doc = reader.read(new File("src/account.xml"));
			// 获取根元素
			Element root = doc.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		
		
		return fictioninfo;
		
	}
	
}

