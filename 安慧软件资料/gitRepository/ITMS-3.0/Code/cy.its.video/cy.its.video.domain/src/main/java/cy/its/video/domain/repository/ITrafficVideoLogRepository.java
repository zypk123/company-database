package cy.its.video.domain.repository;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.video.domain.model.TrafficVideoLog;

public interface ITrafficVideoLogRepository extends IRepository<TrafficVideoLog> {

	/**
	 * 根据巡航日志id获取相关的事件记录
	 * 
	 * @param videoPlanLogId
	 * @return
	 */
	List<TrafficVideoLog> getVideoCruiseEvent(String videoPlanLogId);

}
