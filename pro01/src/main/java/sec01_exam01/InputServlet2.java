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
		System.out.println("init �޼��� ȣ��");
	
	}
	
	public void destroy() {
		System.out.println("destroy �޼��� ȣ��");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		Enumeration enu = request.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String[] values = request.getParameterValues(name);
			for (String value : values) {  //values�� ���� �迭��! �޾ƿ� �迭�� value�� �ֱ�...?
				System.out.println("name=" + name+", value="+ value);
			}
		}
		
		
	}

}
