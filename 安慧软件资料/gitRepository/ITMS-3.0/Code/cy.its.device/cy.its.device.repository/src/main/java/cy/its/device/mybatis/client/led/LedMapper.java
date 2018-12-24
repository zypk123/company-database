package cy.its.device.mybatis.client.led;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.led.Led;
import cy.its.device.domain.model.led.LedSys;

public interface LedMapper {
    int deleteByPrimaryKey(String deviceId);

    int insert(Led record);

    int insertSelective(Led record);

    Led selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(Led record);

    int updateByPrimaryKey(Led record);

	List<Led> selectLeds(Map<String, Object> parseParams);
	
	List<LedSys> selectLedSys(Map<String, Object> parseParams);
}