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
import model.User;

@WebServlet("/MyPage")
public class MyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//ログインしているか確認（ユーザー情報の取得）
				HttpSession session = request.getSession(); //セッションスコープの取得
				User loginUser = (User)session.getAttribute("loginUser"); //スコープからインスタンスの取得

				if(loginUser == null) {
			//ログインしていなかった場合→index.jspへリダイレクト
				response.sendRedirect("/docoTsubu_DB/");
				}else {
			//ログインが確認できたら→.jspへフォワード
					GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
					List<Mutter> myMutterList = getMutterListLogic.searchMyMutter(loginUser.getName());

					ServletContext application = this.getServletContext();
					application.setAttribute("myMutterList", myMutterList);


					RequestDispatcher dispatcher = request.getRequestDispatcher("/myPage.jsp");
				dispatcher.forward(request, response);
			}
			}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();

		if(!getMutterListLogic.deleteMutter(id)) {
			request.setAttribute("errorMsg", "削除できませんでした");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/myPage.jsp");
		    dispatcher.forward(request, response);
		}else {
			request.setAttribute("errorMsg", "削除しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/myPage.jsp");
		    dispatcher.forward(request, response);

//			response.sendRedirect("/docoTsubu_DB/MyPage");
		}
	}

}
