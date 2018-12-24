package cy.its.vehTrack.domain.repository;

import java.util.List;

import cy.its.vehTrack.domain.criteria.PassCriteria;
import cy.its.vehTrack.domain.model.PassInfo;

public interface IPassRepository {
	
	/**
	 * 查找过车信息
	 * @param passCriteria 过车查询条件封装类
	 * @return
	 */
	public List<PassInfo> findPassInfo(PassCriteria passCriteria) throws Exception;
}
