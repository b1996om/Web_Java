package exam_03;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boad/*")
public class studentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		StudentDAO studentDAO;
	
	public void init(ServletConfig config) throws ServletException {
		studentDAO = new StudentDAO();
	}

	public void destroy() {
		System.out.println("destroy 메소드 실행");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		if(action == null || action.equals("/studentlists")) {
			List<StudentVO> studentsList = studentDAO.listStudent();
			request.setAttribute("studentsList", studentsList);
			nextPage = "/exam03/listStudents.jsp";
			
		} else if (action.equals("/addStudent.do")) {
			String name = request.getParameter("name");
			String univ = request.getParameter("univ");
			String birth = request.getParameter("birth");
			String email = request.getParameter("email");
			StudentVO studentVO = new StudentVO(name, univ, birth, email);
			studentDAO.addStudent(studentVO);
			nextPage = "/boad/studentlists";
			
		} else if (action.equals("/studentForm.do")) {
			nextPage = "/exam03/studentForm.jsp";
			
		} else {
			List<StudentVO> studentsList = studentDAO.listStudent();
			request.setAttribute("studentsList", studentsList);
			nextPage = "/exam03/listStudents.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
