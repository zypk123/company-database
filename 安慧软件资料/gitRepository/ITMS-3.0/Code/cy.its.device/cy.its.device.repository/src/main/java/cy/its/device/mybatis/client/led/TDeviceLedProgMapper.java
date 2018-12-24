package cy.its.device.mybatis.client.led;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.model.led.LedProgNoChange;
import cy.its.device.mybatis.model.TDeviceLedProg;

public interface TDeviceLedProgMapper {
    int deleteByPrimaryKey(String programId);

    int insert(TDeviceLedProg record);

    int insertSelective(TDeviceLedProg record);

    TDeviceLedProg selectByPrimaryKey(String programId);

    int updateByPrimaryKeySelective(TDeviceLedProg record);

    int updateByPrimaryKey(TDeviceLedProg record);
    
    int countProgOfDevice(@Param("deviceId") String deviceId);
    
    int selectProgNoSEQ();
    
    int updateProgNo(Map<String, Object> params);

	boolean updateProgNoBatch(@Param("changes") List<LedProgNoChange> changes);
}