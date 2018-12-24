package ah.its.wrokflow.domain.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.domain.WfMenuDomainI;
import ah.its.wrokflow.repository.WfMenuRepositoryI;
import ah.its.wrokflow.repository.dao.WfGroupMenu;
import ah.its.wrokflow.repository.dao.WfMenu;

/**
* @Title: WfMenuDomainImpl.java 
* @Package ah.its.wrokflow.domain.Impl 
* @Description: 菜单操作的领域处理类
* @author chengj@cychina.cn
* @date 2016年5月17日 上午9:23:17 
* @version V1.0   
 */
@Service
public class WfMenuDomainImpl implements WfMenuDomainI {

	@Autowired
	private WfMenuRepositoryI wfMenuRepositoryImpl;
	
	@Override
	public List<WfMenu> queryMenuByGroupId(String groupId) {
		return wfMenuRepositoryImpl.queryMenuByGroupId(groupId);
	}

	@Override
	public int addGroupMenuMapRecord(List<WfGroupMenu> records) {
		return wfMenuRepositoryImpl.addGroupMenuMapRecord(records);
	}

	@Override
	public int delGroupMenuMapRecord(String groupId) {
		return wfMenuRepositoryImpl.delGroupMenuMapRecord(groupId);
	}
}
