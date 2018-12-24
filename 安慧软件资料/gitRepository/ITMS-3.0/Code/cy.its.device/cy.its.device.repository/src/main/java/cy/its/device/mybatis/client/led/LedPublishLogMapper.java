package cy.its.device.mybatis.client.led;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.led.LedPublishLog;

public interface LedPublishLogMapper {
    int deleteByPrimaryKey(String publishLogId);

    int insert(LedPublishLog record);

    int insertSelective(LedPublishLog record);

    LedPublishLog selectByPrimaryKey(String publishLogId);

    int updateByPrimaryKeySelective(LedPublishLog record);

    int updateByPrimaryKey(LedPublishLog record);

	List<LedPublishLog> selectLedPublishLogs(Map<String, Object> parseParams);
	
	int countPublishLogs(Map<String, Object> parseParams);
}