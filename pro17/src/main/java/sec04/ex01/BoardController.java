package sec04.ex01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sec04.ex01.BoardService;
/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
public class BoardController extends HttpServlet { //get방식으로.
	private static final long serialVersionUID = 1L;
		
		BoardService boardService; //객체생성
		ArticleVO articleVO;
		
	
	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
	}

	public void destroy() {
		System.out.println("destroy 메소드 호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage="";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); //html형식으로 표현하겠다.
		String action = request.getPathInfo(); //가장하위에 있는 매핑네임을 가져옴.(여기서는 2차 매핑네임인 listArticles.do)
		System.out.println("action:" + action);
		
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			
			if(action == null) {
				articlesList = boardService.listArticles(); //boardService의 리스트아티클을 호출!
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board01/listArticles.jsp";
				
			} else if (action.equals("/listArticles.do")) { //action이 listArticles.do와 같은지.
				articlesList = boardService.listArticles(); //articlesList = 0x10;
				request.setAttribute("articlesList", articlesList); //("articlesList", 0x10)
				nextPage = "/board01/listArticles.jsp";
			} else {
				nextPage="/board01/listArticles.jsp";
			}
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
