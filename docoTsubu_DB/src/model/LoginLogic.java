package model;

import dao.AccountDAO;

//ログインに関する処理を行うモデル


public class LoginLogic {

//パスワードの正誤判定を行うメソッド（ユーザー情報を引数で受け取り戻り値を返す）
 public boolean execute(User user) {
	 AccountDAO dao = new AccountDAO();
	 Account account = dao.findByLogin(user);
		 return account != null;

}
}
