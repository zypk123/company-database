package cy.its.common.offLine.client;

import java.util.List;
import java.util.Map;

import cy.its.common.offLine.domain.OffLineExport;

/**
* @Title: OffLineExportMapper.java 
* @Package cy.its.common.offLine.client 
* @Description:文件离线导出任务操作类
* @author lil@cychina.cn
* @date 2015年11月26日 下午4:03:11 
* @version V1.0   
 */
public interface OffLineExportMapper {
	
    /** 
    * @Title: insert 
    * @Description: 新增任务 
    * @param @param record
    * @param @return    设定文件 
    * @return int    返回类型 
    * @throws 
    */
    int insert(OffLineExport record);

    
    /** 
    * @Title: selectByPrimaryKey 
    * @Description:查询任务
    * @param @param loginName
    * @param @return    设定文件 
    * @return List<OffLineExport>    返回类型 
    * @throws 
    */
    List<OffLineExport> selectAllListloginName(Map  map);

    
    /** 
    * @Title: updateByPrimaryKey 
    * @Description: 如果任务需要重新下载
    * @param @param recordId
    * @param @return    设定文件 
    * @return int    返回类型 
    * @throws 
    */
    int updateByPrimaryKey(Map  map);
    int deleteByPrimaryKey(String taskId);
}