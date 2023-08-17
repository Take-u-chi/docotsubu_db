package servlet;

import java.io.IOException;
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


@WebServlet("/MutterSearch")
public class MutterSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String searchText = request.getParameter("text");
		 GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
     	 List<Mutter> searchList = getMutterListLogic.searchMutter(searchText);

     	 if(searchText != null && searchText.length() !=0 && !getMutterListLogic.searchMutter(searchText).equals(null)) {

	     	 HttpSession session = request.getSession();
		     session.setAttribute("searchList",searchList);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
		    dispatcher.forward(request, response);
		}else {
			request.setAttribute("errorMsg", "文字が入力されていません");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
		    dispatcher.forward(request, response);
		}
	}
}