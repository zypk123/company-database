package cy.its.device.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.model.Server;

public interface ServerMapper {
    int deleteByPrimaryKey(String serverId);

    int insert(Server record);

    int insertSelective(Server record);

    Server selectByPrimaryKey(String serverId);

    int updateByPrimaryKeySelective(Server record);

    int updateByPrimaryKey(Server record);
    
    List<Server> selectByServerIp(String serverIp);
    
    List<Server> selectServers(@Param("surveySystemId") String surveySystemId);
}