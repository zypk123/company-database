package cy.its.service.standardization.dictionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.standardization.dictionary.dao.ISurveyUpgrade_DeviceDAO;
import cy.its.service.standardization.dictionary.model.IDeviceInfo;
import cy.its.service.standardization.dictionary.model.SurveyUpgrade_DeviceInfo;

@Export
public class SurveyUpgrade_DeviceCache extends Cache{

	HashMap<String, SurveyUpgrade_DeviceInfo> cache = new HashMap<String, SurveyUpgrade_DeviceInfo>();

	@Import
	ISurveyUpgrade_DeviceDAO surveyUpgrade_DeviceDAO;
		
	@Override
	void load(DbAccess dbAccess) throws Exception {
		
        LogManager.info("加载新设备信息缓存【应对监控服务器改造】开始");
		
		List<SurveyUpgrade_DeviceInfo> lstD = surveyUpgrade_DeviceDAO.selectDeviceInfo(dbAccess);
//		List<Section> lstS = sectionDAO.selectSections(dbAccess);

		if (lstD == null) {
			return;
		}
		synchronized (cache) {
			cache.clear();
			lstD.forEach(d -> {
				if (!StringUtil.isNullOrEmpty(d.getDeviceKey())) {
//					d.setDirectNameAndType(mapSection);
					if (!cache.containsKey(d.getDeviceKey())) {
						cache.put(d.getDeviceKey(), d);
					}
				}
			});
		}
		

		LogManager.info("加载新设备信息缓存【应对监控服务器改造】完成");
	}

	@Override
	List<String> getRelatedRouteKeys() {
		return Arrays.asList(ConstValue.ROUTE_KEY_CACHECHANGE_ORG, ConstValue.ROUTE_KEY_CACHECHANGE_ROAD,
				ConstValue.ROUTE_KEY_CACHECHANGE_SITE, ConstValue.ROUTE_KEY_CACHECHANGE_DEVICESYS );
	}

	public IDeviceInfo get(String deviceKey) {
		synchronized (cache) {
			return cache.get(deviceKey);
		}
	}

	public boolean isRegister(String deviceKey) {
		synchronized (cache) {
			return cache.containsKey(deviceKey);
		}
	}
}
