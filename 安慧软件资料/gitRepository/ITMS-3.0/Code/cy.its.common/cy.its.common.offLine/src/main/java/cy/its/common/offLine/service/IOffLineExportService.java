package cy.its.common.offLine.service;

import java.util.List;
import java.util.Map;

import cy.its.common.offLine.domain.OffLineExport;

/**
* @Title: IOffLineExportService.java 
* @Package cy.its.common.offLine.service 
* @Description:离线下载，业务处理类
* @author lil@cychina.cn
* @date 2015年11月26日 下午5:07:59 
* @version V1.0   
 */
public interface IOffLineExportService {
	
	/** 
	* @Title: selectAllListLoginName 
	* @Description:获取离线下载数据
	* @param @param map
	* @param @return    设定文件 
	* @return List<OffLineExport>    返回类型 
	* @throws 
	*/
	List<OffLineExport>  selectAllList(Map map);
	
	
    /** 
    * @Title: insert 
    * @Description:插入记录
    * @param @param offLineExport
    * @param @return    设定文件 
    * @return int    返回类型 
    * @throws 
    */
    int   insert(OffLineExport offLineExport);
    
    
    /** 
    * @Title: updateOffLineExport 
    * @Description:如果需要下载任务可重新下载
    * @param @param map
    * @param @return    设定文件 
    * @return int    返回类型 
    * @throws 
    */
    int   updateOffLineExport(Map map);
    /** 
     * @Title: delete 
     * @Description:删除
     * @param @param map
     * @param @return    设定文件 
     * @return int    返回类型 
     * @throws 
     */
     int   delete(String id);
}
