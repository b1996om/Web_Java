package sec04.ex01;

import java.util.List;

public class BoardService {
	
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(); //articlesList에는 리스트들이 담김?
		return articlesList; //리스트아티클즈를 호출한 애한테로 감.->컨트롤러
	}
}
