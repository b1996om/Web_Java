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
		List articlesList = new ArrayList(); //arrayList: 배열들의 배열
		try {
			conn = dataFactory.getConnection(); //db연결(연동)한거 conn에 넣음
			String query = "SELECT CASE WHEN LEVEL -1 > 0 then"
		               + " CONCAT(CONCAT(REPEAT(' ', level-1),'<'), board.title)"
		               + " ELSE board.title END AS title, board.articleNO, board.parentNO,"
		               + " result.level, board.content, board.id, board.writeDate"
		               + " FROM (SELECT function_hierarchical() AS articleNO, @level AS level"
		               + " FROM (SELECT @start_with:=0, @articleNO:= @start_with, @level:=0) tbl JOIN t_board) result"
		               + " JOIN t_board board ON board.articleNO = result.articleNO;";
			
			pstmt = conn.prepareStatement(query); //
			ResultSet rs = pstmt.executeQuery(); //쿼리의 select구문은 executeQuery(), update delete ?는 executeUpdate().
			while(rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				
				ArticleVO article = new ArticleVO(); //ArticleVO 에서 지원되는 set을 이용해서 밑에서 사용하겠다.
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article); //배열구조의 0인덱스부터 차곡차곡 넣게된다.
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return articlesList; //배열의 시작주소를 나를 호출한애한테 던져줌
	}
}
