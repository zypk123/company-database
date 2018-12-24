package cy.its.service.export;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import javax.imageio.ImageIO;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

import cy.its.service.common.StringUtil;
import cy.its.service.domain.OffLineTask;
import cy.its.service.imageQuery.PassImage;

/**
 * @Title: XmlExportTask.java
 * @Package cy.its.service.export
 * @Description:每一条记录生成一个XML和图片，然后把记录压缩到zip中
 * @author lil@cychina.cn
 * @date 2015年11月19日 下午1:40:49
 * @version V1.0
 */
public class XmlExportTask extends OffLineTask {

	private static String fileseparator = File.separator;

	private static String newLine = System.getProperty("line.separator");

	private static VioXmlExportTemplate _template = new VioXmlExportTemplate();

	public XmlExportTask(String taskId, String sql, String fileName, String loginName) {

		super(taskId, sql, fileName, loginName);
		try {
			LoadTemplate();
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据传入对象生成XML和图片
	 * 
	 * @param vl
	 *            违法信息
	 * @param exportDirectory
	 */
	@Override
	public void createFile(ResultSet rs, String filePath) {
		List<File> list = new ArrayList<File>();
		int s = 0;
		try {
			while (rs.next()) {
				// 获取到JSON数据
				JSONObject jsonObject = getJSONObject(rs);
				// deviceSysNbr
				filePath = filePath.replace("/", fileseparator);

				list.add(createXmlFile(jsonObject));
				String imageUrl = jsonObject.getString("IMAGE");
				if (!StringUtils.isEmpty(imageUrl)) {
					list.addAll(createImageFile(jsonObject, imageUrl));
				}
				s++;
				if (s % 100 == 0) {
					zipFiles(list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		zipFiles(list);
	}

	/**
	 * 创建xml文件
	 * 
	 * @param jsonObject
	 * @param parentPath
	 * @return
	 * @throws IOException
	 */
	private File createXmlFile(JSONObject jsonObject) throws IOException {
		DocumentFactory factory = new DocumentFactory();
		Document document = factory.createDocument();
		Element root = null;
		if (!StringUtil.isNullOrEmpty(_template.getWarpNodeName())) {
			root = document.addElement(_template.getWarpNodeName());
		} else {
			root = document.addElement("violation");
		}
		// 文件名
		String documentName = filePath + fileseparator + getBaseFileName(jsonObject);

		// 填充xml文档内容
		if (_template.getFieldDescriptions() != null && _template.getFieldDescriptions().size() > 0) {
			for (FieldDescription field : _template.getFieldDescriptions()) {

				if (field.getBindField() != null && field.getBindField().contains("[")) {
					String field1 = field.getBindField().substring(1, field.getBindField().length() - 1);
					Element ele = root.addElement(field.getExportField());
					String fieldValue = jsonObject.getString(field1);
					if (fieldValue != null) {
						ele.setText(fieldValue + newLine);
					} else {
						ele.setText("" + newLine);
					}
				} else {
					Element ele = root.addElement(field.getExportField());
					ele.setText(field.getBindField() + newLine);
				}
				// jsonObject.getString()
			}
		}

		FileOutputStream fw = new FileOutputStream(documentName + ".xml");
		OutputFormat format = OutputFormat.createPrettyPrint();// 缩减型格式
		format.setEncoding("UTF-8");// 设置文件内部文字的编码
		format.setNewLineAfterDeclaration(true);

		XMLWriter writer = new XMLWriter(fw, format);
		writer.write(document);
		fw.close();
		File file = new File(documentName + ".xml");
		return file;
	}

	/**
	 * 创建image文件
	 * 
	 * @param imageFileName
	 * @param imgData
	 * @return
	 * @throws IOException
	 */
	private List<File> createImageFile(JSONObject jsonObject, String imageUrl) throws IOException {
		List<File> list = new ArrayList<>();
		String[] urls = imageUrl.split(";");
		int i = 1;
		for (String tmp : urls) {
			if (!StringUtils.isEmpty(tmp)) {
				// 图片名称
				String imageFileName = filePath + fileseparator + fileseparator + getBaseFileName(jsonObject) + "_"
						+ i++ + ".jpg";
				byte[] imgData = PassImage.query(tmp);
				ByteArrayInputStream bais = new ByteArrayInputStream(imgData);
				BufferedImage bi1 = ImageIO.read(bais);
				File w2 = new File(imageFileName);// 可以是jpg,png,gif格式
				ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动
				list.add(w2);
			}
		}
		return list;
	}

	/**
	 * 获取基础文件名
	 * 
	 * @param jsonObject
	 * @return
	 */
	private String getBaseFileName(JSONObject jsonObject) {
		String baseFileFormat = jsonObject.getString("DEVICE_SYS_NBR") + "_" + jsonObject.getString("SNAP_NBR");
		if (!StringUtil.isNullOrEmpty(_template.getBaseFileFormat())) {
			baseFileFormat = _template.getBaseFileFormat();
			java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\[.*?\\]");//
			Matcher matcher = pattern.matcher(_template.getBaseFileFormat());
			List<String> nameFields = new ArrayList<>();
			while (matcher.find()) {
				nameFields.add(matcher.group());
			}
			if (nameFields.size() > 0) {
				for (String nameField : nameFields) {
					String field = nameField.substring(1, nameField.length() - 1);// 取[***]中间的***
					String fieldVaue = jsonObject.getString(field);
					if (fieldVaue != null) {
						baseFileFormat = baseFileFormat.replace(nameField, fieldVaue);
					}
				}
			}
		}
		return baseFileFormat;
	}

	private void LoadTemplate() throws FileNotFoundException, DocumentException {
		SAXReader saxReader = new SAXReader();
		java.io.InputStream is = this.getClass().getResourceAsStream("/config/template/VioTxtExportTemplate.xml");
		Document document = saxReader.read(is);
		Element eleRoot = document.getRootElement();

		_template.setBaseFileFormat(eleRoot.elementText("BaseFileFormat"));
		_template.setEncoding(eleRoot.elementText("Encoding"));
		_template.setWarpNodeName(eleRoot.elementText("WarpNodeName"));
		_template.setTxtFileSuffix(eleRoot.elementText("TxtFileSuffix"));
		_template.setTxtFileMulti(eleRoot.elementText("TxtFileMulti"));
		_template.setImageStartIndex(eleRoot.elementText("ImageStartIndex"));
		_template.setImageCompose(eleRoot.elementText("ImageCompose"));
		Element fieldsEle = eleRoot.element("Fields");
		if (fieldsEle != null) {
			List<Element> elements = fieldsEle.elements("Field");
			List<FieldDescription> fieldDescriptions = new ArrayList<>();
			elements.stream().forEach(el -> {
				FieldDescription item = new FieldDescription();
				org.dom4j.Attribute attr = el.attribute("Description");
				if (attr != null) {
					item.setDescription(attr.getText());
				}
				attr = el.attribute("BindField");
				if (attr != null) {
					item.setBindField(attr.getText());
				}
				attr = el.attribute("ExportField");
				if (attr != null) {
					item.setExportField(attr.getText());
				}
				attr = el.attribute("FormatString");
				if (attr != null) {
					item.setFormatString(attr.getText());
				}
				fieldDescriptions.add(item);
			});
			_template.setFieldDescriptions(fieldDescriptions);
		}
	}

	/**
	 * @Title: getJSONObject @Description: 获取到一JSON对象 @param @param
	 *         rs @param @return 设定文件 @return JSONObject 返回类型 @throws
	 */
	private static JSONObject getJSONObject(ResultSet rs) {
		JSONObject jsonObject = new JSONObject();
		try {
			int length = rs.getMetaData().getColumnCount();
			for (int i = 0; i < length; i++) {
				String key = rs.getMetaData().getColumnName(i + 1);
				String classType = rs.getMetaData().getColumnClassName(i + 1);
				jsonObject.put(key, Class.forName(classType).cast(rs.getObject(i + 1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

}
