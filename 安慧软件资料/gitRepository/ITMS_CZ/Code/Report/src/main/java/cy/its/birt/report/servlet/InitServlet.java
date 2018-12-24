package cy.its.birt.report.servlet;


import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class InitServlet  extends  HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7494197526457091687L;
	
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");  
	
	private String globalPath ="";
	
	 @Override 
	 public void init(ServletConfig config) throws ServletException {  
		super.init(config);  
        String prefix = config.getServletContext().getRealPath("/");  
        if(FILE_SEPARATOR.equals("\\")) {  
            // 获取内容服务器配置文件的路径  
        	globalPath = prefix + "WEB-INF\\logPath.properties"; 
        } else if(FILE_SEPARATOR.equals("/")) {  
        	globalPath = prefix + "WEB-INF/logPath.properties";  
        }  
        Properties  prop  = new Properties();
        FileInputStream fis;
		try {
			fis = new FileInputStream(globalPath);
			prop.load(fis);
			GlobalProerty.loPath =prop.getProperty("logPath");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
  }
