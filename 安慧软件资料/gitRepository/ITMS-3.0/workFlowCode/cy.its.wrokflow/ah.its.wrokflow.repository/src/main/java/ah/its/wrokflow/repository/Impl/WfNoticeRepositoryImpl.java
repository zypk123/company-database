package ah.its.wrokflow.repository.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.repository.WfNoticeRepositoryI;
import ah.its.wrokflow.repository.dao.WfNotice;
import ah.its.wrokflow.repository.domain.WfNoticeMapper;

/**
* @Title: WfNoticeRepositoryImpl.java 
* @Package ah.its.wrokflow.repository.Impl 
* @Description: 消息通知处理类
* @author lil@cychina.cn
* @date 2016年5月28日 下午4:33:15 
* @version V1.0   
 */
@Service
public class WfNoticeRepositoryImpl implements WfNoticeRepositoryI {

	
	@Autowired
	private WfNoticeMapper wfNoticeMapper;
	
	@Override
	public List<WfNotice> queryNoticesByRecord(WfNotice notice) {
		// TODO Auto-generated method stub
		return wfNoticeMapper.selectTop15Data(notice);
	}

	@Override
	public int insertBatch(Map map) {
		return wfNoticeMapper.insertBatch(map);
	}

}
