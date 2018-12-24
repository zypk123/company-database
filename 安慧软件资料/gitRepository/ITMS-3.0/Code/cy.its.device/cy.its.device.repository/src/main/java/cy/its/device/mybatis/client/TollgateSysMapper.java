package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.TollgateSys;
import cy.its.device.domain.model.VTollgate;

public interface TollgateSysMapper {
    int deleteByPrimaryKey(String deviceId);

    int insert(TollgateSys record);

    int insertSelective(TollgateSys record);

    TollgateSys selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(TollgateSys record);

    int updateByPrimaryKey(TollgateSys record);
    
    List<VTollgate> selectVTollgate(Map<String, Object> params);
    
    int countVTollgate(Map<String, Object> params);
}