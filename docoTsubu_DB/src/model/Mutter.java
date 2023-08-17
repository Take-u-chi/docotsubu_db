package model;
import java.io.Serializable;

//つぶやきに関する情報を持つJavaBeans

public class Mutter implements Serializable{
	private int id;		    //id
	private String userName;  //ユーザー名
	private String text;      //つぶやき内容

//コンストラクタ
	public Mutter(){}
	public Mutter(String userName,String text) {
		this.userName = userName;
		this.text = text;
	}
	public Mutter(String text) {
		this.text = text;
	}
	public Mutter(int id,String userName,String text) {
		this.id = id;
		this.userName = userName;
		this.text = text;
	}

	public String getUserName() {return userName;}
	public String getText() {return text;}
	public int getId() {return id;}
}
