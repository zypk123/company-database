/**
 * @Title: ExportDto.java
 * @Package cy.its.sysCfg.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月27日 上午9:27:22
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.sysCfg.rest.dto;



import java.text.SimpleDateFormat;

import cy.its.com.dto.BaseDto;
import cy.its.com.util.ObjectMapUtils;
import cy.its.common.offLine.domain.OffLineExport;

public class ExportDto extends BaseDto {
	public ExportDto(){
		
	}
	public ExportDto(OffLineExport export){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ObjectMapUtils.parseObject(this, export);
		if(export.getUpdateTime()!=null){
			this.setUpdateTime(sdf.format(export.getUpdateTime()));
		}
		if(export.getCreateTime()!=null){
			this.setCreateTime(sdf.format(export.getCreateTime()));
		}
	}
	/**
	 * 任务ID
	 */
    private String taskId;
    
    /**
     *任务类型，
     */
    private String funcKey;
    
    /**
     *生成文件名名称
     */
    private String fileName;
    
    /**
     *存放SQL
     */
    private String exportSql;
    /**
     *存放SQL 
     */
    private String exportSql2="";
    
    /**
     *任务状态
     */
    private String status;
    
    /**
     *更新时间
     */
    private String updateTime;
    
    /**
     *任务操作人
     */
    private String loginName;
    
    /**
     *文件地址
     */
    private String fileUrl;
    
    /**
     *备注（）
     */
    private String remark;
    
    private String funcName;
    private String createTime;
    private String startTime;
    private String endTime;

	/**
	 * getter method
	 * @return the startTime
	 */
	
	public String getStartTime() {
		return startTime;
	}
	/**
	 * setter method
	 * @param startTime the startTime to set
	 */
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * getter method
	 * @return the endTime
	 */
	
	public String getEndTime() {
		return endTime;
	}
	/**
	 * setter method
	 * @param endTime the endTime to set
	 */
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * getter method
	 * @return the taskId
	 */
	
	public String getTaskId() {
		return taskId;
	}

	/**
	 * setter method
	 * @param taskId the taskId to set
	 */
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * getter method
	 * @return the funcKey
	 */
	
	public String getFuncKey() {
		return funcKey;
	}

	/**
	 * setter method
	 * @param funcKey the funcKey to set
	 */
	
	public void setFuncKey(String funcKey) {
		this.funcKey = funcKey;
	}

	/**
	 * getter method
	 * @return the fileName
	 */
	
	public String getFileName() {
		return fileName;
	}

	/**
	 * setter method
	 * @param fileName the fileName to set
	 */
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * getter method
	 * @return the exportSql
	 */
	
	public String getExportSql() {
		return exportSql;
	}

	/**
	 * setter method
	 * @param exportSql the exportSql to set
	 */
	
	public void setExportSql(String exportSql) {
		this.exportSql = exportSql;
	}

	/**
	 * getter method
	 * @return the exportSql2
	 */
	
	public String getExportSql2() {
		return exportSql2;
	}

	/**
	 * setter method
	 * @param exportSql2 the exportSql2 to set
	 */
	
	public void setExportSql2(String exportSql2) {
		this.exportSql2 = exportSql2;
	}

	/**
	 * getter method
	 * @return the status
	 */
	
	public String getStatus() {
		return status;
	}

	/**
	 * setter method
	 * @param status the status to set
	 */
	
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * getter method
	 * @return the updateTime
	 */
	
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * setter method
	 * @param updateTime the updateTime to set
	 */
	
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * getter method
	 * @return the loginName
	 */
	
	public String getLoginName() {
		return loginName;
	}

	/**
	 * setter method
	 * @param loginName the loginName to set
	 */
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * getter method
	 * @return the fileUrl
	 */
	
	public String getFileUrl() {
		return fileUrl;
	}

	/**
	 * setter method
	 * @param fileUrl the fileUrl to set
	 */
	
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	/**
	 * getter method
	 * @return the remark
	 */
	
	public String getRemark() {
		return remark;
	}

	/**
	 * setter method
	 * @param remark the remark to set
	 */
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * getter method
	 * @return the funcName
	 */
	
	public String getFuncName() {
		return funcName;
	}
	/**
	 * setter method
	 * @param funcName the funcName to set
	 */
	
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	/**
	 * getter method
	 * @return the createTime
	 */
	
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * setter method
	 * @param createTime the createTime to set
	 */
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
    
}
