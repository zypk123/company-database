package cy.its.service.domain;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cy.its.platform.common.utils.ZipUtil;
import cy.its.service.util.DBUtil;

/**
 * @Title: OffLineTask.java
 * @Package cy.its.service.domain
 * @Description: 离线下载任务类
 * @author lil@cychina.cn
 * @date 2015年11月17日 下午8:24:39
 * @version V1.0
 */
public abstract class OffLineTask implements Runnable {

	// 系统操作符
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public static Logger log = Logger.getLogger(OffLineTask.class);
	/**
	 * 任务号
	 */
	String taskId;
	/**
	 * sql
	 */
	String sql;

	/**
	 * fileName
	 */
	String fileName;

	/**
	 * loginName
	 */
	String loginName;

	// ZIP文件名称
	private File zipFileName;
	// 文件生成时候路径
	public String filePath;

	public OffLineTask(String taskId, String sql, String fileName, String loginName) {
		this.fileName = fileName;
		this.taskId = taskId;
		this.sql = sql;
		this.loginName = loginName;

		// 确定下载路径由配置文件读取
		String fileRoot = OffLineCache.getFilePath();
		filePath = fileRoot + FILE_SEPARATOR + df.format(new Date());
		// 加入日期
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 加入用户信息
		filePath = filePath + FILE_SEPARATOR + loginName;
		File userFile = new File(filePath);
		if (!userFile.exists()) {
			userFile.mkdirs();
		}
		// 压缩文件名称
		zipFileName = new File(filePath + FILE_SEPARATOR + fileName + System.currentTimeMillis() + ".zip");
	}

	/**
	 * 下载实现类
	 */
	@Override
	public void run() {
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		// 获取数据源信息
		try {
			conn = DBUtil.openConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			// 生成文件
			createFile(rs, filePath);
			// 更新状态
			String fileUrl = zipFileName.getPath().replace("\\", "/");
			fileUrl = fileUrl.replace(OffLineCache.getFilePath(), "");
			fileUrl = "http://" + OffLineCache.getHttpIp() + fileUrl;
			updateTask(taskId, "4", fileUrl);
			// 关闭连接
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			log.error(e.getStackTrace());

		} finally {
			try {
				if (stmt != null) {
					stmt.close();// 出现异常关闭链接
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.error(e.getStackTrace());
			}
		}

	}

	/**
	 * @Title: createFile @Description: 根据获取的数据，生成文件，然后把文件添加到要锁文件中 @param @param
	 * rs @param @param filePath 设定文件 @return void 返回类型 @throws
	 */
	public abstract void createFile(ResultSet rs, String filePath);

	/**
	 * @Title: zipUtil @Description: 可传入多个文件进行压缩 @param @param
	 * file @param @return 设定文件 @return boolean 返回类型 @throws
	 */
	public boolean zipFiles(List<File> list) {
		try {
			ZipUtil.zipFiles(list, zipFileName, true);
			for (File file : list) {
				file.delete();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * @Title: updateTask @Description: 更新任务的状态 @param @param
	 * taskId @param @param string 设定文件 @return void 返回类型 @throws
	 */
	private void updateTask(String taskId, String status, String fileUrl) {
		String updateSql = "update T_SYS_EXPORT set  status = '" + status + "',file_url='" + fileUrl
				+ "' where task_id= '" + taskId + "'";
		DBUtil.updateData(updateSql);
	}
}
