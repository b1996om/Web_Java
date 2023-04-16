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
	private static final String url = "jdbc:mariadb://localhost:3306/servletex";  //servletex : db�̸�
	private static final String user = "root";
	private static final String pwd = "1234";
	
	private Connection con;  
	private PreparedStatement pstmt;  // PreparedStatement ����ϸ� �ӵ��� �� ����
	
	public List listMembers() {
		List list = new ArrayList();
		try {
			connDB();
			String query = "select * from t_member order by joinDate desc"; //ȸ�� ������ �ֱ� ������ ������ ��ȸ
			//���ڿ��� query�� �ִ´�. 
			System.out.println("preparedStatement:" + query);//query�� �� ������ ����غ���
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(); //������ ����Ǵ� �κ�(�迭?ó�����۵Ǵ� �κ�(������?)�� ��ȯ����)
			while(rs.next()) { //next():������ ���� �ִ�?������ �߹�����
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs. getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberBean vo = new MemberBean(); //��ȸ�� ȸ�������� MemeberBean ��ü�� �Ӽ��� ������ �� �ٽ� ArrayList�� ����
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
		return list; //memberList ��ȯ(����Ʈ�� �ּ� 0x10������ ��ȯ)
	}
	//MemberBean ��ü�� ����� ȸ�������� ���̺��� ����
	public void addMember(MemberBean memberBean) {
		try {
			connDB();
			String id = memberBean.getId(); //getter�� �̿��Ͽ� ȸ������ ��������
			String pwd = memberBean.getPwd();
			String name = memberBean.getName();
			String email = memberBean.getEmail();
			String query = "insert into t_member"; //ȸ������ �߰�
		    query += " (id, pwd, name, email)";
		    query += " values(?,?,?,?)";
		    System.out.println("prepareStatement:" + query);
		    pstmt = con.prepareStatement(query);
		    pstmt.setString(1, id); //values(,,,,) 1��°�� id�Է�
		    pstmt.setString(2, pwd);
		    pstmt.setString(3, name);
		    pstmt.setString(4, email);
		    pstmt.executeUpdate(); //query����
		    pstmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle ����̹� �ε� ����");
			con = DriverManager.getConnection(url,user,pwd);
			System.out.println("Connection ���� ����");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}