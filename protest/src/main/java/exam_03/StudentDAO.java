package exam_03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public StudentDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/servletex");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List listStudent() {
		List studentList = new ArrayList();
			try {
				con = dataFactory.getConnection();
				String query = "select * from board";
				System.out.println(query);
				pstmt = con.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					String id = rs.getString("id");
					String name = rs.getString("name");
					String univ = rs.getString("univ");
					String birth = rs.getString("birth");
					String email = rs.getString("email");
					
					StudentVO studentVO = new StudentVO(id, name, univ, birth, email);
					studentList.add(studentVO);
				}
				rs.close();
				pstmt.close();
				con.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return studentList;
	}
	
	public void addStudent(StudentVO s) { //StudentVO사용하겠다.
		  try {
			  con = dataFactory.getConnection(); //db연동
			  String name = s.getName();
			  String univ = s.getUniv();
			  String birth = s.getBirth();
			  String email = s.getEmail();
			  String query = "INSERT INTO board(name,univ,birth,email)" + "VALUES(?,?,?,?)";
			  System.out.println(query);
			  
			  pstmt = con.prepareStatement(query); //
			  pstmt.setString(1, name);
			  pstmt.setString(2, univ);
			  pstmt.setString(3, birth);
			  pstmt.setString(4, email);
			  pstmt.executeUpdate(); //db실행!-insert into 됨
			  pstmt.close();
			  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
}

