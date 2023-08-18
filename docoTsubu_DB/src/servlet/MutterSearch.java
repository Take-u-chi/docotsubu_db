package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.User;


//つぶやき検索機能

@WebServlet("/MutterSearch")
public class MutterSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//ログインしているか確認（ユーザー情報の取得）
	HttpSession session = request.getSession(); //セッションスコープの取得
	User loginUser = (User)session.getAttribute("loginUser"); //スコープからインスタンスの取得

	if(loginUser == null) {
//ログインしていなかった場合→index.jspへリダイレクト
	response.sendRedirect("/docoTsubu_DB/");
	}else {
//ログインが確認できたら→main.jspへフォワード
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
	dispatcher.forward(request, response);
}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String searchText = request.getParameter("text");
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
     	List<Mutter> searchList = new ArrayList<>();
    //入力値を渡し結果を配列で受け取る
     	searchList = getMutterListLogic.searchMutter(searchText);
   //入力値がある＆受け取った配列が空ではない時セッションスコープに保存しフォワード
     	 if(searchText != null && searchText.length() !=0 && !searchList.isEmpty()) {
	        HttpSession session = request.getSession();
		    session.setAttribute("searchList",searchList);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
		    dispatcher.forward(request, response);
	//受け取った配列が空の時にエラーメッセージを表示
		}else if(searchList.isEmpty()){
			request.setAttribute("errorMsg", "つぶやきが見つかりません");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
		    dispatcher.forward(request, response);
	//それ以外（入力値がない時）エラーメッセージを表示
		}else {
			request.setAttribute("errorMsg", "文字を入力して下さい");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
		    dispatcher.forward(request, response);
		}
	}
}