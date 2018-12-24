package cy.its.video.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.video.domain.model.TrafficVideoGroup;

public interface TrafficVideoGroupMapper {
	int deleteByPrimaryKey(String groupId);

	int insert(TrafficVideoGroup record);

	int insertSelective(TrafficVideoGroup record);

	TrafficVideoGroup selectByPrimaryKey(String groupId);

	int updateByPrimaryKeySelective(TrafficVideoGroup record);

	int updateByPrimaryKey(TrafficVideoGroup record);

	List<TrafficVideoGroup> getVideoGroupByCondition(Map<String, Object> map);
}