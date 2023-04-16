package sec04.ex02;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articledsList = boardDAO.selectAllArticles();
		return articledsList;
	}
	
	public void addArticle(ArticleVO article) {
		boardDAO.insertNewArticle(article);
	}
}
