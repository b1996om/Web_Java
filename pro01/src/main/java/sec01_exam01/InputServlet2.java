package sec01_exam01;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input2")
public class InputServlet2 extends HttpServlet {
	
	public void init() throws ServletException {
		System.out.println("init 메서드 호출");
	
	}
	
	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		Enumeration enu = request.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String[] values = request.getParameterValues(name);
			for (String value : values) {  //values는 위에 배열명! 받아온 배열을 value에 넣기...?
				System.out.println("name=" + name+", value="+ value);
			}
		}
		
		
	}

}
