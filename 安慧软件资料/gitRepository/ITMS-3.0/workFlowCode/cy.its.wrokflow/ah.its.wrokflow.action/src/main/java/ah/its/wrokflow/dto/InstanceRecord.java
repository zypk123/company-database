package ah.its.wrokflow.dto;

import java.util.Date;

/**
* @Title: InstanceRecord.java 
* @Package cy.its.wrokflow.approve.dto 
* @Description: 流程实例信息
* @author lil@cychina.cn
* @date 2016年3月28日 下午8:12:31 
* @version V1.0   
 */
public class InstanceRecord {
	
    /**
     * @Description:实例ID
     */
    private String   recordId;
    
    /**
     * @Description:流程名称
     */
    private String   ProcessDefinitionName;
    
    /**
     * @Description:申请人
     */
    private String   approveUsr;
    
    /**
     * @Description:申请内容简介
     */
    private String   approveContent;
    
    /**
     * @Description:申请时间
     */
    private Date   approveTime;
    
    
    /**
     * @Description:流程耗时（天）
     */
    private int   consumeTime;


	public String getRecordId() {
		return recordId;
	}


	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}


	public String getProcessDefinitionName() {
		return ProcessDefinitionName;
	}


	public void setProcessDefinitionName(String processDefinitionName) {
		ProcessDefinitionName = processDefinitionName;
	}


	public String getApproveUsr() {
		return approveUsr;
	}


	public void setApproveUsr(String approveUsr) {
		this.approveUsr = approveUsr;
	}


	public String getApproveContent() {
		return approveContent;
	}


	public void setApproveContent(String approveContent) {
		this.approveContent = approveContent;
	}


	public Date getApproveTime() {
		return approveTime;
	}


	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}


	public int getConsumeTime() {
		return consumeTime;
	}


	public void setConsumeTime(int consumeTime) {
		this.consumeTime = consumeTime;
	}
    
}
