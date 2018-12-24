/**
 * @Title: TrafficManualReportService.java
 * @Package cy.its.trafficSituation.domain.service.impl
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月28日 下午4:02:22
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.trafficSituation.domain.model.TrafficManualReport;
import cy.its.trafficSituation.domain.repository.ITrafficManualReportRepository;
import cy.its.trafficSituation.domain.service.ITrafficManualReportService;

/**
  * @ClassName: TrafficManualReportService
  * @Description: 人工上报交通事件领域实现层
  * @author gyf guanyf@cychina.cn
  * @date 2015年10月28日 下午4:02:22
  *
  */

@Service
public class TrafficManualReportService implements ITrafficManualReportService {
	@Autowired
	ITrafficManualReportRepository trafficManualReportRepository;

	/*
	  * <p>Title: selectById</p>
	  * <p>Description:查询 </p>
	  * @param id
	  * @return
	  * @see cy.its.trafficSituation.domain.service.ITrafficManualReportService#selectById(java.lang.String)
	  */

	@Override
	public TrafficManualReport selectById(String id) throws Exception {
		return trafficManualReportRepository.aggregateOfId(id);
	}

	/*
	  * <p>Title: save</p>
	  * <p>Description:新增 </p>
	  * @param trafficManualReport
	  * @return
	  * @see cy.its.trafficSituation.domain.service.ITrafficManualReportService#save(cy.its.trafficSituation.domain.model.TrafficManualReport)
	  */

	@Override
	public String save(TrafficManualReport trafficManualReport) {
		return trafficManualReportRepository.save(trafficManualReport);
	}

	/*
	  * <p>Title: delete</p>
	  * <p>Description:删除 </p>
	  * @param id
	  * @return
	  * @see cy.its.trafficSituation.domain.service.ITrafficManualReportService#delete(java.lang.String)
	  */

	@Override
	public int delete(String id) {
		return trafficManualReportRepository.delete(id);
	}

	/*
	  * <p>Title: update</p>
	  * <p>Description: 修改</p>
	  * @param trafficManualReport
	  * @return
	  * @see cy.its.trafficSituation.domain.service.ITrafficManualReportService#update(cy.its.trafficSituation.domain.model.TrafficManualReport)
	  */

	@Override
	public int update(TrafficManualReport trafficManualReport) {
		return trafficManualReportRepository.update(trafficManualReport);
	}

}
