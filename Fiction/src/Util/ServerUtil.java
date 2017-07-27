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
	// ��½����22222222
	public static boolean login(User user) {
		// ����SAXReader����
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read("src/account.xml");
			// ��ȡ��Ԫ��
			Element root = doc.getRootElement();
			// ��ȡ�����˻���Ϣ
			List<Element> accounts = root.elements();

			// ���������˻���Ϣ
			for (Element account : accounts) {
				// �ж��û����������Ƿ���ȷ
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

	// ע�����
	public static boolean register(User user) {
		SAXReader reader = new SAXReader();
		XMLWriter writer = null;
		try {
			Document doc = reader.read(new File("src/account.xml"));
			// ��ȡ��Ԫ��
			Element root = doc.getRootElement();
			// ��ȡ�����˻���Ϣ
			List<Element> accounts = root.elements();
			// �ж��û����������Ƿ���ȷ
			for (Element account : accounts) {
				if (!account.attributeValue("name").equals(user.getName())) {
					// �ڸ�Ԫ���´���һ���µ���Ԫ�����ֽ�user
					Element account1 = root.addElement("user");
					// ��Ԫ��������Լ�����ֵ
					account1.addAttribute("name", user.getName());
					account1.addAttribute("password", user.getPassword());
					// ��ȡxml�����
					writer = new XMLWriter(OutputFormat.createPrettyPrint());
					// ����XML�������Ŀ���ļ�
					writer.setOutputStream(new FileOutputStream(new File("src/account.xml")));
					// ��ʼ���
					writer.write(doc);
					// ˢ��
					writer.flush();
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println("ע��ʱ����XML�ĵ�ʧ��!");
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
	
	//����С˵����
	public static List<FictionInfo> loadFictionList(Integer num){
		//�������ϴ惦С�f��Ϣ
		List<FictionInfo> fictioninfo=new ArrayList<FictionInfo>();
		SAXReader reader = new SAXReader();		
		try {
			Document doc = reader.read(new File("src/account.xml"));
			// ��ȡ��Ԫ��
			Element root = doc.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		
		
		return fictioninfo;
		
	}
	
}

