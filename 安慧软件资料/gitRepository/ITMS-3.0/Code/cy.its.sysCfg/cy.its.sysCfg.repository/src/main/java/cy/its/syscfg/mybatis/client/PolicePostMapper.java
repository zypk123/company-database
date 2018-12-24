package cy.its.syscfg.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.model.traffic.PolicePost;

public interface PolicePostMapper {
    int deleteByPrimaryKey(String policePostId);

    int insert(PolicePost record);

    int insertSelective(PolicePost record);

    PolicePost selectByPrimaryKey(String policePostId);

    int updateByPrimaryKeySelective(PolicePost record);

    int updateByPrimaryKey(PolicePost record);

	
	List<PolicePost> selectAll(Map map);
}