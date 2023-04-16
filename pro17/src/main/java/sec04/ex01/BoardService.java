package sec04.ex01;

import java.util.List;

public class BoardService {
	
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(); //articlesList���� ����Ʈ���� ���?
		return articlesList; //����Ʈ��ƼŬ� ȣ���� �����׷� ��.->��Ʈ�ѷ�
	}
}
