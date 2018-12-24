package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.SurveySys;

public interface SurveySysMapper {
	int deleteByPrimaryKey(String surveySystemId);

	int insert(SurveySys record);

	int insertSelective(SurveySys record);

	SurveySys selectByPrimaryKey(String surveySystemId);

	int updateByPrimaryKeySelective(SurveySys record);

	int updateByPrimaryKey(SurveySys record);

	List<SurveySys> selectSurveySyses(Map<String, Object> params);

	int countSurveySystems(Map<String, Object> parseParams);
}