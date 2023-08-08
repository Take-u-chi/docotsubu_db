package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;


@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

//つぶやきに関するリクエストを処理するコントローラ

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//つぶやきリストを取得して、リクエストスコープに保存
	GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
	List<Mutter>mutterList = getMutterListLogic.execute();
	request.setAttribute("mutterList", mutterList);


//ログインしているか確認（ユーザー情報の取得）
	HttpSession session = request.getSession(); //セッションスコープの取得
	User loginUser = (User)session.getAttribute("loginUser"); //スコープからインスタンスの取得

	if(loginUser == null) {
//ログインしていなかった場合→index.jspへリダイレクト
	response.sendRedirect("/docoTsubu_DB/");
	}else {
//ログインが確認できたら→main.jspへフォワード
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
	dispatcher.forward(request, response);
}
}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

//リクエストパラメータの取得（main.jspから）
	request.setCharacterEncoding("UTF-8");
	String text = request.getParameter("text");

//↑入力値チェック
	if(text != null && text.length() != 0) {
		ServletContext application = this.getServletContext();
		List<Mutter>mutterList = (List<Mutter>)application.getAttribute("mutterList");

//セッションスコープに保存されたユーザー情報を取得
        HttpSession session = request.getSession();    //セッションスコープの取得
        User loginUser = (User)session.getAttribute("loginUser"); //スコープからインスタンスの取得

//つぶやきをつぶやきリストに追加
        Mutter mutter = new Mutter(loginUser.getName(),text);   //mutterクラスをインスタンス化
        PostMutterLogic postMutterLogic = new PostMutterLogic();//PostMutterLogicクラスをインスタンス化
        postMutterLogic.execute(mutter);               //↑executeメソッドの実行

//アプリケーションスコープにつぶやきリストを保存
        application.setAttribute("mutterList", mutterList);
	}else {
//エラーメッセージをリクエストコープに保存
		request.setAttribute("errorMsg", "つぶやきが入力されていません");
	}
//つぶやきリストを取得してリクエストスコープに保存
	    GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
	    List<Mutter>mutterList = getMutterListLogic.execute();
	    request.setAttribute("mutterList",mutterList);

//メイン画面にフォワード
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
    dispatcher.forward(request, response);
}
}