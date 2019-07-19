package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardMvc.BoardDao;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		req.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		String pass = req.getParameter("pass");
		
		BoardDao dao = BoardDao.getInstance();
		int check = dao.deleteArticle(num, pass);
		
		req.setAttribute("pageNum", new Integer(pageNum));
		req.setAttribute("check", new Integer(check));
		return "/WEB-INF/boardMvc/deletePro.jsp";
		
	}
	
}
