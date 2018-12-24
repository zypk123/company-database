/**
 * @Title: TrafficManualReportRepository.java
 * @Package cy.its.trafficSituation.repository
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月28日 下午3:31:43
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.trafficSituation.domain.model.TrafficManualReport;
import cy.its.trafficSituation.domain.repository.ITrafficManualReportRepository;
import cy.its.trafficSituation.mybatis.client.TrafficManualReportMapper;

/**
  * @ClassName: TrafficManualReportRepository
  * @Description: 人工上报交通事件基础设施层实现类
  * @author gyf guanyf@cychina.cn
  * @date 2015年10月28日 下午3:31:43
  *
  */
@Service
public class TrafficManualReportRepository implements ITrafficManualReportRepository {
	
	@Autowired
	TrafficManualReportMapper trafficManualReportMapper;
	/*
	  * <p>Title: aggregateOfId</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @throws Exception
	  * @see cy.its.com.domain.IRepository#aggregateOfId(java.lang.String)
	  */

	@Override
	public TrafficManualReport aggregateOfId(String id) throws Exception {
		return trafficManualReportMapper.selectByPrimaryKey(id);
	}

	/*
	  * <p>Title: save</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.com.domain.IRepository#save(java.lang.Object)
	  */

	@Override
	public String save(TrafficManualReport obj) {
		obj.setManualReportTraId(StringUtil.generateUUID());
		trafficManualReportMapper.insertSelective(obj);
		return obj.getManualReportTraId();
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
		return trafficManualReportMapper.deleteByPrimaryKey(id);
	}

	/*
	  * <p>Title: update</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.com.domain.IRepository#update(java.lang.Object)
	  */

	@Override
	public int update(TrafficManualReport obj) {
		return trafficManualReportMapper.updateByPrimaryKey(obj);
	}

}
