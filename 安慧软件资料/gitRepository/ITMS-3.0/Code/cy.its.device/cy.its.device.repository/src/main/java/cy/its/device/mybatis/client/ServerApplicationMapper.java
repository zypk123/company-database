package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.ServerApplication;

public interface ServerApplicationMapper {
	
    int deleteByPrimaryKey(String serverAppId);

    int insert(ServerApplication record);

    int insertSelective(ServerApplication record);

    ServerApplication selectByPrimaryKey(String serverAppId);

    int updateByPrimaryKeySelective(ServerApplication record);

    int updateByPrimaryKey(ServerApplication record);
    
    List<ServerApplication> selectServerApps(Map<String, Object> parseParams);
    
    int countServerApps(Map<String, Object> parseParams);
}