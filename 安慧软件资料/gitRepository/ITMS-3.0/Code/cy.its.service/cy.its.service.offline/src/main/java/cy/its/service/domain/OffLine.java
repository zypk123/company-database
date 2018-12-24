package cy.its.service.domain;

import java.util.Date;

/**
* @Title: Section.java 
* @Package cy.its.service.section.domain 
* @Description: 区间对象
* @author lil@cychina.cn
* @date 2015年11月4日 下午2:01:17 
* @version V1.0   
 */
public class OffLine {
	/**
	 * 唯一标识
	 */
	private  String  taskId;
	/**
	 *对应功能唯一码
	 */
	private  String  funcKey;
	
	/**
	 *导出文件名称
	 */
	private  String  fileName;
	
	/**
	 *导出文件sql
	 */
	private  String  exportSql;
	
	
	/**
	 *导出文件备用SQL，如果SQL过长可以切割成两个，字段加上可暂时不考虑
	 */
	private  String  exportSql2;
	
	
	/**
	 *导出状态
	 */
	private  String  status;
	
	/**
	 *更新时间
	 */
	private  Date  updateTime;
	
	/**
	 *操作人
	 */
	private  String  loginName;


	public String getFuncKey() {
		return funcKey;
	}

	public void setFuncKey(String funcKey) {
		this.funcKey = funcKey;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExportSql() {
		return exportSql;
	}

	public void setExportSql(String exportSql) {
		this.exportSql = exportSql;
	}

	public String getExportSql2() {
		return exportSql2;
	}

	public void setExportSql2(String exportSql2) {
		this.exportSql2 = exportSql2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
    
}
