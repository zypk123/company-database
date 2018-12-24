/**
 * @Title: TrafficStateRepository.java
 * @Package cy.its.trafficSituation.repository
 * @Description: 交通状态基础设施层实现类
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月28日 下午7:09:37
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

import cy.its.trafficSituation.domain.model.TrafficState;
import cy.its.trafficSituation.domain.repository.ITrafficStateRepository;
import cy.its.trafficSituation.mybatis.client.TrafficStateMapper;

@Service
public class TrafficStateRepository implements ITrafficStateRepository {
	
	@Autowired
	TrafficStateMapper trafficStateMapper;
	/*
	  * <p>Title: aggregateOfId</p>
	  * <p>Description: 根据ID查询</p>
	  * @param id
	  * @return
	  * @throws Exception
	  * @see cy.its.com.domain.IRepository#aggregateOfId(java.lang.String)
	  */

	@Override
	public TrafficState aggregateOfId(String id) throws Exception {
		return trafficStateMapper.selectByPrimaryKey(id);
	}



	
	/*
	  * <p>Title: selectByRoadSectionId</p>
	  * <p>Description:根据路段ID查询 </p>
	  * @param id
	  * @return
	  * @see cy.its.trafficSituation.domain.repository.ITrafficStateRepository#selectByRoadSectionId(java.lang.String)
	  */
	
	
	@Override
	public TrafficState selectByRoadSectionId(String id) {
		return trafficStateMapper.selectByRoadSectionID(id);
	}

	
	
	/*
	  * <p>Title: save</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.com.domain.IRepository#save(java.lang.Object)
	  */
	@Override
	public String save(TrafficState obj) {
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
	public int update(TrafficState obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
