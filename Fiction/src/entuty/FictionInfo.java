package entuty;

import java.io.Serializable;

public class FictionInfo implements Serializable{
	private static final long serialVersionUID = 8526642605305523894L;
	private String title;  //С˵����
	private String author; //С˵����
	private String date;   //С˵�ϴ�����
	private String path;   //С˵·��
	private String dict;   //С˵����
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDict() {
		return dict;
	}
	public void setDict(String dict) {
		this.dict = dict;
	}	
}
