package cy.its.platform.common.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
* @Title: JacksonXmlUtil.java 
* @Package com.cy.utils 
* @Description: 工具类 XML与JSON互换 
* @author lil@cychina.cn
* @date 2015年8月19日 下午3:29:41 
* @version V1.0   
 */
public class JacksonXmlUtil {
	
	/** 
	* @Title: convertXMLtoMap 
	* @Description: 把XML文件转化为MAP 
	* @param @param path
	* @param @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	*/
	public static Map convertXMLtoMap(String path){
		SAXReader reader = new SAXReader();               
	    Document document=null;
		try {
			document = reader.read(new File(path));
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} 
		Map map  = new HashMap();
		File  file  = new File(path);
		Element   el  = document.getRootElement();
		Iterator<Element> it = el.elementIterator();
        while (it.hasNext()) {  
            // 获取某个子节点对象  
            Element e = it.next();  
            // 对子节点进行遍历  
            map.put(e.attributeValue("id"), e.attributeValue("sql"));
        }  
		return map;
	}
}
