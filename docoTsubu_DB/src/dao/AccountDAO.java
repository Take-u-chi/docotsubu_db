package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.User;

public class AccountDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost/docotsubu_db";
	private final String DB_USER = "root";
	private final String DB_PASS = "1234";

public Account findByLogin(User user) {
	Account account = null;

	try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

		String sql = "SELECT USER_ID,NAME,PASS FROM ACCOUNT WHERE NAME=? AND PASS=?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,user.getName());
		pStmt.setString(2,user.getPass());

		ResultSet rs = pStmt.executeQuery();

		if(rs.next()) {
			int userId = rs.getInt("USER_ID");
			String name = rs.getString("NAME");
			String pass = rs.getString("PASS");

			account = new Account(userId,name,pass);
		return account;
		}else {
			return null;
		}
	}catch (SQLException e) {
		e.printStackTrace();
		return null;
	}

	}



public boolean signUp(User user) {
		AccountDAO dao = new AccountDAO();
		Account signUpUser = dao.findByLogin(user);

	//ユーザーが見つからない or 同じ名前がいなければINSERT文を実行しTrueを返す
		if(signUpUser == null || !user.getName().equals(signUpUser.getName()) ) {
	//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

	//INSERT文の準備（idは自動連番なので指定なし）
			String sql = "INSERT INTO ACCOUNT(NAME,PASS) VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);


	//INSERT文の「?」に使用する値のを設定
			pStmt.setString(1, user.getName());
			pStmt.setString(2,user.getPass());

	//INSERT文の実行（）

            pStmt.executeUpdate();
			    return true;

			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
	//INSERT文を実行できなければFalseを返す

		}else {
		return false;
		}
}


public boolean register(User user) {
	try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

		String sql = "SELECT USER_ID,NAME,PASS FROM ACCOUNT";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,user.getName());
		pStmt.setString(2,user.getPass());

		ResultSet rs = pStmt.executeQuery();

		if(rs.getString("NAME") != user.getName()) {
			String sql2 = "INSERT INTO ACCOUNT(NAME,PASS) VALUES(?,?)";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);


	//INSERT文の「?」に使用する値のを設定
			pStmt2.setString(1, user.getName());
			pStmt2.setString(2,user.getPass());

	//INSERT文の実行（）

            pStmt.executeUpdate();
			    return true;
		}
	}catch (SQLException e) {
		e.printStackTrace();
		return false;
}
	return false;
}

}