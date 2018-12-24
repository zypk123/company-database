package cy.its.sysCfg.rest.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cy.its.sysCfg.rest.dto.DataGridResult;
import cy.its.sysCfg.rest.dto.DownloadDto;
import cy.its.sysCfg.rest.dto.DownloadQryDto;
import cy.its.sysCfg.rest.dto.FastMenuDto;
import cy.its.sysCfg.rest.dto.NoticeDto;
import cy.its.sysCfg.rest.dto.NoticeQryDto;

public interface IHomeAction {	

	int removeNotice(String noticeId);

	List<NoticeDto> getLatestNoticeLst(String currentOrgPrivilegeCode);

	DataGridResult<NoticeDto> queryNotice(NoticeQryDto noticeQryDto);

	int addNotice(NoticeDto form) throws Exception;

	int updateNotice(NoticeDto form) throws Exception;

	DataGridResult<DownloadDto> queryDownload(DownloadQryDto downloadQryDto);

	List<DownloadDto> getLatestDownloadLst();

	int updateDownload(DownloadDto form) throws Exception;

	int addDownload(DownloadDto form) throws Exception;

	int removeDownload(String downloadId);

	List<FastMenuDto> getUserMenu(String currentUserId);

	void saveUserFastMenu(String userId, String oldMenus, String newMenus) throws Exception;

	List<FastMenuDto> getUserFastMenu(String currentUserId);

	int loginTotalOfUser(String userId);

	int loginCountCurMonthOfUser(String userId);

	Map getLoginTotalOfUser(String userId);

	

	

}
