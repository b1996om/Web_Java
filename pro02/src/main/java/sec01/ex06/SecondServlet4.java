package sec01.ex06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SecondServlet3
 */
@WebServlet("/second8_3_2")
public class SecondServlet4 extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init �޼��� ȣ��");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("destroy �޼��� ȣ��");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		out.println("<html><body>");
		out.println("�̸�:"+ name);
		out.println("<br>");
		out.println("dispatch�� �̿��� forward �ǽ��Դϴ�.");
		out.println("</body></html>");
		
	}

}
