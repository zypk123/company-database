package ah.its.wrokflow.domain;

import java.util.List;

import ah.its.wrokflow.repository.dao.SysGroup;

/**
* @Title: SysGroupDomainI.java 
* @Package ah.its.wrokflow.domain 
* @Description: 机构group操作的领域 
* @author chengj@cychina.cn
 */
public interface SysGroupDomainI {
	/**
	 * 查询机构group
	 * @param SysGroup record
	 * @return
	 */
	List querygroups(SysGroup record);
}
