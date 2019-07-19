package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardMvc.BoardDao;
import boardMvc.BoardDto;

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		int num = Integer.parseInt(req.getParameter("num"));
		
		String pageNum = req.getParameter("pageNum");
		BoardDao dao = BoardDao.getInstance();
		
		BoardDto dto = dao.getArticle(num);
		System.out.println(dto.getFilename());
		req.setAttribute("num", new Integer(num));
		req.setAttribute("pageNum", new Integer(pageNum));
		req.setAttribute("article", dto);
		
		return "/WEB-INF/boardMvc/content.jsp";
	}
	
}
