package cy.its.trafficSituation.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.DateUtil;
import cy.its.platform.common.utils.SqlHelper;
import cy.its.trafficSituation.domain.model.TrafficDeviceFlow;
import cy.its.trafficSituation.domain.repository.ITrafficFlowRepository;
import cy.its.trafficSituation.mybatis.client.TrafficFlowMapper;

@Service
public class TrafficFlowRepository implements ITrafficFlowRepository {

	@Autowired
	TrafficFlowMapper trafficFlowMapper;
	
	@Override
	public List<TrafficDeviceFlow> countDeviceFlow(String deviceSysNbr, Date from, Date to) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("deviceSysNbr", deviceSysNbr);
		p.put("from", DateUtil.formatDate(from));
		p.put("to", DateUtil.formatDate(to));
		
		System.out.println(SqlHelper.getMapperSql(trafficFlowMapper, "countDeviceFlow", p));
		
		return trafficFlowMapper.countDeviceFlow(p);
	}

}
