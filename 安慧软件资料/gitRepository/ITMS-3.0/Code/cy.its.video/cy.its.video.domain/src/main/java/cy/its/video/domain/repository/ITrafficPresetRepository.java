package cy.its.video.domain.repository;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.video.domain.model.TrafficPreset;

public interface ITrafficPresetRepository extends IRepository<TrafficPreset> {
	List<TrafficPreset> selectByCondition(Map<String, String> map);
}
