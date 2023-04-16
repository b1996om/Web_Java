package sec04.ex03;

import java.net.URLEncoder;
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
			
			System.out.println(query);
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
	
	private int getNewArticleNO() {
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT max(articleNO) as articleNO from t_board";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return(rs.getInt("articleNO") + 1);
			}
				rs.close();
				pstmt.close();
				conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 25;
	}
	
	public int insertNewArticle(ArticleVO article) {
		int articleNO = getNewArticleNO();
		try {
			conn = dataFactory.getConnection();
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
			String query = "INSERT INTO t_board (articleNO, parentNO, title, content, imageFileName, id)"
					+ "VALUES(?,?,?,?,?,?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,  articleNO);
			pstmt.setInt(2,  parentNO);
			pstmt.setString(3,  title);
			pstmt.setString(4,  content);
			pstmt.setString(5,  imageFileName);
			pstmt.setString(6,  id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return articleNO;
	}

	public ArticleVO selectArticle(int articleNO) {
		ArticleVO article = new ArticleVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select articleNO, parentNO, title, content, ifnull(imageFileName, 'null') as imageFileName, id, writeDate"
					+ " from t_board" + " where articleNO=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,  articleNO);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int _articleNO = rs.getInt("articleNO");
			int parentNO = rs.getInt("parentNO");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String imageFileName=URLEncoder.encode(rs.getString("imageFileName"), "utf-8");
			//파일 이름에 특수문자가 있을 경우 인코딩합니다.
			
			if(imageFileName.equals("null")) {
				imageFileName = null;
			}
			
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");
			article.setArticleNO(_articleNO);
			article.setParentNO(parentNO);
			article.setTitle(title);
			article.setContent(content);
			article.setImageFileName(imageFileName);
			article.setId(id);
			article.setWriteDate(writeDate);
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return article;
	}

	public void updateArticle(ArticleVO article) {
		int articleNO = article.getArticleNO();
		String title = article.getTitle();
		String content = article.getContent();
		String imageFileName = article.getImageFileName();
		try {
			conn = dataFactory.getConnection();
			String query = "update t_board set title=?, content=?";
			
			if(imageFileName != null && imageFileName.length() != 0) {
				query += ",imageFileName=?";
			} else  //?????
				
			query += " where articleNO=?";
			
			System.out.println(query);
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1,  title);
			pstmt.setString(2,  content);
			if(imageFileName != null && imageFileName.length() != 0) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4,  articleNO);
			} else {
				pstmt.setInt(3,  articleNO);
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

