package cy.its.video.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.video.domain.model.TrafficPlan;

public interface TrafficPlanMapper {
	int deleteByPrimaryKey(String videoPlanId);

	int insert(TrafficPlan record);

	int insertSelective(TrafficPlan record);

	TrafficPlan selectByPrimaryKey(String videoPlanId);

	List<TrafficPlan> getTrafficPlanByCondition(Map<String, String> map);

	int updateByPrimaryKeySelective(TrafficPlan record);

	int updateByPrimaryKey(TrafficPlan record);
}