package cy.its.device.repository.site;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.PackageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.bus.EventBus;
import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.data.SiteData;
import cy.its.device.domain.criteria.PassSiteCriteria;
import cy.its.device.domain.criteria.SiteCriteria;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.repository.site.ISiteRepository;
import cy.its.device.mybatis.client.SiteMapper;
import cy.its.platform.common.utils.SqlHelper;


@Service
public class SiteRepository implements ISiteRepository {

	@Autowired
	SiteMapper siteMapper;
	
	@Autowired
	SiteData siteData;	

	@Autowired
	EventBus eventBus;
	

	public Site aggregateOfId(String id) throws Exception {
		return siteData.dataOfId(id);
	}
	
	public Site selectByCode(String siteCode){
		return siteMapper.selectByCode(siteCode);
	}

	public String save(Site obj) {
		obj.setSiteId(StringUtil.generateUUID());
		siteMapper.insertSelective(obj);		
		return obj.getSiteId();
	}

	public int delete(String id) {
		return siteMapper.deleteByPrimaryKey(id);
	}

	public int update(Site obj) {
		return siteMapper.updateByPrimaryKey(obj);
	}

	public List<Site> findSites(SiteCriteria criteria) {
		PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
		if(!StringUtil.isNullOrEmpty(criteria.getOrderName())){
			PageHelper.orderBy(criteria.getOrderName() + " " + criteria.getOrderType());
		}
		Page<Site> page = (Page<Site>) siteMapper.selectSites(ParamUtil.parseParams(criteria));
		criteria.setTotal(page.getTotal());
		return page.getResult();
	}
	
	@Override
	public int countSites(SiteCriteria criteria) {
		return siteMapper.countSites(ParamUtil.parseParams(criteria));
	}
	
	@Override
	public void siteChanged() {
		eventBus.publish(siteData.getTopic(), "");
	}

	@Override
	public List<Site> findPassSites(PassSiteCriteria criteria) {
		Map<String, Object> paramMap = ParamUtil.parseParams(criteria);
		return siteMapper.selectPassSites(paramMap);
	}

	@Override
	public List<Site> findVideoSites(String roadType, String roadId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roadType", roadType);
		paramMap.put("roadId", roadId);
		return siteMapper.selectVideoSites(paramMap);
	}

	
}
