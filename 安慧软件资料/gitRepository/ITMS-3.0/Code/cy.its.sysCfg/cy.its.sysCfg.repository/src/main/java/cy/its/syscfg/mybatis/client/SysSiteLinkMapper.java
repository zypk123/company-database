package cy.its.syscfg.mybatis.client;

import java.util.List;

import cy.its.syscfg.domain.model.home.SysSiteLink;

public interface SysSiteLinkMapper {
    int deleteByPrimaryKey(String linkId);

    int insert(SysSiteLink record);

    int insertSelective(SysSiteLink record);

    SysSiteLink selectByPrimaryKey(String linkId);

    int updateByPrimaryKeySelective(SysSiteLink record);

    int updateByPrimaryKey(SysSiteLink record);

	List<SysSiteLink> selectAll();
}