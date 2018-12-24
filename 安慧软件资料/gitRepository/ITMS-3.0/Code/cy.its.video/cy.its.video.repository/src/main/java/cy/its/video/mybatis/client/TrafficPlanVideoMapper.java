package cy.its.video.mybatis.client;

import java.util.List;

import cy.its.video.domain.model.TrafficPlanVideo;

public interface TrafficPlanVideoMapper {
	int deleteByPrimaryKey(String relatedVideoId);

	int insert(TrafficPlanVideo record);

	int insertSelective(TrafficPlanVideo record);

	TrafficPlanVideo selectByPrimaryKey(String relatedVideoId);

	int updateByPrimaryKeySelective(TrafficPlanVideo record);

	int updateByPrimaryKey(TrafficPlanVideo record);

	List<TrafficPlanVideo> getDataByVideoPlanID(String videoPlanID);
}