package cy.its.video.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.video.domain.model.TrafficPlanLog;

public interface TrafficPlanLogMapper {
	int deleteByPrimaryKey(String videoPlanLogId);

	int insert(TrafficPlanLog record);

	int insertSelective(TrafficPlanLog record);

	TrafficPlanLog selectByPrimaryKey(String videoPlanLogId);

	int updateByPrimaryKeySelective(TrafficPlanLog record);

	int updateByPrimaryKey(TrafficPlanLog record);

	List<TrafficPlanLog> getLogByCondition(Map<String, Object> map);

	int countLogByCondition(Map<String, Object> map);
}