package cy.its.syscfg.repository.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.bus.EventBus;
import cy.its.com.constant.ConstValue;
import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.criteria.NoticeCriteria;
import cy.its.syscfg.domain.model.home.SysNotice;
import cy.its.syscfg.domain.repository.home.INoticeRepository;
import cy.its.syscfg.mybatis.client.SysNoticeMapper;


@Service
public class NoticeRepository implements INoticeRepository {

	@Autowired
	SysNoticeMapper sysNoticeMapper;

	@Autowired
	EventBus eventBus;

	@Override
	public SysNotice aggregateOfId(String id) throws Exception {
		return sysNoticeMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(SysNotice obj) {
		sysNoticeMapper.insertSelective(obj);
		return obj.getIdentityId();
	}

	@Override
	public int delete(String id) {
		return sysNoticeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(SysNotice obj) {
		return sysNoticeMapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public List<SysNotice> findNotices(NoticeCriteria criteria) {
		Map<String, Object> p = ParamUtil.parseParams(criteria);
		createOrgParam(criteria, p);
		return sysNoticeMapper.selectNotices(p);
	}

	private void createOrgParam(NoticeCriteria criteria, Map<String, Object> p) {
		if (!StringUtil.isNullOrEmpty(criteria.userOrgPrivilegeCode)) {
			if (criteria.userOrgPrivilegeCode.length() > 2) {
				List<String> lst = new ArrayList<String>();
				String tmp = criteria.userOrgPrivilegeCode.substring(0, criteria.userOrgPrivilegeCode.length() - 2);				
				while (true) {
					lst.add(tmp);					
					if(tmp.length() == 2){
						break;
					}else{
					   tmp = tmp.substring(0, tmp.length() - 2);
					}
				}
				p.put("orgPrivCodeList", lst);
			}
		}
	}

	@Override
	public int countNotices(NoticeCriteria criteria) {
		Map<String, Object> p = ParamUtil.parseParams(criteria);
		createOrgParam(criteria, p);
		return sysNoticeMapper.countNotices(p);
	}

	@Override
	public void sysNoticeChanged() {
		eventBus.publish(ConstValue.ROUTE_KEY_HOME_CHANGED_NOTICE, "");
	}

}
