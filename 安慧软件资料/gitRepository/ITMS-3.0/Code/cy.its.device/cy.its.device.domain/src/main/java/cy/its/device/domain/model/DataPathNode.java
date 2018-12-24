package cy.its.device.domain.model;

public class DataPathNode {
		
	/**
	 * 节点key  取值:
	 * PreSurveyServer
	 * SurveyServer   
	 * AftSurveyServer
	 * ICE2MQ         
	 * PreServer      
	 * AftServer      
	 * UploadBegin    
	 * UploadEnd
	 * ITMS 
	 */
	private String nodeKey;

	/**
	 * 节点名称,取值如下：
	 * 前置监控服务器 
	 * 监控服务器     
	 * 后置监控服务器 
	 * ICE2MQ         
	 * 前置机         
	 * 后置机         
	 * 上传开始       
	 * 上传结束
	 * 管控平台
	 * 节点key和节点名称的对应关系如下:
	 * 节点key          节点名称
	 * PreSurveyServer 前置监控服务器 			    
	 * SurveyServer    监控服务器 
	 * AftSurveyServer 后置监控服务器 
	 * ICE2MQ          ICE2MQ 
	 * PreServer       前置机 
	 * AftServer       后置机 
	 * UploadBegin     上传开始 
	 * UploadEnd       上传结束
	 * ITMS            管控平台
	 */
	private String nodeName;

	/**
	 * 当前节点距离上个节点的传输时差
	 */
	private Integer delayToLastNode;
	
	/**
	 * 当前节点距离最开始节点的传输时差
	 */
	private Integer totalDelay;
	
	public DataPathNode(String nodeKey, String nodeName, Short delayToLastNode, Short totalDelay) {
		this(nodeKey, nodeName, delayToLastNode.intValue(), totalDelay.intValue());
	}

	public DataPathNode(String nodeKey, String nodeName, Integer delayToLastNode, Integer totalDelay) {
		this.nodeKey = nodeKey;
		this.nodeName = nodeName;
		this.delayToLastNode = delayToLastNode;
		this.totalDelay = totalDelay;
	}

	public String getNodeKey() {
		return nodeKey;
	}

	public void setNodeKey(String nodeKey) {
		this.nodeKey = nodeKey;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Integer getDelayToLastNode() {
		return delayToLastNode;
	}

	public void setDelayToLastNode(Integer delayToLastNode) {
		this.delayToLastNode = delayToLastNode;
	}

	public Integer getTotalDelay() {
		return totalDelay;
	}

	public void setTotalDelay(Integer totalDelay) {
		this.totalDelay = totalDelay;
	}
	
	
}
