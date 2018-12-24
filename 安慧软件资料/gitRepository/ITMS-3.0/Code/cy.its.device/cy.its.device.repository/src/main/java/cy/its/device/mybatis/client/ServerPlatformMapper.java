package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.ServerPlatform;

public interface ServerPlatformMapper {
	
	int deleteByPrimaryKey(String serverPlatId);

	int insert(ServerPlatform record);

	int insertSelective(ServerPlatform record);

	ServerPlatform selectByPrimaryKey(String serverPlatId);

	int updateByPrimaryKeySelective(ServerPlatform record);

	int updateByPrimaryKey(ServerPlatform record);

	List<ServerPlatform> selectServerPlatforms(Map<String, Object> mapParams);

	int countServerPlatforms(Map<String, Object> mapParams);
}