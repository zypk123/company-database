package ah.its.wrokflow.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.repository.WfMenuRepositoryI;
import ah.its.wrokflow.repository.dao.WfGroupMenu;
import ah.its.wrokflow.repository.dao.WfMenu;
import ah.its.wrokflow.repository.domain.WfMenuMapper;

/**
* @Title: WfMenuRepositoryImpl.java 
* @Package ah.its.wrokflow.repository.Impl 
* @Description: 菜单操作类
* @author chengj@cychina.cn
* @date 2016年4月5日 下午6:36:13 
* @version V1.0   
 */
@Service
public class WfMenuRepositoryImpl implements WfMenuRepositoryI {

	@Autowired
	private WfMenuMapper wfMenuMapper;//用户操作接口

	@Override
	public List<WfMenu> queryMenuByGroupId(String groupId) {
		return wfMenuMapper.queryMenuByGroupId(groupId);
	}

	@Override
	public int addGroupMenuMapRecord(List<WfGroupMenu> records) {
		return wfMenuMapper.addGroupMenuMapRecord(records);
	}

	@Override
	public int delGroupMenuMapRecord(String groupId) {
		return wfMenuMapper.delGroupMenuMapRecord(groupId);
	}
}
