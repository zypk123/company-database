package cy.its.device.data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cy.its.com.bus.EventBus;
import cy.its.com.constant.ConstValue;
import cy.its.com.util.Data;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.SiteCriteria;
import cy.its.device.domain.model.site.Site;
import cy.its.device.mybatis.client.SiteMapper;

@Service
public class SiteData extends Data<SiteMapper, Site> {

	@Autowired(required = true)
	public SiteData(@Qualifier(value = "siteMapper") SiteMapper siteMapper,
			@Qualifier(value = "eventBus") EventBus eventBus) {
		super(siteMapper, ConstValue.ROUTE_KEY_CACHECHANGE_SITE, eventBus);
	}

	@Override
	protected List<Site> readMapper(SiteMapper mapper) {
		return mapper.selectSites(null);
	}

	@Override
	protected Map<String, Site> groupList(List<Site> lstData) {
		return lstData.stream().collect(
				Collectors.toMap(Site::getSiteId, (s) -> s));
	}
	

//	public List<Site> findSites(SiteCriteria criteria) {
////		Map<String, String> orgMap = (criteria.orgIds == null || criteria.orgIds
////				.size() == 0) ? null : criteria.orgIds.stream().distinct()
////				.collect(Collectors.toMap((s) -> s, (s) -> s));
//
//		return findDatas(
//				(s) -> (StringUtil.isNullOrEmpty(criteria.siteCode) ? true
//						: criteria.siteCode.equals(s.getSiteCode()))
//						&& (StringUtil.isNullOrEmpty(criteria.siteName) ? true
//								: (StringUtil.isNullOrEmpty(s.getSiteName()) ? false
//										: s.getSiteName().contains(
//												criteria.siteName)))
//						&& (StringUtil.isNullOrEmpty(criteria.roadId) ? true
//								: criteria.roadId.equals(s.getRoadId()))
//						&& (StringUtil.isNullOrEmpty(criteria.districtCode) ? true
//								: criteria.districtCode.equals(s
//										.getDistrictCode()))
////						&& (orgMap == null ? true : orgMap.containsKey(s
////								.getOrgId()))
//						&& (StringUtil.isNullOrEmpty(criteria.orgPrvCode) ? true
//								: s.getOrgPrivilegeCode().startsWith(criteria.orgPrvCode)), null, criteria);
//	}

}
