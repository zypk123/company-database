package cy.its.device.mybatis.client.led;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.led.LedSpec;

public interface LedSpecMapper {
    int deleteByPrimaryKey(String specId);

    int insert(LedSpec record);

    int insertSelective(LedSpec record);

    LedSpec selectByPrimaryKey(String specId);

    int updateByPrimaryKeySelective(LedSpec record);

    int updateByPrimaryKey(LedSpec record);

	List<LedSpec> selectLedSpecs(Map<String, Object> parseParams);
	
	int countLedSpecs(Map<String, Object> parseParams);
}