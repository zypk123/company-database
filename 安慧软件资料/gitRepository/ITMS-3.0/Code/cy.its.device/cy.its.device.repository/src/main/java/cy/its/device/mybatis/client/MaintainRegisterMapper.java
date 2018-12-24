package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.MaintainRegister;

public interface MaintainRegisterMapper {
    int deleteByPrimaryKey(String maintenanceId);

    int insert(MaintainRegister record);

    int insertSelective(MaintainRegister record);

    MaintainRegister selectByPrimaryKey(String maintenanceId);

    int updateByPrimaryKeySelective(MaintainRegister record);

    int updateByPrimaryKeyWithBLOBs(MaintainRegister record);

    int updateByPrimaryKey(MaintainRegister record);
    
    List<MaintainRegister> selectMaintainRegisters(Map<String, Object> params);

	int countMaintainRegisters(Map<String, Object> parseParams);
}