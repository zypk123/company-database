package cy.its.violation.rest.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.springframework.util.StringUtils;

import cy.its.violation.domain.model.violation.Violation;
import cy.its.violation.rest.action.impl.FileToZip;

public class XmlExportGen {

	private static String fileseparator = File.separator == "/" ? "/" : "\\";

	/**
	 * 根据传入对象生成XML和图片
	 * 
	 * @param vl
	 *            违法信息
	 * 
	 * @param exportDirectory
	 */
	public static void createXmlByViolation(Violation vl, String exportDirectory) {
		String documentName = exportDirectory + fileseparator + vl.getIdentityId() + ".xml";
		DocumentFactory factory = new DocumentFactory();
		Document document = factory.createDocument();
		Element root = document.addElement("violation");
		// 建立根节点
		Element images = root.addElement("images");
		String url = "http://localhost:8080/ControlPlatform/html/src/data/violationMgr/img/region_0.jpg,http://localhost:8080/ControlPlatform/html/src/data/violationMgr/img/region_1.jpg";// vl.image;
		String[] urls = url.split(",");
		int i = 1;
		for (String tmp : urls) {
			if (!StringUtils.isEmpty(tmp)) {
				Element image1 = images.addElement("image");
				String imageName = tmp.substring(tmp.lastIndexOf("/") + 1, tmp.length());
				image1.addAttribute("url", vl.getIdentityId() + "-" + i + imageName);// 属性名name
				// 把图片移入文件夹
				try {
					URL httpurl = new URL(tmp);
					HttpURLConnection conn = (HttpURLConnection) httpurl.openConnection();
					// 设置请求方式为"GET"
					conn.setRequestMethod("GET");
					// 超时响应时间为5秒
					conn.setConnectTimeout(5 * 1000);
					// 通过输入流获取图片数据
					InputStream inStream = conn.getInputStream();
					// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
					byte[] data = FileToZip.readInputStream(inStream);
					// new一个文件对象用来保存图片，默认保存当前工程根目录
					File imageFile = new File(exportDirectory + fileseparator + vl.getIdentityId() + "-" + imageName);
					// 创建输出流
					FileOutputStream outStream = new FileOutputStream(imageFile);
					// 写入数据
					outStream.write(data);
					// 关闭输出流
					outStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// 建立一个xml文件，将Dom4j树写入xml文件
		try {
			FileWriter fw = new FileWriter(documentName);
			XMLWriter writer = new XMLWriter(fw);
			writer.write(document);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
