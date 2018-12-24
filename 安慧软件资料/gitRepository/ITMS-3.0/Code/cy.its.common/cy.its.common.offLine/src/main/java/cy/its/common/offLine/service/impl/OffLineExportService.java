package cy.its.common.offLine.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.common.offLine.client.OffLineExportMapper;
import cy.its.common.offLine.domain.OffLineExport;
import cy.its.common.offLine.service.IOffLineExportService;

/**
* @Title: OffLineExportService.java 
* @Package cy.its.common.offLine.service.impl 
* @Description:离线下载处理类
* @author lil@cychina.cn
* @date 2015年11月26日 下午5:12:38 
* @version V1.0   
 */
@Service
public class OffLineExportService implements IOffLineExportService {
    
	/**
	 *注入
	 */
	@Autowired
	private  OffLineExportMapper offLineExportMapper;
	
	@Override
	public List<OffLineExport> selectAllList(Map map) {
		return offLineExportMapper.selectAllListloginName(map);
	}

	@Override
	public int insert(OffLineExport offLineExport) {
		return offLineExportMapper.insert(offLineExport);
	}

	@Override
	public int updateOffLineExport(Map map) {
		return offLineExportMapper.updateByPrimaryKey(map);
	}

	/*
	  * <p>Title: delete</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.common.offLine.service.IOffLineExportService#delete(java.lang.String)
	  */
	
	
	@Override
	public int delete(String id) {
		return offLineExportMapper.deleteByPrimaryKey(id);
	}

}
