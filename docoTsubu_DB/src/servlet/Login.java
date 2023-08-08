package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

//ログインに関するリクエストを処理するコントローラー

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//リクエストトパラメータの取得
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
	String pass = request.getParameter("pass");

//Userインスタンスの生成
	User user = new User(name,pass);

//ログイン処理
	LoginLogic loginLogic = new LoginLogic();//LoginLogicインスタンスの生成
	boolean isLogin = loginLogic.execute(user);//executeメソッドを実行→結果をisLoginに代入

//ログイン成功時の処理
	if(isLogin) {   //isLogin=trueの処理（falseはそのままフォワードへ）
		HttpSession session = request.getSession();//セッションスコープの取得
		session.setAttribute("loginUser", user);//セッションスコープへUserインスタンスを保存
	}
//フォワード
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
	dispatcher.forward(request, response);
}
}
