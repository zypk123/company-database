package cy.its.vehTrack.domain.service;

import java.util.List;

import cy.its.vehTrack.domain.criteria.PassCriteria;
import cy.its.vehTrack.domain.model.PassInfo;

public interface IPassService {
	
	/**
	 * 查找过车信息
	 * @param passCriteria 过车查询条件封装类
	 * @return
	 */
	public List<PassInfo> findPassInfo(PassCriteria passCriteria) throws Exception ;
}
