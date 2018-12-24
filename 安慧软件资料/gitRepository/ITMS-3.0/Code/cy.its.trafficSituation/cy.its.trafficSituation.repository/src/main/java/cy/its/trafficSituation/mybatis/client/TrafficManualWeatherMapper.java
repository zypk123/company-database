package cy.its.trafficSituation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficManualWeather;

public interface TrafficManualWeatherMapper {
    int deleteByPrimaryKey(String badWeatherId);

    int insert(TrafficManualWeather record);

    int insertSelective(TrafficManualWeather record);

    TrafficManualWeather selectByPrimaryKey(String badWeatherId);

    int updateByPrimaryKeySelective(TrafficManualWeather record);

    int updateByPrimaryKey(TrafficManualWeather record);
    
    List<TrafficManualWeather> selectAll(Map<String, Object> map);
}