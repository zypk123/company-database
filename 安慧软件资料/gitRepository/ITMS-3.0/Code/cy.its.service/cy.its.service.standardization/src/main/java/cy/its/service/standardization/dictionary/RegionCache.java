package cy.its.service.standardization.dictionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.standardization.dictionary.dao.IRegionDAO;
import cy.its.service.standardization.dictionary.model.DeviceRegion;

@Export
public class RegionCache extends Cache {

	@Import
	IRegionDAO regionDAO;

	private Map<String, DeviceRegion> cache = new HashMap<String, DeviceRegion>();

	@Override
	void load(DbAccess dbAccess) throws Exception {

		LogManager.info("加载设备区间信息缓存开始");
		List<DeviceRegion> regions = regionDAO.selectRegions(dbAccess);
		if (regions == null) {
			return;
		}

		synchronized (cache) {
			cache.clear();
			for (DeviceRegion reg : regions) {
				if (!cache.containsKey(reg.getRegionalcode())) {
					cache.put(reg.getRegionalcode(), reg);
				}
			}
		}
		
		LogManager.info("加载设备区间信息缓存完成");
	}

	@Override
	List<String> getRelatedRouteKeys() {
		return Arrays.asList(ConstValue.ROUTE_KEY_CACHECHANGE_DEVICEREGION);
	}

	public DeviceRegion get(String regionCode) {
		synchronized (cache) {
			return cache.get(regionCode);
		}
	}
}
