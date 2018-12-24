package cy.its.road.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.road.domain.criteria.ServiceAreaCriteria;
import cy.its.road.domain.criteria.SpecialSectionCriteria;
import cy.its.road.domain.criteria.ServiceResourceCriteria;
import cy.its.road.domain.model.highway.ServiceArea;
import cy.its.road.domain.model.highway.SpecialSection;
import cy.its.road.domain.model.highway.ServiceResource;
import cy.its.road.domain.repository.highway.IServiceAreaRepository;
import cy.its.road.domain.repository.highway.IServiceResourceRepository;
import cy.its.road.domain.repository.highway.ISpecialSectionRepository;
import cy.its.road.domain.service.IHighwayService;

/**
 * 高速相关服务
 * @author STJ
 *
 */
@Service
public class HighwayService implements IHighwayService {

	@Autowired
	IServiceAreaRepository serviceAreaRepository;
	
	@Autowired
	IServiceResourceRepository serviceResourceRepository;
	
	@Autowired
	ISpecialSectionRepository specialSectionRepository;
	

	/**
	 * 查询符合条件的的高速服务区列表
	 * 
	 * @param highwayServiceAreaCriteria
	 *            查询条件
	 * @return 高速服务区列表
	 */
	public List<ServiceArea> findServiceAreas(
			ServiceAreaCriteria serviceAreaCriteria) {
		if(serviceAreaCriteria.getNeedTotal()){
			serviceAreaCriteria.setTotal(serviceAreaRepository
			.countHighwayServiceAreas(serviceAreaCriteria));
		}
		
		return serviceAreaRepository
				.findHighwayServiceAreas(serviceAreaCriteria);
	}

	/**
	 * 查询指定的高速服务区信息
	 * 
	 * @param highwayServiceAreaId
	 *            高速服务区唯一标识
	 * @return 高速服务区信息
	 * @throws Exception 
	 */
	public ServiceArea serviceAreaOfId(String highwayServiceAreaId) throws Exception {
		return serviceAreaRepository.aggregateOfId(highwayServiceAreaId);
	}

	/**
	 * 创建新高速服务区
	 * 
	 * @param serviceArea
	 *            高速服务区信息
	 */
	public void saveServiceArea(ServiceArea serviceArea) {
		serviceAreaRepository.save(serviceArea);
	}

	/**
	 * 更新高速服务区
	 * 
	 * @param serviceArea
	 *            服务区信息
	 */
	public void updateServiceArea(ServiceArea serviceArea) {
		serviceAreaRepository.update(serviceArea);
	}

	/**
	 * 删除指定的高速服务区
	 * 
	 * @param serviceAreaId
	 *            高速服务区唯一标识
	 */
	public void deleteServiceArea(String serviceAreaId) {
		serviceAreaRepository.delete(serviceAreaId);
	}

	/**
	 * 查询符合条件的服务资源信息列表
	 * 
	 * @param resourceCriteria
	 *            查询条件
	 * @return 服务资源信息列表
	 */
	public List<ServiceResource> findServiceResources(
			ServiceResourceCriteria resourceCriteria) {
		if(resourceCriteria.getNeedTotal()){
			resourceCriteria.setTotal(serviceResourceRepository
			.countServiceResources(resourceCriteria));
		}
		return serviceResourceRepository.findServiceResources(resourceCriteria);
	}

	/**
	 * 查询指定的服务资源
	 * 
	 * @param serviceResourceId
	 *            服务资源唯一标识
	 * @return 服务资源
	 * @throws Exception 
	 */
	public ServiceResource serviceResourceOfId(String serviceResourceId) throws Exception {
		return serviceResourceRepository.aggregateOfId(serviceResourceId);
	}

	/**
	 * 创建新服务资源
	 * 
	 * @param serviceResource
	 *            服务资源
	 */
	public void saveServiceResource(ServiceResource serviceResource) {
		serviceResourceRepository.save(serviceResource);
	}

	/**
	 * 更新服务资源
	 * 
	 * @param serviceResource
	 *            服务资源
	 */
	public void updateServiceResource(ServiceResource serviceResource) {
		serviceResourceRepository.update(serviceResource);
	}

	/**
	 * 删除指定的服务资源
	 * 
	 * @param serviceResourceId
	 *            服务资源唯一标识
	 */
	public void deleteServiceResource(String serviceResourceId) {
		serviceResourceRepository.delete(serviceResourceId);
	}

	/**
	 * 查询符合条件的特殊路段列表
	 * 
	 * @param specialSectionCriteria
	 *            查询条件
	 * @return 特殊路段列表
	 */
	public List<SpecialSection> findHighwaySpecialSections(
			SpecialSectionCriteria specialSectionCriteria) {
		if(specialSectionCriteria.getNeedTotal()){
			specialSectionCriteria.setTotal(specialSectionRepository
			.countHighwaySpecialSections(specialSectionCriteria));
		}
		return specialSectionRepository
				.findHighwaySpecialSections(specialSectionCriteria);
	}

	/**
	 * 查询指定的特殊路段
	 * 
	 * @param specialSectionId
	 *            特殊路段唯一标识
	 * @return 特殊路段
	 * @throws Exception 
	 */
	public SpecialSection specialSectionOfId(String specialSectionId) throws Exception {
		return specialSectionRepository.aggregateOfId(specialSectionId);
	}

	/**
	 * 创建高速特殊路段
	 * 
	 * @param specialSection
	 *            特殊路段信息
	 */
	public void saveSpecialSection(SpecialSection specialSection) {
		specialSectionRepository.save(specialSection);
	}

	/**
	 * 更新高速特殊路段
	 * 
	 * @param specialSection
	 *            特殊路段
	 */
	public void updateSpecialSection(SpecialSection specialSection) {
		specialSectionRepository.update(specialSection);
	}

	/**
	 * 删除指定的高速特殊路段
	 * 
	 * @param specialSectionId
	 *            特殊路段唯一标识
	 */
	public void deleteSpecialSection(String specialSectionId) {
		specialSectionRepository.delete(specialSectionId);
	}

}
