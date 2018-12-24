package cy.its.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



/**
* @Title: ServiceFileResource.java 
* @Package cy.its.service.surveillance.util 
* @Description: 系统配置文件加载入口，初始化所有的资源
* @author lil@cychina.cn
* @date 2015年10月20日 下午2:56:00 
* @version V1.0   
 */
public class ServiceFileResource {
	
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");  
	//配置文件路径
    private String  fileName;
    
    //系统目录
    public String   globalPath;
    
    //日志名称
    public String   logName;
    /**
     * 构造器出入资源
     * @param path
     */
    public ServiceFileResource(String fileName,String logName){
    	this.fileName = fileName;
    	this.logName = logName;
    	//初始化系统资源
    	try {
			intiAllProperties();
		} catch (DocumentException | FileNotFoundException e) {
			System.out.println("解析文件出现问题，工程无法正常启动！");
			e.printStackTrace();
		}
    }
    
	/**
	 * @throws FileNotFoundException 
	 * @throws DocumentException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException  
	* @Title: intiAllProperties 
	* @Description: 解析XML文件，初始化信息 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void intiAllProperties() throws DocumentException, FileNotFoundException {
		 	SAXReader saxReader = new SAXReader();
		 	String  path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		 	File temp =  new File(path);
		 	String  jarPath  = temp.getParent()+FILE_SEPARATOR;
		 	File  file = new File(jarPath);
		 	globalPath =  file.getParent()+FILE_SEPARATOR;
		 	FileInputStream is=new FileInputStream(globalPath + fileName);
	        Document document = saxReader.read(is);
	        Element root = document.getRootElement();
	        // 获取所有子元素
	        List<Element> childList = root.elements();
	        // 获取特定名称的子元素
	        childList.stream().forEach(el->{
	        	String  className = el.attributeValue("class");
	        	String  id = el.attributeValue("id");
	        	if(!"".equals(className)){
	        		List<Element> list = el.elements();
	        		String  text1 = list.get(0).getStringValue()==null?"":list.get(0).getStringValue();
	        		String  text2 = list.get(1).getStringValue()==null?"":list.get(1).getStringValue();
	        		try {
						Class<?> c = Class.forName(className);
						Constructor c0= null;
						if(className.equals("cy.its.service.util.Log4jConfig")){
							c0=c.getDeclaredConstructor(new Class[]{String.class,String.class,String.class});
							c0.setAccessible(true);
							c0.newInstance(new Object[]{text1,globalPath+text2,logName}); 
						}else{
							c0=c.getDeclaredConstructor(new Class[]{String.class,String.class});   
							c0.setAccessible(true);
							c0.newInstance(new Object[]{text1,globalPath+text2}); 
						}
						
					} catch (Exception e) {
						System.out.println(id+"初始化失败,没找到"+id+"初始化类！");
						e.printStackTrace();
					} 
	        	}else{
	        		System.out.println(id+"初始化失败！");
	        	}
	        });
	}
}
