package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import boardMvc.BoardDao;
import boardMvc.BoardDto;

public class WriteProAction implements CommandAction{

	String[] arr = new String[10];
	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		printInfo(req, resp);
		BoardDto dto = new BoardDto();
		dto.setNum(Integer.parseInt(arr[0]));
		dto.setRef(Integer.parseInt(arr[1]));
		dto.setStep(Integer.parseInt(arr[2]));
		dto.setDepth(Integer.parseInt(arr[3]));
		dto.setWriter(arr[4]);
		dto.setEmail(arr[5]);
		dto.setSubject(arr[6]);
		dto.setContent(arr[7]);
		dto.setPass(arr[8]);
		dto.setFilename(arr[9]);
		dto.setRegdate(new Timestamp(System.currentTimeMillis()));
		dto.setIp(req.getRemoteAddr());
		BoardDao dao = BoardDao.getInstance();
		dao.insertArticle(dto);
		
		return "/boardMvc/writePro.jsp";
	}
	
	private void printInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Collection<Part> parts= req.getParts();
		int i = 0;
		for(Part part : parts) {
			String contentType = part.getContentType();
			if(contentType == null) {
				arr[i] = req.getParameter(part.getName());
				part.delete();
			}else if(contentType.startsWith("application/")) {
				long size = part.getSize();
				String filename = getFileName(part);
				arr[i] = filename;
				if(size>0) {
					part.write("D:\\반응형웹개발자\\upload\\"+filename);
					part.delete();
				}
			}
			i++;
		}
	}
	
	
	private String getFileName(Part part) throws UnsupportedEncodingException{
		for(String cd : part.getHeader("Content-Disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				String tmp = cd.substring(cd.indexOf('=')+1).trim().replace("\"", "");
				tmp = tmp.substring(tmp.indexOf(":")+1);
				return tmp;
			}
		}
		return null;
	}
	
}
