package cy.its.road.domain.service;

import java.util.List;

import cy.its.road.domain.criteria.InterflowCriteria;
import cy.its.road.domain.model.interflow.Interflow;

public interface IInterflowService {

	/**
	 * 查询符合条件的公路互通列表
	 * 
	 * @param interflowCriteria
	 *            查询条件
	 * @return 公路互通列表
	 */
	public List<Interflow> findInterflows(InterflowCriteria interflowCriteria);

	/**
	 * 查询指定的公路互通
	 * 
	 * @param id
	 *            公路互通唯一标识
	 * @return 公路互通详细
	 * @throws Exception
	 */
	public Interflow interflowOfId(String id) throws Exception;

	/**
	 * 创建新的公路互通
	 * 
	 * @param interflow
	 *            公路互通
	 */
	public void saveInterflow(Interflow interflow);

	/**
	 * 更新公路互通
	 * 
	 * @param interflow
	 *            公路互通
	 */
	public void updateInterflow(Interflow interflow);

	/**
	 * 删除指定的公路互通
	 * 
	 * @param interflowId
	 *            公路互通唯一标识
	 */
	public void deleteInterflow(String interflowId);

}