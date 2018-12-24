/**
 * @Title: SectionFlowRepository.java
 * @Package cy.its.trafficSituation.repository
 * @Description: 断面流量基础设施层实现类
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月28日 下午6:39:47
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.trafficSituation.domain.model.TrafficSectionFlow;
import cy.its.trafficSituation.domain.repository.ISectionFlowRepository;
import cy.its.trafficSituation.mybatis.client.TrafficSectionFlowMapper;

@Service
public class SectionFlowRepository implements ISectionFlowRepository {
	
	@Autowired
	TrafficSectionFlowMapper sectionFlowMapper;
	/*
	  * <p>Title: aggregateOfId</p>
	  * <p>Description: 查询</p>
	  * @param id
	  * @return
	  * @throws Exception
	  * @see cy.its.com.domain.IRepository#aggregateOfId(java.lang.String)
	  */

	@Override
	public TrafficSectionFlow aggregateOfId(String id) throws Exception {
		// TODO Auto-generated method stub
		return sectionFlowMapper.selectByPrimaryKey(id);
	}

	
	/*
	  * <p>Title: selectSctionFlows</p>
	  * <p>Description:根据条件查询断面流量列表 </p>
	  * @param map
	  * @return
	  * @see cy.its.trafficSituation.domain.repository.ISectionFlowRepository#selectSctionFlows(java.util.Map)
	  */
	
	
	@Override
	public List<TrafficSectionFlow> selectSctionFlows(Map<String, Object> map) {
		return sectionFlowMapper.selectAllSectionFlow(map);
	}
	
	@Override
	public List<TrafficSectionFlow> select2HourSectionFlow(Map<String, Object> map) {
		return sectionFlowMapper.select2HourSectionFlow(map);
	}
	
	
	/*
	  * <p>Title: save</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.com.domain.IRepository#save(java.lang.Object)
	  */

	@Override
	public String save(TrafficSectionFlow obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	  * <p>Title: delete</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.com.domain.IRepository#delete(java.lang.String)
	  */

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	  * <p>Title: update</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.com.domain.IRepository#update(java.lang.Object)
	  */

	@Override
	public int update(TrafficSectionFlow obj) {
		// TODO Auto-generated method stub
		return 0;
	}


	/*
	  * <p>Title: selectSectionFlowBySectionId</p>
	  * <p>Description: </p>
	  * @param sectionId
	  * @return
	  * @see cy.its.trafficSituation.domain.repository.ISectionFlowRepository#selectSectionFlowBySectionId(java.lang.String)
	  */
	
	
	@Override
	public TrafficSectionFlow selectSectionFlowBySectionId(String sectionId) {
		return sectionFlowMapper.selectSectionFlowBySectionId(sectionId);
	}

}
