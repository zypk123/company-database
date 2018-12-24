package cy.its.syscfg.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.model.home.SysDownload;

public interface SysDownloadMapper {
    int deleteByPrimaryKey(String downloadId);

    int insert(SysDownload record);

    int insertSelective(SysDownload record);

    SysDownload selectByPrimaryKey(String downloadId);

    int updateByPrimaryKeySelective(SysDownload record);

    int updateByPrimaryKey(SysDownload record);

	List<SysDownload> selectAll();

	List<SysDownload> selectDownloads(Map<String, Object> parseParams);
	
	int countDownloads(Map<String, Object> parseParams);
}