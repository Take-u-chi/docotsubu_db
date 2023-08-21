package model;

public class Account {
 private int userId;
 private String name;
 private String pass;

 public Account(int userId,String name , String pass) {
	 this.userId = userId;
	 this.name = name;
	 this.pass = pass;
 }
 public Account(int userId) {
	 this.userId = userId;
 }

public int getUserId() {
	return userId;
}

public String getName() {
	return name;
}

public String getPass() {
	return pass;
}

}
