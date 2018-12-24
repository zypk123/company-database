package ah.its.wrokflow.repository.domain;

import java.util.List;
import java.util.Map;

import ah.its.wrokflow.repository.dao.WfNotice;

public interface WfNoticeMapper {
    int deleteByPrimaryKey(String noticeId);

    int insert(WfNotice record);

    int insertSelective(WfNotice record);
    
    int insertBatch(Map map);

    WfNotice selectByPrimaryKey(String noticeId);

    int updateByPrimaryKeySelective(WfNotice record);

    int updateByPrimaryKey(WfNotice record);

	/** 
	* @Description: 取最新的15条通知记录
	* @param @param notice
	* @param @return  设定文件 
	* @return List<WfNotice>    返回类型 
	* @throws 
	*/
	List<WfNotice> selectTop15Data(WfNotice notice);
}