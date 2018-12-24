package cy.its.video.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.video.domain.model.TrafficPreset;

public interface TrafficPresetMapper {
	int deleteByPrimaryKey(String presetRecordId);

	int insert(TrafficPreset record);

	int insertSelective(TrafficPreset record);

	TrafficPreset selectByPrimaryKey(String presetRecordId);

	int updateByPrimaryKeySelective(TrafficPreset record);

	int updateByPrimaryKey(TrafficPreset record);

	List<TrafficPreset> selectByCondition(Map<String, String> map);
}