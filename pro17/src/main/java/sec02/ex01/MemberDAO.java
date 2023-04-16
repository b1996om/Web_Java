package sec02.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/servletex");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public List listMember() {
		List memberList = new ArrayList();
			try {
				con = dataFactory.getConnection();
				String query = "select * from t_member order by joinDate desc";
				System.out.println(query);
				pstmt = con.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					String id = rs.getString("id");
					String pwd = rs.getString("pwd");
					String name = rs.getString("name");
					String email = rs.getString("email");
					Date joinDate = rs.getDate("joinDate");
					
					MemberVo memberVo = new MemberVo(id, pwd, name, email, joinDate);
					memberList.add(memberVo);
				}
				rs.close();
				pstmt.close();
				con.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return memberList;
	}
	
	  public void addMember(MemberVo m) { //MemberVo사용하겠다.
		  try {
			  con = dataFactory.getConnection(); //db연동
			  String id = m.getId(); //받아온값저장
			  String pwd = m.getPwd();
			  String name = m.getName();
			  String email = m.getEmail();
			  String query = "INSERT INTO t_member(id,pwd,name,email)" + "VALUES(?,?,?,?)";
			  System.out.println(query);
			  
			  pstmt = con.prepareStatement(query); //
			  pstmt.setString(1, id); // ?의 첫번째에 id값 들어감
			  pstmt.setString(2, pwd); // ?의 두번째에 pwd값 들어감
			  pstmt.setString(3, name);
			  pstmt.setString(4, email);
			  pstmt.executeUpdate(); //db실행!-insert into 됨
			  pstmt.close();
			  
		  } catch (Exception e) {
			  e.printStackTrace();
			  
		  }
	  }
	  
}
