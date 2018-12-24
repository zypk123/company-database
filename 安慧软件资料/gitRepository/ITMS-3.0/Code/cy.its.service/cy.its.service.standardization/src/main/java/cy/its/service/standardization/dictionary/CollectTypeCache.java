package cy.its.service.standardization.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cy.its.service.common.config.ITSConfig;
import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.log.LogManager;

@Export
public class CollectTypeCache extends Cache {

	Map<String, String> mapCollect = new HashMap<String, String>();

	@Override
	void load(DbAccess dbAccess) throws Exception {
		LogManager.info("加载采集类型缓存[从配置文件]开始");
		String collectType = ITSConfig.findValue("DeviceType_CollectType");
		String[] s1 = collectType.split(";");

		synchronized (mapCollect) {
			for (String s : s1) {
				String[] s2 = s.split("-");
				String[] devTypes = s2[0].split(",");
				String cType = s2[1];

				for (String dt : devTypes) {
					if (!mapCollect.containsKey(dt)) {
						mapCollect.put(dt, cType);
					}
				}

			}
		}

		LogManager.info("加载采集类型缓存[从配置文件]完成");
	}

	@Override
	List<String> getRelatedRouteKeys() {
		return null;
	}

	public String getCollectType(String deviceType) {
		synchronized (mapCollect) {
			return mapCollect.get(deviceType);
		}
	}
}
