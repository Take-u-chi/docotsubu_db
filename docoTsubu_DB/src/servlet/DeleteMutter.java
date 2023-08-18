package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.User;


@WebServlet("/DeleteMutter")
public class DeleteMutter extends HttpServlet {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String deleteText = request.getParameter("id");
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();

		if(deleteText == null) {
			request.setAttribute("errorMsg", "IDを入力して下さい");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
		    dispatcher.forward(request, response);
		}else if(!getMutterListLogic.deleteMutter(deleteText)) {
			request.setAttribute("errorMsg1", "該当の投稿はありませんでした");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
		    dispatcher.forward(request, response);
		}else {
			request.setAttribute("resultMsg", "投稿を削除しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
		    dispatcher.forward(request, response);
		}

	}

}
