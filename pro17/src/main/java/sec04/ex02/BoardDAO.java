package sec04.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import sec04.ex02.ArticleVO;

public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/servletex");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List selectAllArticles() {
		List articlesList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT CASE WHEN LEVEL -1 > 0 then"
		               + " CONCAT(CONCAT(REPEAT(' ', level-1),'<'), board.title)"
		               + " ELSE board.title END AS title, board.articleNO, board.parentNO,"
		               + " result.level, board.content, board.id, board.writeDate"
		               + " FROM (SELECT function_hierarchical() AS articleNO, @level AS level"
		               + " FROM (SELECT @start_with:=0, @articleNO:= @start_with, @level:=0) tbl JOIN t_board) result"
		               + " JOIN t_board board ON board.articleNO = result.articleNO;";
			
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
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
				articlesList.add(article);
				
				System.out.println(level);
				System.out.println(articleNO);
				System.out.println(parentNO);
				System.out.println(title);
				System.out.println(content);
				System.out.println(id);
				System.out.println(writeDate);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	
	private int getNewArticleNO( ) {
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT max(articleNO) from t_board";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			if(rs.next()) {
				return(rs.getInt(1) + 1);
			}
				rs.close();
				pstmt.close();
				conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void insertNewArticle(sec04.ex02.ArticleVO article) {
		try {
			conn = dataFactory.getConnection();
			int articleNO = getNewArticleNO();
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
			String query = "INSERT INTO t_board(articleNO, parentNO, title, content, imageFileName, id)"
					+ " VALUES(?,?,?,?,?,?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch(Exception e ) {
			e.printStackTrace();
		}
	}
}
