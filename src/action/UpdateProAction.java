package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardMvc.BoardDao;
import boardMvc.BoardDto;

public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		req.setCharacterEncoding("utf-8");
		String pageNum = req.getParameter("pageNum");
		
		BoardDto dto = new BoardDto();
		dto.setNum(Integer.parseInt(req.getParameter("num")));
		dto.setWriter(req.getParameter("writer"));
		dto.setEmail(req.getParameter("email"));
		dto.setSubject(req.getParameter("subject"));
		dto.setPass(req.getParameter("pass"));
		dto.setContent(req.getParameter("content"));
		
		BoardDao dao = BoardDao.getInstance();
		int check = dao.updateArticle(dto);
		
		req.setAttribute("pageNum", new Integer(pageNum));
		req.setAttribute("check", new Integer(check));
		
		return "/WEB-INF/boardMvc/updatePro.jsp";
	}
	
}
