package cy.its.device.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.DailyMaintainCriteria;
import cy.its.device.domain.model.DailyMaintain;
import cy.its.device.domain.repository.maintain.IDailyMaintainRepository;
import cy.its.device.mybatis.client.DailyMaintainMapper;
import cy.its.platform.common.utils.SqlHelper;

@Service
public class DailyMaintainRepository implements IDailyMaintainRepository {
	
	@Autowired
	DailyMaintainMapper dailyMaintainMapper;

	public DailyMaintain aggregateOfId(String id) throws Exception {
		return dailyMaintainMapper.selectByPrimaryKey(id);
	}

	public String save(DailyMaintain obj) {
		obj.setDailyMaintenanceId(StringUtil.generateUUID());
		dailyMaintainMapper.insertSelective(obj);
		return obj.getDailyMaintenanceId();
	}

	public int delete(String id) {
		return dailyMaintainMapper.deleteByPrimaryKey(id);
	}

	public int update(DailyMaintain obj) {
		return dailyMaintainMapper.updateByPrimaryKeySelective(obj);
	}

	public List<DailyMaintain> findDailyMaintains(DailyMaintainCriteria maintainCriteria) {
		PageHelper.startPage(maintainCriteria.getPageNum(), maintainCriteria.getPageSize());
		if(!StringUtil.isNullOrEmpty(maintainCriteria.getOrderName())){
			PageHelper.orderBy(maintainCriteria.getOrderName() + " " + maintainCriteria.getOrderType());
		}
		Page<DailyMaintain> page = (Page<DailyMaintain>) dailyMaintainMapper.selectDailyMaintains(ParamUtil.parseParams(maintainCriteria));
		maintainCriteria.setTotal(page.getTotal());
		return page.getResult();
	}

	public int countDailyMaintains(DailyMaintainCriteria criteria) {
		return dailyMaintainMapper.countDailyMaintains(
				ParamUtil.parseParams(criteria));
	}
}
