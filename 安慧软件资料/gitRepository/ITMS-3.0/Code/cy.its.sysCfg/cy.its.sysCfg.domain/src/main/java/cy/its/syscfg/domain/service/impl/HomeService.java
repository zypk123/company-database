package cy.its.syscfg.domain.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.criteria.DownloadCriteria;
import cy.its.syscfg.domain.criteria.NoticeCriteria;
import cy.its.syscfg.domain.model.home.SysDailyMenu;
import cy.its.syscfg.domain.model.home.SysDownload;
import cy.its.syscfg.domain.model.home.SysNotice;
import cy.its.syscfg.domain.model.home.SysSiteLink;
import cy.its.syscfg.domain.repository.duty.IUserRepository;
import cy.its.syscfg.domain.repository.home.IDailyMenuRepository;
import cy.its.syscfg.domain.repository.home.IDownloadRepository;
import cy.its.syscfg.domain.repository.home.INoticeRepository;
import cy.its.syscfg.domain.repository.home.ISiteLinkRepository;
import cy.its.syscfg.domain.service.IHomeService;

@Service
public class HomeService implements IHomeService {

	@Autowired
	IDailyMenuRepository dailyMenuRepository;

	@Autowired
	IDownloadRepository downloadRepository;

	@Autowired
	INoticeRepository noticeRepository;

	@Autowired
	ISiteLinkRepository siteLinkRepository;

	@Autowired
	IUserRepository userRepository;

	@Override
	public List<SysNotice> findNotices(NoticeCriteria criteria) {
		if (criteria.getNeedTotal()) {
			criteria.setTotal(noticeRepository.countNotices(criteria));
		}

		return noticeRepository.findNotices(criteria);
	}

	@Override
	public void createSysNotice(SysNotice notice) throws Exception {
		notice.validateUser(userRepository);
		notice.setNoticeId(StringUtil.generateUUID());
		notice.setCreateTime(new Date());
		noticeRepository.save(notice);
	}

	@Override
	public void removeSysNotice(String noticeId) {
		noticeRepository.delete(noticeId);
	}

	@Override
	public void updateSysNotice(SysNotice notice) throws Exception {
		if (StringUtil.isNullOrEmpty(notice.getIdentityId())) {
			throw new Exception("未指定要更新公告的唯一标识");
		}

		notice.validateUser(userRepository);
		notice.setUpdateTime(new Date());
		noticeRepository.update(notice);
	}

	@Override
	public SysNotice sysNoticeOfId(String noticdId) throws Exception {
		return noticeRepository.aggregateOfId(noticdId);
	}

	@Override
	public List<SysDownload> allDownloads() {
		return downloadRepository.allDownloads();
	}

	@Override
	public SysDownload downLoadOfId(String downLoadId) throws Exception {
		return downloadRepository.aggregateOfId(downLoadId);
	}

	@Override
	public void createDownload(SysDownload sysDownload) {
		sysDownload.setDownloadId(StringUtil.generateUUID());
		sysDownload.setCreateTime(new Date());
		downloadRepository.save(sysDownload);
	}

	@Override
	public void updateDownload(SysDownload sysDownload) throws Exception {
		if (StringUtil.isNullOrEmpty(sysDownload.getIdentityId())) {
			throw new Exception("未指定要更新的下载内容唯一标识");
		}
		sysDownload.setUpdateTime(new Date());
		downloadRepository.update(sysDownload);
	}

	@Override
	public List<SysDailyMenu> dailyMenusOfUser(String userId) {
		return dailyMenuRepository.dailyMenusOfUser(userId);
	}

	@Override
	public void removeDailyMenu(String dailyMenuId) {
		dailyMenuRepository.delete(dailyMenuId);
	}

	@Override
	public void resetUserDailyMenus() {
		dailyMenuRepository.deleteByUserPermmission();
	}
	
	@Override
	public void createDailyMenu(SysDailyMenu dailyMenu) throws Exception {
		dailyMenu.validateUser(userRepository);
		dailyMenu.setDailyMenuId(StringUtil.generateUUID());
		dailyMenu.setUpdateTime(new Date());
		dailyMenu.setVisitCount(0L);
		dailyMenu.setSortIndex("0");
		
		dailyMenuRepository.save(dailyMenu);

	}

	@Override
	public List<SysSiteLink> allSiteLinks() {
		return siteLinkRepository.allSiteLinks();
	}

	@Override
	public void createSiteLink(SysSiteLink siteLink) {
		siteLink.setLinkId(StringUtil.generateUUID());
		siteLinkRepository.save(siteLink);
	}

	@Override
	public void removeSiteLink(String linkId) {
		siteLinkRepository.delete(linkId);
	}

	@Override
	public void updateSiteLink(SysSiteLink siteLink) throws Exception {
		if (StringUtil.isNullOrEmpty(siteLink.getIdentityId())) {
			throw new Exception("未指定要更新的常用链接唯一标识");
		}
		siteLinkRepository.update(siteLink);
	}

	@Override
	public void sysNoticeChanged() {
		noticeRepository.sysNoticeChanged();
	}

	@Override
	public void downloadChanged() {
		downloadRepository.downloadChanged();
	}

	@Override
	public List<SysDownload> findDownloads(DownloadCriteria criteria) {		
		return downloadRepository.findDownloads(criteria);
	}

	@Override
	public void removeDownload(String downloadId) {
		downloadRepository.delete(downloadId);
	}
}
