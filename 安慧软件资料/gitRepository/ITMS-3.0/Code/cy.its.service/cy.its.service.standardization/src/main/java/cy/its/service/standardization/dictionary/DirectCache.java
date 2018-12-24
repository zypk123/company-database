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
import cy.its.service.standardization.dictionary.dao.ISectionDAO;
import cy.its.service.standardization.dictionary.model.Section;

@Export
public class DirectCache extends Cache {

	HashMap<String, Section> cache = new HashMap<String, Section>();

	@Import
	ISectionDAO sectionDAO;

	String KEY_FMT = "%s-%s";

	@Override
	void load(DbAccess dbAccess) throws Exception {

		LogManager.info("加载方向信息缓存开始");

		List<Section> lstS = sectionDAO.selectSections(dbAccess);

		if (lstS == null) {
			return;
		}

		synchronized (cache) {
			cache.clear();
			lstS.forEach(d -> {
				if (!StringUtil.isNullOrEmpty(d.getDirectionName())) {
					String key = String.format(KEY_FMT, d.getSiteId(), d.getDirectionName());
					if (!cache.containsKey(key)) {
						cache.put(key, d);
					}
				}
			});
		}

		LogManager.info("加载方向信息缓存完成");
	}

	@Override
	List<String> getRelatedRouteKeys() {
		return Arrays.asList(ConstValue.ROUTE_KEY_CACHECHANGE_ROAD, ConstValue.ROUTE_KEY_CACHECHANGE_SITE);
	}

	public Section getSection(String siteId, String directName) {
		synchronized (cache) {
			return cache.get(String.format(KEY_FMT, siteId, directName));
		}
	}

	public String getDirectType(String siteId, String directName) {
		Section s = getSection(siteId, directName);
		return s != null ? s.getDirectionType() : StringUtil.EMPTY_STR;
	}
}
