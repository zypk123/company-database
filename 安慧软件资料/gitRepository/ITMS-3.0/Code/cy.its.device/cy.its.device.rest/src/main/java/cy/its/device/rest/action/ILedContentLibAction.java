package cy.its.device.rest.action;

import java.util.Map;

import cy.its.device.rest.dto.LedContentLibDto;

public interface ILedContentLibAction {
	/**
	 * 添加内容库
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public int addLedContentLib(LedContentLibDto form) throws Exception;
	/**
	 * 删除内容库（支持批量删除）
	 * @param contentId
	 * @return
	 * @throws Exception
	 */
	public int removeLedContentLib(String contentId) throws Exception;
	/**
	 * 修改内容库
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public int updateLedContentLib(LedContentLibDto form) throws Exception; 
	/**
	 * 查询内容库列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findLedContentLibList(LedContentLibDto form) throws Exception;
}
