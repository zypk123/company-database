package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.ServerPlatformAppAsso;

public interface ServerPlatformAppAssoMapper {
    int insert(ServerPlatformAppAsso record);

    int insertSelective(ServerPlatformAppAsso record);
    
    int delete(ServerPlatformAppAsso record);
    
    List<ServerPlatformAppAsso> selectAssos(Map<String, Object> params);
}