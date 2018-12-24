/**
 * @Title: ISectionRepository.java
 * @Package cy.its.road.domain.repository.section
 * @Description: ��������������Repository�ӿ�
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015��10��29�� ����9:26:45
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: ���ճ�Զ��Ϣ�������޹�˾
 * Copyright: Copyright (c) 2015 
 */
package cy.its.device.domain.repository.site;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.model.site.Section;

/**
 * @ClassName: ISectionRepository
 * @Description: 定义断面领域层中Repository接口
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年10月29日 上午9:26:45
 *
 */
public interface ISectionRepository  extends IRepository<Section> {
	
	 List<Section> findSectionBySiteId(String siteId);
	
	 /**
	  * 根据点位列表查询断面信息
	  * @param siteIds
	  */
	 List<Section> findSectionBySiteIds(List<String> siteIds);

	 int countSectionBySiteIds(List<String> siteIds);	 
}
