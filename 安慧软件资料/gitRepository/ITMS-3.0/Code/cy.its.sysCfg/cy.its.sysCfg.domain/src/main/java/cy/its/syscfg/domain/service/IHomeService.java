package cy.its.syscfg.domain.service;

import java.util.List;

import org.springframework.web.servlet.config.VelocityConfigurerBeanDefinitionParser;

import cy.its.syscfg.domain.criteria.DownloadCriteria;
import cy.its.syscfg.domain.criteria.NoticeCriteria;
import cy.its.syscfg.domain.model.home.SysDailyMenu;
import cy.its.syscfg.domain.model.home.SysDownload;
import cy.its.syscfg.domain.model.home.SysNotice;
import cy.its.syscfg.domain.model.home.SysSiteLink;

public interface IHomeService {

	/**
	 * 查询符合条件的最新五条通知公告
	 * 
	 * @param recentMax
	 * @param userId
	 * @param noticeTitle
	 * @param createTimeFrom
	 * @param createTimeTo
	 * @return
	 */
	List<SysNotice> findNotices(NoticeCriteria criteria);

	/**
	 * 创建系统公告
	 * 
	 * @param notice
	 * @throws Exception
	 */
	void createSysNotice(SysNotice notice) throws Exception;

	/**
	 * 删除系统公告
	 * 
	 * @param noticeId
	 */
	void removeSysNotice(String noticeId);

	/**
	 * 更新系统公告
	 * 
	 * @param notice
	 * @throws Exception
	 */
	void updateSysNotice(SysNotice notice) throws Exception;

	/**
	 * 查询指定的系统公告
	 * 
	 * @param noticdId
	 * @return
	 * @throws Exception
	 */
	SysNotice sysNoticeOfId(String noticdId) throws Exception;

	/**
	 * 公告变更通知
	 */
	void sysNoticeChanged();

	/**
	 * 列出所有的下载内容
	 * 
	 * @return
	 */
	List<SysDownload> allDownloads();
	
	/**
	 * 
	 * @return
	 */
	List<SysDownload> findDownloads(DownloadCriteria criteria);

	/**
	 * 查询指定的下载内容
	 * 
	 * @throws Exception
	 */
	SysDownload downLoadOfId(String downLoadId) throws Exception;

	/**
	 * 创建下载
	 * 
	 * @param sysDownload
	 */
	void createDownload(SysDownload sysDownload);
	
	/**
	 * 删除下载
	 * @param downloadId
	 */
	void removeDownload(String downloadId);

	/**
	 * 更新下载
	 * 
	 * @param sysDownload
	 * @throws Exception
	 */
	void updateDownload(SysDownload sysDownload) throws Exception;

	/**
	 * 下载内容变更通知
	 */
	void downloadChanged();

	/**
	 * 获取指定用户的常用菜单
	 * 
	 * @param userId
	 * @return
	 */
	List<SysDailyMenu> dailyMenusOfUser(String userId);

	/**
	 * 移除指定的日常菜单
	 * 
	 * @param dailyMenuId
	 */
	void removeDailyMenu(String dailyMenuId);

	/**
	 * 创建日常菜单
	 * 
	 * @param dailyMenu
	 * @throws Exception
	 */
	void createDailyMenu(SysDailyMenu dailyMenu) throws Exception;
	
	/**
	 * 根据用户的权限重新整理日常菜单
	 * @param userId
	 */
	void resetUserDailyMenus();

	/**
	 * 查询所有的常用链接
	 * 
	 * @return
	 */
	List<SysSiteLink> allSiteLinks();

	/**
	 * 创建常用链接
	 * 
	 * @param siteLink
	 */
	void createSiteLink(SysSiteLink siteLink);

	/**
	 * 移除指定的常用链接
	 * 
	 * @param linkId
	 */
	void removeSiteLink(String linkId);

	/**
	 * 更新常用链接
	 * 
	 * @param siteLink
	 * @throws Exception
	 */
	void updateSiteLink(SysSiteLink siteLink) throws Exception;
}
