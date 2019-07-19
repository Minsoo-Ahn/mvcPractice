package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardMvc.BoardDao;
import boardMvc.BoardDto;

public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		BoardDao dao= BoardDao.getInstance();
		BoardDto dto = dao.updateGetArticle(num);
		
		//뷰에서 사용할 속성
		req.setAttribute("pageNum", new Integer(pageNum));
		req.setAttribute("article", dto);
		return "/WEB-INF/boardMvc/updateForm.jsp";
	}

}
