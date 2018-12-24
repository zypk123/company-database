package cy.its.video.mybatis.client;

import java.util.List;

import cy.its.video.domain.model.TrafficVideoLog;

public interface TrafficVideoLogMapper {
	int deleteByPrimaryKey(String videoLogId);

	int insert(TrafficVideoLog record);

	int insertSelective(TrafficVideoLog record);

	TrafficVideoLog selectByPrimaryKey(String videoLogId);

	List<TrafficVideoLog> selectByPlanLogID(String planLogId);

	int updateByPrimaryKeySelective(TrafficVideoLog record);

	int updateByPrimaryKey(TrafficVideoLog record);
}