package cy.its.trafficSituation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficWeather;

public interface TrafficWeatherMapper {
    int deleteByPrimaryKey(String meteorologicDataId);

    int insert(TrafficWeather record);

    int insertSelective(TrafficWeather record);

    TrafficWeather selectByPrimaryKey(String meteorologicDataId);

    int updateByPrimaryKeySelective(TrafficWeather record);

    int updateByPrimaryKey(TrafficWeather record);
    
    List<TrafficWeather> selectWeathers(Map<String,Object> map);
    
    List<TrafficWeather> select2HourWeathers(Map<String,Object> map);
    
    TrafficWeather getLastWeatherByNBR(String deviceNbr);
}