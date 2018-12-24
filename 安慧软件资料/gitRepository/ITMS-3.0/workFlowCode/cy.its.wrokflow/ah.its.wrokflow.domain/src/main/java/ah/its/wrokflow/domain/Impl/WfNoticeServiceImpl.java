package ah.its.wrokflow.domain.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.domain.WfNoticeServiceI;
import ah.its.wrokflow.repository.WfMessageRepositoryI;
import ah.its.wrokflow.repository.WfNoticeRepositoryI;
import ah.its.wrokflow.repository.dao.WfNotice;

@Service
public class WfNoticeServiceImpl implements WfNoticeServiceI {

	@Autowired
	private WfNoticeRepositoryI wfNoticeRepositoryI;
	@Override
	public List<WfNotice> queryNoticesByRecord(WfNotice notice) {
		// TODO Auto-generated method stub
		return wfNoticeRepositoryI.queryNoticesByRecord(notice);
	}

	@Override
	public int insertBatch(Map map) {
		// TODO Auto-generated method stub
		return wfNoticeRepositoryI.insertBatch(map);
	}

}
