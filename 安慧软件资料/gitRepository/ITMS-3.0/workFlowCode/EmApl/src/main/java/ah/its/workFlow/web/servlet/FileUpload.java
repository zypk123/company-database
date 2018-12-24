package ah.its.workFlow.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

@WebServlet("/fileUpload")
public class FileUpload extends HttpServlet {
	
		private static final long serialVersionUID = -8244073279641189889L;
		
	    private final Logger log = Logger.getLogger(FileUpload.class.getName());

	    
	    private String filePath="fileDirs";    // 文件存放目录
	    private String fileDirs=filePath;
	    
	    public void init(ServletConfig config) throws ServletException  
	    {  
	        super.init(config);  
	        // 从配置文件中获得初始化参数  
	        String contextPath = getServletContext().getRealPath("");  
	        filePath = contextPath+File.separator+filePath;
	        File  file = new File(filePath);
	        if(!file.exists()){
	        	file.mkdir();
	        }
	        String  dayFile = new SimpleDateFormat("yyyyMMdd").format(new Date());
	        		
	        filePath+="/"+ dayFile;
	        fileDirs+="/"+ dayFile;
	        file = new File(filePath);
	        if(!file.exists()){
	        	file.mkdir();
	        }
	    }  
	    
	    // doPost  
	    public void doPost(HttpServletRequest req, HttpServletResponse res)  
	        throws IOException, ServletException  
	    {  
	    	Map  map = new HashMap();
			// 设置上传文件的大小，超过这个大小 将抛出IOException异常，默认大小是200M。
			int inmaxPostSize = 200 * 1024 * 1024;
			MultipartRequest multirequest = null;
			String fileNames = "";
			// 上传文件重命名策略
			RenamePolicyCos myRenamePolicyCos = new RenamePolicyCos();
			// MultipartRequest()有8种构造函数！
			multirequest = new MultipartRequest(req, filePath, inmaxPostSize, "UTF-8",
					myRenamePolicyCos); // GBK中文编码模式上传文件
			Enumeration<String> filedFileNames = multirequest.getFileNames();
			String filedName = null;
			if (null != filedFileNames) {
				while (filedFileNames.hasMoreElements()) {
					filedName = filedFileNames.nextElement();// 文件文本框的名称
					File uploadFile = multirequest.getFile(filedName);
					if (null != uploadFile && uploadFile.length() > 0) {
						log.info("上传文件名为："+uploadFile.getName());
						fileNames += fileDirs+"/"+uploadFile.getName()+",";
					}
				}
			}
			 if(!"".equals(fileNames)){
            	 fileNames = fileNames.substring(0, fileNames.length()-1);
            	 map.put("status", "1"); 
            	 map.put("fileUrl",fileNames);
            }else{
            	 map.put("status", "0"); 
            	 map.put("fileUrl",fileNames); 
            }
            returnAjax(res,map);
	    }// end doPost()
		// doGet  
	    public void doGet(HttpServletRequest req, HttpServletResponse res)  
	        throws IOException, ServletException  
	    {  
	        doPost(req, res);  
	    } 
	    
	    public  void  returnAjax(HttpServletResponse response,Map  map){
		   	 response.setContentType("application/x-json;charset=UTF-8");
		   	 PrintWriter out;
			try {
				out = response.getWriter();
			   	out.write(JSONObject.toJSONString(map));//注意这里向jsp输出的流，在script中的截获方法
			   	out.flush();  
			   	out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
class RenamePolicyCos implements FileRenamePolicy {
	
	public File rename(File uploadFile) {
		String newName = getNewFileName(uploadFile.getName());
		File renameFile = new File(uploadFile.getParent(), newName);
		return renameFile;
	}

	private String getNewFileName(String fileName) {
		StringBuffer newName = new StringBuffer();
		if (null != fileName && !"".equals(fileName)) {
			String type = "";
			String name = "";
			if (fileName.indexOf(".") != -1) {
				type = fileName.substring(fileName.indexOf("."));
				name = fileName.substring(0, fileName.indexOf("."));
			}else{
				name = fileName;
			}
			newName.append(name);
			newName.append(getSuffix());
			newName.append(type);
		}
		return newName.toString();
	}
	
	private String getSuffix(){
		StringBuffer suffix = new StringBuffer("_");
		String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		suffix.append(now);
		Random random = new Random();
		String randomValue = String.valueOf(random.nextInt(1000));
		suffix.append(randomValue);
		return suffix.toString();
	}
}
