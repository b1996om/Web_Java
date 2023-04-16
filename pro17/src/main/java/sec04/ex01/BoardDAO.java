package sec04.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	
	public BoardDAO() {
		try {
			Context stx = new InitialContext();
			Context envContext = (Context) stx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/servletex");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List selectAllArticles() {
		List articlesList = new ArrayList(); //arrayList: �迭���� �迭
		try {
			conn = dataFactory.getConnection(); //db����(����)�Ѱ� conn�� ����
			String query = "SELECT CASE WHEN LEVEL -1 > 0 then"
		               + " CONCAT(CONCAT(REPEAT(' ', level-1),'<'), board.title)"
		               + " ELSE board.title END AS title, board.articleNO, board.parentNO,"
		               + " result.level, board.content, board.id, board.writeDate"
		               + " FROM (SELECT function_hierarchical() AS articleNO, @level AS level"
		               + " FROM (SELECT @start_with:=0, @articleNO:= @start_with, @level:=0) tbl JOIN t_board) result"
		               + " JOIN t_board board ON board.articleNO = result.articleNO;";
			
			pstmt = conn.prepareStatement(query); //
			ResultSet rs = pstmt.executeQuery(); //������ select������ executeQuery(), update delete ?�� executeUpdate().
			while(rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				
				ArticleVO article = new ArticleVO(); //ArticleVO ���� �����Ǵ� set�� �̿��ؼ� �ؿ��� ����ϰڴ�.
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article); //�迭������ 0�ε������� �������� �ְԵȴ�.
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return articlesList; //�迭�� �����ּҸ� ���� ȣ���Ѿ����� ������
	}
}
