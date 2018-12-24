package cy.its.service.standardization.dictionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.standardization.dictionary.dao.IDeviceInfoDAO;
import cy.its.service.standardization.dictionary.dao.ISectionDAO;
import cy.its.service.standardization.dictionary.model.DeviceInfo;
import cy.its.service.standardization.dictionary.model.Section;

//@Export
public class DeviceCache extends Cache {

	HashMap<String, DeviceInfo> cache = new HashMap<String, DeviceInfo>();

	@Import
	IDeviceInfoDAO deviceInfoDAO;

	@Import
	ISectionDAO sectionDAO;

	public DeviceInfo get(String deviceNo) {
		synchronized (cache) {
			return cache.get(deviceNo);
		}
	}

	@Override
	void load(DbAccess dbAccess) throws Exception {

		LogManager.info("加载设备信息缓存开始");
		
		List<DeviceInfo> lstD = deviceInfoDAO.selectDeviceInfo(dbAccess);
		List<Section> lstS = sectionDAO.selectSections(dbAccess);

		if (lstD == null || lstS == null) {
			return;
		}

		Map<String, Section> mapSection = lstS.stream().collect(Collectors.toMap(Section::getSectionId, s -> s));

		synchronized (cache) {
			cache.clear();
			lstD.forEach(d -> {
				if (!StringUtil.isNullOrEmpty(d.getDeviceNbr())) {
					d.setDirectNameAndType(mapSection);
					if (!cache.containsKey(d.getDeviceNbr())) {
						cache.put(d.getDeviceNbr(), d);
					}
				}
			});
		}
		

		LogManager.info("加载设备信息缓存完成");
	}

	@Override
	List<String> getRelatedRouteKeys() {
		return Arrays.asList(ConstValue.ROUTE_KEY_CACHECHANGE_ORG, ConstValue.ROUTE_KEY_CACHECHANGE_ROAD,
				ConstValue.ROUTE_KEY_CACHECHANGE_SITE, ConstValue.ROUTE_KEY_CACHECHANGE_DEVICESYS );
	}
}
