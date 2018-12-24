package cy.its.syscfg.repository.home;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.bus.EventBus;
import cy.its.com.constant.ConstValue;
import cy.its.com.util.ParamUtil;
import cy.its.syscfg.domain.criteria.DownloadCriteria;
import cy.its.syscfg.domain.model.home.SysDownload;
import cy.its.syscfg.domain.repository.home.IDownloadRepository;
import cy.its.syscfg.mybatis.client.SysDownloadMapper;

@Service
public class DownloadRepository implements IDownloadRepository {

	@Autowired
	SysDownloadMapper sysDownloadMapper;

	@Autowired
	EventBus eventBus;
	
	@Override
	public SysDownload aggregateOfId(String id) throws Exception {
		return sysDownloadMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(SysDownload obj) {
		sysDownloadMapper.insertSelective(obj);
		return obj.getIdentityId();
	}

	@Override
	public int delete(String id) {
		return sysDownloadMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(SysDownload obj) {
		return sysDownloadMapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public List<SysDownload> allDownloads() {
		return sysDownloadMapper.selectAll();
	}

	@Override
	public void downloadChanged() {
		eventBus.publish(ConstValue.ROUTE_KEY_HOME_CHANGED_DOWNLOAD, "");
	}

	@Override
	public List<SysDownload> findDownloads(DownloadCriteria criteria) {
		 Map<String, Object> p = ParamUtil.parseParams(criteria);
		if(criteria.getNeedTotal()){
			criteria.setTotal(sysDownloadMapper.countDownloads(p));
		}
		return sysDownloadMapper.selectDownloads(p);
	}

}
