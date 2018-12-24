package ah.its.wrokflow.repository.domain;

import java.util.List;
import java.util.Map;

import ah.its.wrokflow.repository.dao.SysApprove;
import ah.its.wrokflow.repository.dao.SysApproveFile;

public interface SysApproveFileMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysApproveFile record);

    int insertSelective(SysApproveFile record);

    SysApproveFile selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysApproveFile record);

    int updateByPrimaryKey(SysApproveFile record);

	List<SysApprove> querySystemApproveHistory(Map map);

	List<SysApproveFile> selectAllData(Map map);
}