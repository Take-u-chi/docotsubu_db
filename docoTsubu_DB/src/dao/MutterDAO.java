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

}
