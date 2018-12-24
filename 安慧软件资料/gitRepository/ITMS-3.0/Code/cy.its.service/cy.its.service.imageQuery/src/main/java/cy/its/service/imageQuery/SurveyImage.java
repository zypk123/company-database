package cy.its.service.imageQuery;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import cy.its.service.common.ImageUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.imageQuery.cfg.ImageServer;
import cy.its.service.imageQuery.cfg.ServerCfg;
import cy.its.service.imageQuery.ftp.FtpAccess;
import cy.its.service.imageQuery.http.HttpAccess;
import cy.its.service.imageQuery.ice.ExceptionConverter;
import cy.its.service.imageQuery.ice.ImageProxy;

public class SurveyImage {

	private static final String F = "F:";
	private static final String FTP = "FTP:";
	private static final String HTTP = "HTTP:";
	private static final String EMPTY_URL = "%s图像查询无法处理空的图像url";
	private static final String INVALID_URL = "%s图像查询无法处理无效的图像url地址:%s";

	protected static byte[] getImage(String encryptImageUrl, String type, ImageProxy proxy) {
		if (StringUtil.isNullOrEmpty(encryptImageUrl)) {
			return createImage(String.format(EMPTY_URL, type));
		}

		String imageUrl = encryptImageUrl;
		String imageUrlUpper = imageUrl.toUpperCase();
		if(!imageUrlUpper.startsWith(F) && !imageUrlUpper.startsWith(FTP) && 
				!imageUrlUpper.startsWith(HTTP)) {
			imageUrl = ImageUtil.deEncryptUrl(encryptImageUrl);
			imageUrlUpper = imageUrl.toUpperCase();
		}

		if (imageUrlUpper.startsWith(F)) {
			return imageServer(type, proxy, imageUrl.substring(2));
		} else if (imageUrlUpper.startsWith(FTP)) {
			return imageFtp(type, imageUrl);
		} else if (imageUrlUpper.startsWith(HTTP)) {
			return imageHttp(type, imageUrl);
		}
		return createImage(String.format(INVALID_URL, type, encryptImageUrl));
	}

	/**
	 * 获取来自ftp上的图片
	 * 
	 * @param type
	 * @param imageUrl
	 * @return
	 */
	private static byte[] imageFtp(String type, String imageUrl) {
		try {
			byte[] img = FtpAccess.download(imageUrl, "", "");
			if (img == null) {
				return createImage(String.format("没有获取到FTP上的%s图片", type));
			}

			return img;
		} catch (Exception e) {
			return createImage(String.format("获取到FTP上的%s图片失败", type));
		}
	}

	/**
	 * 获取来自大文件服务的图片
	 * 
	 * @param type
	 * @param proxy
	 * @param imageUrl
	 * @return
	 */
	private static byte[] imageServer(String type, ImageProxy proxy, String imageUrl) {
		try {
			String[] arr = imageUrl.split("-");
			int index = Integer.valueOf(arr[0]);
			String deviceNbr = arr[1];
			String snapNbr = arr[2];

			ImageServer server = null;

			try {
				server = ServerCfg.getServer(deviceNbr);
				if (server == null) {
					return createImage(String.format("没有为设备%s配置图像服务器,无法获取%s图片", deviceNbr, type));
				}
			} catch (Exception e) {
				return createImage(String.format("读取设备%s的图像服务器失败,无法获取%s图片", deviceNbr, type));
			}

			if ("4".equals(server.getServerType())) {
				List<byte[]> images = proxy.queryImages(server.getServerIp(), deviceNbr, snapNbr);
				byte[] img = images != null && images.size() > index ? images.get(index) : null;
				if (img == null) {
					return createImage(String.format("没有查询到设备%s的%s图片", deviceNbr, type));
				}

				return img;
			}

		} catch (Exception e) {
			String errMsg = ExceptionConverter.convert(e);
			if (!StringUtil.isNullOrEmpty(errMsg)) {
				errMsg = ":" + errMsg;
			}
			return createImage(String.format("获取%s图片失败%s", type, errMsg));
		}

		return createImage("设备未配置正确的服务器,无法读取图片");
	}

	/**
	 * 获取HTTP上的过车图片
	 * 
	 * @param type
	 * @param url
	 * @return
	 */
	private static byte[] imageHttp(String type, String url) {
		try {
			byte[] img = HttpAccess.download(url);
			if (img == null) {
				return createImage(String.format("没有获取到HTTP的%s图片", type));
			}

			return img;
		} catch (Throwable e) {
			return createImage(String.format("获取HTTP的%s图片失败", type));
		}
	}

	public static byte[] createImage(String txt) {
		ByteArrayOutputStream outStream = null;
		try {
			int red = 0, green = 0, blue = 0;
			int width = 1024;
			int height = 768;
			int fontSize = 20;
			int y = (height / 2) + (fontSize / 2);
			int x = (width / 2) - (txt.length() * fontSize / 2);
			Font font = new Font("TimesRoman", Font.PLAIN, fontSize);
			@SuppressWarnings("restriction")
			FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font);

			y = (height / 2) - (fm.getHeight() / 2);
			x = (width / 2) - (fm.stringWidth(txt) / 2);

			// 图像buffer
			BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = buffImg.createGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width, height);

			// 创建字体
			// ImgFontByte imgFont=new ImgFontByte();

			red = new Random().nextInt(255);
			green = new Random().nextInt(255);
			blue = new Random().nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.setFont(font);
			g.drawString(txt, x, y);

			g.dispose();

			outStream = new ByteArrayOutputStream();
			ImageIO.write(buffImg, "JPG", outStream);
			return outStream.toByteArray();
		} catch (Exception e) {
			
		} finally {
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
