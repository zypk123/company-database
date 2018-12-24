package cy.its.sysCfg.rest.action.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cy.its.com.util.ObjectMapUtils;
import cy.its.sysCfg.rest.action.IHomeAction;
import cy.its.sysCfg.rest.dto.DataGridResult;
import cy.its.sysCfg.rest.dto.DownloadDto;
import cy.its.sysCfg.rest.dto.DownloadQryDto;
import cy.its.sysCfg.rest.dto.FastMenuDto;
import cy.its.sysCfg.rest.dto.NoticeDto;
import cy.its.sysCfg.rest.dto.NoticeQryDto;
import cy.its.sysCfg.rest.dto.Results;
import cy.its.syscfg.domain.criteria.DownloadCriteria;
import cy.its.syscfg.domain.criteria.NoticeCriteria;
import cy.its.syscfg.domain.model.home.SysDailyMenu;
import cy.its.syscfg.domain.model.home.SysDownload;
import cy.its.syscfg.domain.model.home.SysNotice;
import cy.its.syscfg.domain.model.permission.Menu;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.syscfg.domain.service.IHomeService;
import cy.its.syscfg.domain.service.IPermissionService;

@RestController
@RequestMapping("/sysCfg/HomeAction")
public class HomeAction implements IHomeAction {

	@Autowired
	IHomeService homeService;
	@Autowired
	IPermissionService permissionService;
	@Autowired
	IDutyService dutyService;

	/**
	 * 查询通知公告信息
	 * 
	 * @param noticeQryDto
	 * @param request
	 * @return
	 */
	@Override
	@RequestMapping(value = "/queryNotice")
	public DataGridResult<NoticeDto> queryNotice(NoticeQryDto noticeQryDto) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		NoticeCriteria noticeCriteria = new NoticeCriteria();
		noticeCriteria.noticeTitle = noticeQryDto.getTitle();
		if (noticeQryDto.getCreateTimeFr() != null && !noticeQryDto.getCreateTimeFr().equals("")) {
			try {
				noticeCriteria.createTimeFrom = sFormat.parse(noticeQryDto.getCreateTimeFr());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (noticeQryDto.getCreateTimeTo() != null && !noticeQryDto.getCreateTimeTo().equals("")) {
			try {
				noticeCriteria.createTimeTo = sFormat.parse(noticeQryDto.getCreateTimeTo());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		noticeCriteria.userOrgPrivilegeCode = noticeQryDto.getCurrentOrgPrivilegeCode();
		noticeCriteria.setPageNum(noticeQryDto.getPageNumber());
		noticeCriteria.setPageSize(noticeQryDto.getPageSize());
		noticeCriteria.setNeedTotal(true);// 判断是否要总数
		List<SysNotice> noticeLst = homeService.findNotices(noticeCriteria);
		long total = noticeCriteria.getTotal();// 获取总数
		List<NoticeDto> lst = domainLst2DtoLst(noticeLst);
		Results<NoticeDto> res = new Results<NoticeDto>();
		res.setRows(lst);
		res.setTotal(total);
		DataGridResult<NoticeDto> dgr = new DataGridResult<NoticeDto>();
		dgr.setError("");
		dgr.setResult(res);
		return dgr;
	}

	/**
	 * 查询最新的5条通知公告信息
	 * 
	 * @param request
	 * @return
	 */
	@Override
	@RequestMapping(value = "/getLatestNoticeLst")
	public List<NoticeDto> getLatestNoticeLst(String currentOrgPrivilegeCode) {
		NoticeCriteria noticeCriteria = new NoticeCriteria();
		noticeCriteria.userOrgPrivilegeCode = currentOrgPrivilegeCode;
		List<SysNotice> noticeLst = homeService.findNotices(noticeCriteria);
		if (noticeLst != null && noticeLst.size() > 5) {
			noticeLst = noticeLst.subList(0, 5);
		}
		return domainLst2DtoLst(noticeLst);
	}

	private List<NoticeDto> domainLst2DtoLst(List<SysNotice> noticeLst) {
		List<NoticeDto> lst = new ArrayList<NoticeDto>();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (noticeLst != null) {
			for (int i = 0; i < noticeLst.size(); i++) {
				NoticeDto noticeDto = new NoticeDto();
				ObjectMapUtils.parseObject(noticeDto, noticeLst.get(i));
				if (noticeLst.get(i).getCreateTime() != null) {
					noticeDto.setCreateTime(sFormat.format(noticeLst.get(i).getCreateTime()));
				}
				if (noticeLst.get(i).getUpdateTime() != null) {
					noticeDto.setUpdateTime(sFormat.format(noticeLst.get(i).getUpdateTime()));
				}
				lst.add(noticeDto);
			}
		}
		return lst;
	}

	@Override
	@RequestMapping(value = "/addNotice")
	public int addNotice(@ModelAttribute("form") NoticeDto form) throws Exception {
		SysNotice sysNotice = new SysNotice();
		ObjectMapUtils.parseObject(sysNotice, form);
		sysNotice.setUserId(form.getCurrentUserId());
		homeService.createSysNotice(sysNotice);
		// homeService.sysNoticeChanged();
		return 1;
	}

	/**
	 * 删除通知信息
	 */
	@Override
	@RequestMapping(value = "/removeNotice")
	public int removeNotice(@RequestParam("noticeId") String noticeId) {
		homeService.removeSysNotice(noticeId);
		// homeService.sysNoticeChanged();
		return 1;
	}

	/**
	 * 编辑通知信息
	 * 
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/updateNotice")
	public int updateNotice(@ModelAttribute("form") NoticeDto form) throws Exception {
		SysNotice sysNotice = homeService.sysNoticeOfId(form.getNoticeId());
		sysNotice.setNoticeTitle(form.getNoticeTitle());
		sysNotice.setNoticeContent(form.getNoticeContent());
		homeService.updateSysNotice(sysNotice);
		// homeService.sysNoticeChanged();
		return 1;
	}

	/****** 下载专区 *********************************************************************/
	@Override
	@RequestMapping(value = "/queryDownload")
	public DataGridResult<DownloadDto> queryDownload(DownloadQryDto downloadQryDto) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DownloadCriteria criteria = new DownloadCriteria();
		criteria.title = downloadQryDto.getDownloadTitle();
		if (downloadQryDto.getCreateTimeFr() != null && !downloadQryDto.getCreateTimeFr().equals("")) {
			try {
				criteria.createTimeFrom = sFormat.parse(downloadQryDto.getCreateTimeFr());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (downloadQryDto.getCreateTimeTo() != null && !downloadQryDto.getCreateTimeTo().equals("")) {
			try {
				criteria.createTimeTo = sFormat.parse(downloadQryDto.getCreateTimeTo());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		criteria.setPageNum(downloadQryDto.getPageNumber());
		criteria.setPageSize(downloadQryDto.getPageSize());
		criteria.setNeedTotal(true);// 判断是否要总数
		List<SysDownload> downloadLst = homeService.findDownloads(criteria);
		long total = criteria.getTotal();// 获取总数
		List<DownloadDto> lst = domainDownloadLst2DtoLst(downloadLst);
		Results<DownloadDto> res = new Results<DownloadDto>();
		res.setRows(lst);
		res.setTotal(total);
		DataGridResult<DownloadDto> dgr = new DataGridResult<DownloadDto>();
		dgr.setError("");
		dgr.setResult(res);
		return dgr;
	}

	/**
	 * 查询最新的5条通知公告信息
	 * 
	 * @param request
	 * @return
	 */
	@Override
	@RequestMapping(value = "/getLatestDownloadLst")
	public List<DownloadDto> getLatestDownloadLst() {
		List<SysDownload> downloadLst = homeService.allDownloads();
		if (downloadLst != null && downloadLst.size() > 5) {
			downloadLst = downloadLst.subList(0, 5);
		}
		return domainDownloadLst2DtoLst(downloadLst);
	}

	private List<DownloadDto> domainDownloadLst2DtoLst(List<SysDownload> sysDownloads) {
		List<DownloadDto> lst = new ArrayList<DownloadDto>();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (sysDownloads != null) {
			for (int i = 0; i < sysDownloads.size(); i++) {
				DownloadDto downloadDto = new DownloadDto();
				ObjectMapUtils.parseObject(downloadDto, sysDownloads.get(i));
				if (sysDownloads.get(i).getCreateTime() != null) {
					downloadDto.setCreateTime(sFormat.format(sysDownloads.get(i).getCreateTime()));
				}
				if (sysDownloads.get(i).getUpdateTime() != null) {
					downloadDto.setUpdateTime(sFormat.format(sysDownloads.get(i).getUpdateTime()));
				}
				lst.add(downloadDto);
			}
		}
		return lst;
	}

	/**
	 * 新增下载
	 */
	@Override
	@RequestMapping(value = "/addDownload")
	public int addDownload(@ModelAttribute("form") DownloadDto form) throws Exception {
		// 保存到数据库
		SysDownload sysDownload = new SysDownload();
		ObjectMapUtils.parseObject(sysDownload, form);
		homeService.createDownload(sysDownload);
		// 通知更新
		// homeService.downloadChanged();
		return 1;
	}

	/**
	 * 删除下载信息
	 */
	@Override
	@RequestMapping(value = "/removeDownload")
	public int removeDownload(@RequestParam("downloadId") String downloadId) {
		homeService.removeDownload(downloadId);
		// homeService.sysNoticeChanged();
		return 1;
	}

	/**
	 * 编辑下载信息
	 * 
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/updateDownload")
	public int updateDownload(@ModelAttribute("form") DownloadDto form) throws Exception {
		// 保存到数据库
		SysDownload sysDownload = homeService.downLoadOfId(form.getDownloadId());
		sysDownload.setDownloadTitle(form.getDownloadTitle());
		sysDownload.setDownloadContent(form.getDownloadContent());
		sysDownload.setDownloadUrl(form.getDownloadUrl());
		homeService.updateDownload(sysDownload);
		// 通知更新
		// homeService.downloadChanged();
		return 1;
	}

	@Override
	@RequestMapping(value = "/getUserMenu")
	public List<FastMenuDto> getUserMenu(String currentUserId) {
		List<FastMenuDto> fastMenuDtos = new ArrayList<FastMenuDto>();
		List<Menu> userMenus = permissionService.menusOfUser(currentUserId);
		if (userMenus == null || userMenus.size() == 0) {
			return fastMenuDtos;
		}
		List<Menu> userFirstLevelMenus = userMenus.stream().filter(p -> p.getMenuCode().length() == 2)
				.collect(Collectors.toList());
		if (userFirstLevelMenus == null || userFirstLevelMenus.size() == 0) {
			return fastMenuDtos;
		}
		for (int i = 0; i < userFirstLevelMenus.size(); i++) {
			FastMenuDto fastMenuDto = createFastMenuDto(userFirstLevelMenus.get(i));
			String mc = userFirstLevelMenus.get(i).getMenuCode();

			List<Menu> userThirdLevelMenus = userMenus.stream()
					.filter(p -> p.getMenuCode().length() == 6 && p.getMenuCode().startsWith(mc))
					.collect(Collectors.toList());
			if (userThirdLevelMenus != null && userThirdLevelMenus.size() > 0) {
				List<FastMenuDto> thirdFastMenuDtos = new ArrayList<FastMenuDto>();
				for (int j = 0; j < userThirdLevelMenus.size(); j++) {
					FastMenuDto thirdFastMenuDto = createFastMenuDto(userThirdLevelMenus.get(j));
					thirdFastMenuDtos.add(thirdFastMenuDto);
				}
				fastMenuDto.setSonMenus(thirdFastMenuDtos);
			}
			fastMenuDtos.add(fastMenuDto);
		}
		return fastMenuDtos;
	}

	private FastMenuDto createFastMenuDto(Menu menu) {
		FastMenuDto fastMenuDto = new FastMenuDto();
		String mc = menu.getMenuCode();
		fastMenuDto.setMenuCode(mc);
		fastMenuDto.setMenuName(menu.getMenuName());
		fastMenuDto.setTargetUrl(menu.getTargetUrl());
		return fastMenuDto;
	}
	
	@Override
	@RequestMapping(value = "/getUserFastMenu")
	public List<FastMenuDto> getUserFastMenu(String currentUserId) {
		List<FastMenuDto> fastMenuDtos = new ArrayList<FastMenuDto>();
		List<SysDailyMenu> userDailyMenus = homeService.dailyMenusOfUser(currentUserId);
		if (userDailyMenus == null || userDailyMenus.size() == 0) {
			return fastMenuDtos;
		}
		List<Menu> userMenus = permissionService.menusOfUser(currentUserId);
		for(int i=0;i<userDailyMenus.size();i++)
		{
			String menuCode = userDailyMenus.get(i).getMenuCode();
			FastMenuDto fastMenuDto = createFastMenuDto(getMenuByCode(userMenus,menuCode));
			fastMenuDto.setDailyMenuId(userDailyMenus.get(i).getDailyMenuId());
			fastMenuDtos.add(fastMenuDto);
		}
		return fastMenuDtos;
	}
	
	private Menu getMenuByCode(List<Menu> userMenus,String memuCode) {
		Menu menu=new Menu();
		if(userMenus!=null&&userMenus.size()>0)
		{
			List<Menu> menus = userMenus.stream().filter(p->p.getMenuCode().equals(memuCode)).collect(Collectors.toList());
			menu=menus.get(0);
		}
		return menu;
	}
	

    @Override
	@RequestMapping(value = "/saveUserFastMenu")
	public void saveUserFastMenu(String userId, String oldMenus, String newMenus) throws Exception {
		JSONArray oldMenusArry = JSONObject.parseArray(oldMenus);
		JSONArray newMenusArry = JSONObject.parseArray(newMenus);
		// 如果没有旧菜单，则全部新加
		if (oldMenusArry == null || oldMenusArry.size() == 0) {
			// 如果新菜单不为空，则全部新加
			if (newMenusArry != null && newMenusArry.size() > 0) {
				createDailyMenus(userId, newMenusArry);
			}
		} else {
			// 新的菜单为空，则全部删除
			if (newMenusArry == null || newMenusArry.size() == 0) {
				removeDailyMenus(oldMenusArry);
			} else// 既有旧菜单，又有新菜单
			{
				Map<String, String> oldMenuMp=buildOldMenuMp(oldMenusArry);
				List<String> oldMenuCodes = new ArrayList<String>(oldMenuMp.keySet());
				createNewDailyMenus(userId,oldMenuCodes,newMenusArry);
				removeOldDailyMenus(oldMenuMp,newMenusArry);
			}
		}
	}

	/**
	 * 构建旧菜单map,key为menuCode，value为菜单dailyMenuId
	 * 
	 * @param oldMenusArry
	 * @return
	 */
	private Map<String, String> buildOldMenuMp(JSONArray oldMenusArry) {
		Map<String, String> oldMenuMp = new HashMap<String, String>();
		for (Object object : oldMenusArry) {
			JSONObject obj = (JSONObject) object;
			String dailyMenuId = obj.getString("dailyMenuId");
			String menuCode = obj.getString("menuCode");
			oldMenuMp.put(menuCode, dailyMenuId);
		}
		return oldMenuMp;
	}

	/**
	 * 创建新菜单，要判断在旧菜单表中可存在，存在则pass，不存在则增加
	 * 
	 * @param userId
	 * @param oldMenus
	 * @param newMenusArry
	 * @throws Exception
	 */
	private void createNewDailyMenus(String userId, List<String> oldMenus, JSONArray newMenusArry) throws Exception {
		for (Object object : newMenusArry) {
			String newMenu = object.toString();
			if (!oldMenus.contains(newMenu)) {
				createDailyMenu(userId, newMenu);
			}
		}
	}

	private void removeOldDailyMenus(Map<String, String> oldMenus, JSONArray newMenusArry) throws Exception {
		List<String> oldMenuCodes = new ArrayList<String>(oldMenus.keySet());
		for (String str : oldMenuCodes) {
           if(!newMenusArry.contains(str))
           {
        	   removeDailyMenu(oldMenus.get(str));
           }
		}
	}

	private void createDailyMenus(String userId, JSONArray newMenusArry) throws Exception {
		for (Object object : newMenusArry) {
			createDailyMenu(userId, object.toString());
		}
	}

	private void createDailyMenu(String userId, String menuCode) throws Exception {
		SysDailyMenu sysDailyMenu = new SysDailyMenu();
		sysDailyMenu.setMenuCode(menuCode);
		sysDailyMenu.setUserId(userId);
		homeService.createDailyMenu(sysDailyMenu);
	}

	private void removeDailyMenus(JSONArray oldMenusArry) throws Exception {
		for (Object object : oldMenusArry) {
			JSONObject obj = (JSONObject) object;
			String dailyMenuId = obj.getString("dailyMenuId");
			removeDailyMenu(dailyMenuId);
		}
	}

	private void removeDailyMenu(String dailyMenuId) {
		homeService.removeDailyMenu(dailyMenuId);
	}
	/**
	 * 查询该用户当月登录次数
	 * @param userId
	 * @return
	 */
	@Override
	@RequestMapping(value = "/loginCountCurMonthOfUser")
	public int loginCountCurMonthOfUser(String currentUserId){
		return dutyService.loginCountCurMonthOfUser(currentUserId);
	}
	
	
	/**
	 * 查询指定用户自注册以来总的登陆次数
	 * @param userId
	 * @return
	 */
	@Override
	@RequestMapping(value = "/loginTotalOfUser")
	public int loginTotalOfUser(String currentUserId){
		return dutyService.loginTotalOfUser(currentUserId);
	}
	
	/**
	 * 查询用户的登录相关统计信息
	 */
	@Override
	@RequestMapping(value = "/getLoginTotalOfUser")
	public Map getLoginTotalOfUser(String currentUserId) {
		Map map=new HashMap();
		int loginCountCurMonth = dutyService.loginCountCurMonthOfUser(currentUserId);
		int loginTotal = dutyService.loginTotalOfUser(currentUserId);
		map.put("loginCountCurMonth", loginCountCurMonth);
		map.put("loginTotal", loginTotal);
		return map;
	}

}
