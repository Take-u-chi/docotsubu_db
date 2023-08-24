package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SignUpLogic;
import model.User;


@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");


		SignUpLogic sul = new SignUpLogic();
    //入力値チェック
		if(!sul.isValidName(name)) {
		request.setAttribute("errorMsg", "ユーザー名は英数字で4〜20字です");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/signUp.jsp");
		dispatcher.forward(request, response);

		}
		else if(!sul.isValidPass(pass)){
		request.setAttribute("errorMsg","パスワードは8〜16字です");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/signUp.jsp");
		dispatcher.forward(request, response);
		}else {

	//ユーザー登録
		User user = new User(name,pass);
	//	if(sul.isSignUp(user)) {

		if(sul.isSignUp(user)) {
    //↑TrueならUserをセッションスコープへ保存し登録結果画面へフォワード
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUpResult.jsp");
    	dispatcher.forward(request, response);

		}else {
	//Falseならエラーメッセージを画面に出力
		request.setAttribute("errorMsg","このユーザーは登録できません");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/signUp.jsp");
		dispatcher.forward(request, response);
		}
	}
	}
}