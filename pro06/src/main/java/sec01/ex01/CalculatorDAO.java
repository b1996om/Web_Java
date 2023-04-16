package sec01.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sec01.ex01.CalculatorBean;

public class CalculatorDAO {
	private static final String driver = "org.mariadb.jdbc.Driver";
	private static final String url = "jdbc:mariadb://localhost:3306/servletex2";  //servletex2 : db�̸�
	private static final String user = "root";
	private static final String pwd = "1234";
	private Connection con;
	private PreparedStatement pstmt;
	
	public List listMembers() {
		List list = new ArrayList();
		try {
			connDB();
			String query = "select * from calctbl"; 
			System.out.println("preparedStatement:" + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int n1 = rs.getInt("n1");
				int n2 = rs.getInt("n2");
				String op = rs. getString("op");
				int result = rs.getInt("result");
				
				CalculatorBean vo = new CalculatorBean(); 
				vo.setN1(n1);
				vo.setN2(n2);
				vo.setOp(op);
				vo.setResult(result);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//CalculatorBean ��ü�� ����� ������ ���̺� ����
	public void addMember(CalculatorBean calculatorBean) {
		try {
			connDB();
			int n1 = calculatorBean.getN1(); 
			int n2 = calculatorBean.getN2();
			String op = calculatorBean.getOp();
			int result = calculatorBean.calc();
			
			String query = "insert into calctbl"; // �߰�
		    query += " (n1, n2, op, result)";
		    query += " values(?,?,?,?)";
		    System.out.println("prepareStatement:" + query);
		    pstmt = con.prepareStatement(query);
			pstmt.setInt(1, n1); //values(,,,,) 1��°�� id�Է�
			pstmt.setInt(2, n2);
			pstmt.setString(3, op);
			pstmt.setInt(4, result);
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
