package cy.its.syscfg.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.model.home.SysNotice;

public interface SysNoticeMapper {
    int deleteByPrimaryKey(String noticeId);

    int insert(SysNotice record);

    int insertSelective(SysNotice record);

    SysNotice selectByPrimaryKey(String noticeId);

    int updateByPrimaryKeySelective(SysNotice record);

    int updateByPrimaryKey(SysNotice record);

	List<SysNotice> selectNotices(Map<String, Object> parseParams);

	int countNotices(Map<String, Object> parseParams);
}