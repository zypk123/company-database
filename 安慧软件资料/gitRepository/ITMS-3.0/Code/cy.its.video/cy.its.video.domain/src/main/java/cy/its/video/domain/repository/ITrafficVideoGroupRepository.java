package cy.its.video.domain.repository;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.video.domain.criteria.TrafficVideoGroupCriteria;
import cy.its.video.domain.model.TrafficVideoGroup;

public interface ITrafficVideoGroupRepository extends IRepository<TrafficVideoGroup> {
	List<TrafficVideoGroup> getVideoGroupByCondition(TrafficVideoGroupCriteria criteria);

}
