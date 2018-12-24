package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.DailyMaintain;

public interface DailyMaintainMapper {
    int deleteByPrimaryKey(String dailyMaintenanceId);

    int insert(DailyMaintain record);

    int insertSelective(DailyMaintain record);

    DailyMaintain selectByPrimaryKey(String dailyMaintenanceId);

    int updateByPrimaryKeySelective(DailyMaintain record);

    int updateByPrimaryKey(DailyMaintain record);
    
    List<DailyMaintain> selectDailyMaintains(Map<String, Object> params);

	int countDailyMaintains(Map<String, Object> parseParams);
}