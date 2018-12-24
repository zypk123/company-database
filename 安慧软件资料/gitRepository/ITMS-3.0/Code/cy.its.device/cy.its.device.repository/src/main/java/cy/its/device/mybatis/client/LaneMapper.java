package cy.its.device.mybatis.client;

import java.util.List;

import cy.its.device.domain.model.site.Lane;



public interface LaneMapper {
    int deleteByPrimaryKey(String laneId);

    int insert(Lane record);

    int insertSelective(Lane record);

    Lane selectByPrimaryKey(String laneId);

    int updateByPrimaryKeySelective(Lane record);

    int updateByPrimaryKey(Lane record);
    
    List<Lane> findLaneBySectionId(String sectionId);
}