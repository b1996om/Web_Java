package sec01.ex02;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

public class MemberDAO {
	private static final String driver = "org.mariadb.jdbc.Driver";
	private static final String url = "jdbc:mariadb://localhost:3306/servletex";  //servletex : db이름
	private static final String user = "root";
	private static final String pwd = "1234";
	
	private Connection con;  
	private PreparedStatement pstmt;  // PreparedStatement 사용하면 속도가 더 빠름
	
	public List listMembers() {
		List list = new ArrayList();
		try {
			connDB();
			String query = "select * from t_member order by joinDate desc"; //회원 정보를 최근 가입일 순으로 조회
			//문자열을 query에 넣는다. 
			System.out.println("preparedStatement:" + query);//query에 잘 들어갔는지 출력해보기
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(); //실제로 실행되는 부분(배열?처음시작되는 부분(번지수?)을 반환해줌)
			while(rs.next()) { //next():다음에 값이 있니?있으면 중문실행
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs. getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberBean vo = new MemberBean(); //조회한 회원정보를 MemeberBean 객체의 속성에 저장한 후 다시 ArrayList에 저장
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list; //memberList 반환(리스트의 주소 0x10번지만 반환)
	}
	//MemberBean 객체에 저장된 회원정보를 테이블에 전달
	public void addMember(MemberBean memberBean) {
		try {
			connDB();
			String id = memberBean.getId(); //getter를 이용하여 회원정보 가져오기
			String pwd = memberBean.getPwd();
			String name = memberBean.getName();
			String email = memberBean.getEmail();
			String query = "insert into t_member"; //회원정보 추가
		    query += " (id, pwd, name, email)";
		    query += " values(?,?,?,?)";
		    System.out.println("prepareStatement:" + query);
		    pstmt = con.prepareStatement(query);
		    pstmt.setString(1, id); //values(,,,,) 1번째에 id입력
		    pstmt.setString(2, pwd);
		    pstmt.setString(3, name);
		    pstmt.setString(4, email);
		    pstmt.executeUpdate(); //query실행
		    pstmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url,user,pwd);
			System.out.println("Connection 생성 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
