package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		int num = 0, ref = 1, step = 0, depth= 0;
		try {
			if(req.getParameter("num") != null) {
				System.out.println(num);
				num = Integer.parseInt(req.getParameter("num"));
				ref = Integer.parseInt(req.getParameter("ref"));
				step = Integer.parseInt(req.getParameter("step"));
				depth = Integer.parseInt(req.getParameter("depth"));
			}
		}catch(Exception e) {e.printStackTrace();}
		
		//해당 뷰에서 사용할 속성
		req.setAttribute("num", new Integer(num));
		req.setAttribute("ref", new Integer(ref));
		req.setAttribute("step", new Integer(step));
		req.setAttribute("depth", new Integer(depth));
		
		return "/WEB-INF/boardMvc/writeForm.jsp";
	}
	
}
