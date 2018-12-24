package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.Meteorologic;

public interface MeteorologicMapper {
    int deleteByPrimaryKey(String deviceId);

    int insert(Meteorologic record);

    int insertSelective(Meteorologic record);

    Meteorologic selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(Meteorologic record);

    int updateByPrimaryKey(Meteorologic record);
    
    int countMeteorologics(Map<String, Object> parseParams);

	List<Meteorologic> selectMeteorologics(Map<String, Object> parseParams);
}