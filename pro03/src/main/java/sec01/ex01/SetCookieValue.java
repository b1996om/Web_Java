package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetCookieValue
 */
@WebServlet("/set")
public class SetCookieValue extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 호출");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Date d = new Date(0);
		Cookie c = new Cookie("cookieTest", URLEncoder.encode("JSP프로그래밍입니다.", "UTF-8"));
		
		c.setMaxAge(24*60*60);
		response.addCookie(c);
		out.print("현재시간:" + d);
		out.print("현재시간을 Cookie로 지정합니다.");
	}

}
