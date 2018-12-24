package cy.its.syscfg.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.criteria.MessageCriteria;
import cy.its.syscfg.mybatis.model.T_Msg_Info;

public interface T_Msg_InfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(T_Msg_Info record);

    int insertSelective(T_Msg_Info record);

    T_Msg_Info selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T_Msg_Info record);

    int updateByPrimaryKey(T_Msg_Info record);

    List<Map<String, String>> getMessageList(Map<String,Object> map) throws Exception;
}