package sec02.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sec01.ex01.MemberDAO;
import sec01.ex01.MemberVo;

@WebServlet("/member/*")
public class MemberController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
		MemberDAO memberDAO;
		
	public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO();
	}

	public void destroy() {
		System.out.println("destroy �޼ҵ� ����");
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
		
		if(action == null || action.equals("/listMembers.do")) {
			List<MemberVo> membersList = memberDAO.listMember();
			request.setAttribute("membersList", membersList); //membersList : ����, ��ü�ּҴ��
			nextPage = "/test02/listMembers.jsp";
			
		} else if (action.equals("/addMember.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			MemberVo memberVo = new MemberVo(id, pwd, name, email);
			memberDAO.addMember(memberVo);
			nextPage = "/member/listMembers.do";
			
		} else if (action.equals("/memberForm.do")) {
			nextPage = "/test02/memberForm.jsp";
			
		} else {
			List<MemberVo> membersList = memberDAO.listMember();
			request.setAttribute("membersList", membersList);
			nextPage = "/test02/listMembers.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
