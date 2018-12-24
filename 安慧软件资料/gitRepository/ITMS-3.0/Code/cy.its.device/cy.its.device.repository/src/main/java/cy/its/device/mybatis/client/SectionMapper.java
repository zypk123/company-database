package cy.its.device.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.model.site.Section;


public interface SectionMapper {
    int deleteByPrimaryKey(String sectionId);

    int insert(Section record);

    int insertSelective(Section record);

    Section selectByPrimaryKey(String sectionId);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);
    
    List<Section> selectSectionBySiteIds(@Param("siteIds") List<String> siteIds);

	int countSectionBySiteIds(@Param("siteIds") List<String> siteIds);
}