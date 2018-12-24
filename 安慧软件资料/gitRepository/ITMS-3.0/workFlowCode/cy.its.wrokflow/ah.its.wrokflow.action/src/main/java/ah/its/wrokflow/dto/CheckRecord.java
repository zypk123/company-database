package ah.its.wrokflow.dto;

import java.util.Date;

/**
* @Description: 申请单，审批记录
* @author lil@cychina.cn
* @version V1.0   
 */
public class CheckRecord {
	
	/**
	 * @serialField:审批ID
	 */
	private String  id;
	
	/**
	 * @serialField:审批结果
	 */
	private String  result;
	
	/**
	 * @serialField:审批人
	 */
	private String  checkUser;
	
	/**
	 * @serialField:审批意见
	 */
	private String  checkOpinion;
	
	/**
	 * @serialField:审批时间
	 */
	private Date  checkTime;

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public String getCheckOpinion() {
		return checkOpinion;
	}

	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}
}
