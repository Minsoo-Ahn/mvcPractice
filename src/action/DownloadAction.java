package action;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		String fileName = req.getParameter("filename");
		String savePath= "D:\\반응형웹개발자\\upload";
		ServletContext context = req.getSession().getServletContext();
		String filePath = savePath+"\\"+fileName;
		File f = new File(filePath);
		byte[] b = new byte[50*1024*1024];
		FileInputStream fis = new FileInputStream(f);
		String type = context.getMimeType(filePath);
		System.out.println("유형 : "+type);
		
		if(type==null){
			type = "application.octec-stream";
		}
		
		resp.setContentType(type);
		String A = new String(fileName.getBytes("euc-kr"),"8859_1");
		String B = "utf-8";
		String encoding = URLEncoder.encode(A,B);
		String AA = "Content-Disposition";
		String BB = "attachment; filename="+encoding;
		resp.setHeader(AA, BB);
		
		ServletOutputStream out2 = resp.getOutputStream();
		
		int numRead = 0;
		
		while((numRead=fis.read(b,0,b.length))!=-1){
			out2.write(b,0,numRead);
		}
		
		out2.flush();
		out2.close();
		fis.close();
		return null;
	}
	
}
