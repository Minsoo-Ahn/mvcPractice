package action;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardMvc.BoardDao;
import boardMvc.BoardDto;

public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String pageNum = req.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage - 1 )* pageSize +1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List<BoardDto> articleList = null;
		
		BoardDao dao = BoardDao.getInstance();
		
		count = dao.getArticleCount();  //전체페이지 글 개수
		if(count> 0) { //현재 페이지 글 목록
			articleList = dao.getArticles(startRow, endRow);
		}else {
			articleList = Collections.emptyList();
		}
		number = count - (currentPage -1) * pageSize; //글 목록에 표시할 글 번호
		
		//해당 뷰에서 사용할 속성
		req.setAttribute("currentPage", new Integer(currentPage));
		req.setAttribute("startRow", new Integer(startRow));
		req.setAttribute("endRow", new Integer(endRow));
		req.setAttribute("count", new Integer(count));
		req.setAttribute("pageSize", new Integer(pageSize));
		req.setAttribute("number", new Integer(number));
		req.setAttribute("articleList", articleList);
		return "/WEB-INF/boardMvc/list.jsp";
	}
}
