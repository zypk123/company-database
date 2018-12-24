package cy.its.service.standardization.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.standardization.dictionary.dao.IVehBrandDAO;
import cy.its.service.standardization.dictionary.model.VehBrand;

@Export
public class VehBrandCache extends Cache {

	@Import
	IVehBrandDAO vehBrandDAO;

	private Map<String, VehBrand> cache = new HashMap<String, VehBrand>();

	@Override
	void load(DbAccess dbAccess) throws Exception {
		LogManager.info("加载车辆品牌信息缓存开始");
		List<VehBrand> brands = vehBrandDAO.selectVehBrand(dbAccess);

		if (brands == null) {
			return;
		}

		synchronized (cache) {
			cache.clear();
			for (VehBrand brand : brands) {
				if (!cache.containsKey(brand.getBrandCode())) {
					cache.put(brand.getBrandCode(), brand);
				}
			}
		}

		LogManager.info("加载车辆品牌信息缓存完成");
	}

	@Override
	List<String> getRelatedRouteKeys() {
		return null;
	}

	public VehBrand get(String brandCode) {
		synchronized (cache) {
			return cache.get(brandCode);
		}
	}

	public VehBrand get(String brand, String subBrand) {
		
		if (StringUtil.isNullOrEmpty(brand)) {
			return null;
		}

		if (!StringUtil.isNullOrEmpty(subBrand)) {
			return get(brand + ConstValue.COMMA + subBrand);
		} else {
			return get(brand);
		}
	}
}
