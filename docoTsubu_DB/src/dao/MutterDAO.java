package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

//MUTTERテーブルを担当するDAO

public class MutterDAO {

//データベース接続にかんする情報
	private final String JDBC_URL = "jdbc:mysql://localhost/docotsubu_db";
	private final String DB_USER = "root";
	private final String DB_PASS = "1234";


public List<Mutter> findAll(){   // 戻り値List<Mutter>
	List<Mutter>mutterList = new ArrayList<>();

//データベースへ接続
	try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

//SELECT文の準備
	String sql = "SELECT ID,NAME,TEXT FROM MUTTER ORDER BY ID DESC";//ID降順(大きい順)に並び替える
	PreparedStatement pStmt = conn.prepareStatement(sql);

//SELECT文の実行
	ResultSet rs = pStmt.executeQuery();

//SELECT文の結果をArrayListに格納
	while(rs.next()) {
		int id = rs.getInt("ID");
		String userName = rs.getString("NAME");
		String text = rs.getString("TEXT");
		Mutter mutter = new Mutter(id,userName,text);
		mutterList.add(mutter);
	}
	}catch(SQLException e) {
		e.printStackTrace();
		return null;
	}
     return mutterList;
}
public boolean create(Mutter mutter) {

//データベースへ接続
	try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

//INSERT文の準備（idは自動連番なので指定なし）
		String sql = "INSERT INTO MUTTER(NAME,TEXT) VALUES(?,?)";
		PreparedStatement pStmt = conn.prepareStatement(sql);

//INSERT文の「?」に使用する値のを設定
		pStmt.setString(1, mutter.getUserName());
		pStmt.setString(2,mutter.getText());

//INSERT文の実行（resultには追加された行数が代入される）
		int result = pStmt.executeUpdate();
		if(result != 1) {
			return false;
		}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	    return true;
		}


//つぶやき検索機能（入力されたテキストで検索する）
public List<Mutter> mutterSearch(String searchText) {
	List<Mutter>searchMutterList = new ArrayList<>();

//データベースへ接続
	try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){

//SELECT文の準備
	String sql = "SELECT * FROM MUTTER WHERE TEXT LIKE ?";
	PreparedStatement pStmt = conn.prepareStatement(sql);
	pStmt.setString(1,"%" + searchText +"%");

//SELECT文の実行
	ResultSet rs = pStmt.executeQuery();

	while(rs.next()) {
		int id = rs.getInt("ID");
		String userName = rs.getString("NAME");
		String text = rs.getString("TEXT");
		Mutter searchMutter = new Mutter(id,userName,text);
		searchMutterList.add(searchMutter);

	}
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
//SELECT文の結果を配列に入れて返す（該当するものがなければ空の配列を返す）
	return searchMutterList ;

}
public boolean deleteMutter(String id) {


	//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "SELECT * FROM MUTTER WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
		    pStmt.setString(1, id);
		    ResultSet rs = pStmt.executeQuery();

		    if(rs.next()) {

	//DELETE文の準備
			String sql2 = "DELETE FROM MUTTER WHERE id = ?";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);

		    pStmt2.setString(1, id);

	// SQLの実行
		    pStmt2.executeUpdate();
		    return true;
		    }


			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}return false;
			}

public List<Mutter> myMutterFindAll(String name){   // 戻り値List<Mutter>
	List<Mutter>myMutterList = new ArrayList<>();

//データベースへ接続
	try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
		String findName = name;

//SELECT文の準備
	String sql = "SELECT ID,NAME,TEXT FROM MUTTER WHERE NAME=? ORDER BY ID DESC";//ID降順(大きい順)に並び替える
	PreparedStatement pStmt = conn.prepareStatement(sql);
	pStmt.setString(1, findName);

//SELECT文の実行
	ResultSet rs = pStmt.executeQuery();

//SELECT文の結果をArrayListに格納
	while(rs.next()) {
		int id = rs.getInt("ID");
		String userName = rs.getString("NAME");
		String text = rs.getString("TEXT");
		Mutter mutter = new Mutter(id,userName,text);
		myMutterList.add(mutter);

	}
	}catch(SQLException e) {
		e.printStackTrace();
		return null;
	}
     return myMutterList;
}
}
