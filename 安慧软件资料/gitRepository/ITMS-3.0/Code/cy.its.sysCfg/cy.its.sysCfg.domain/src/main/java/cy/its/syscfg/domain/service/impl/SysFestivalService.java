package cy.its.syscfg.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.syscfg.domain.model.config.SysFestival;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.repository.sysConfig.ISysFestivalRepository;
import cy.its.syscfg.domain.service.ISysFestivalService;


@Service
public class SysFestivalService implements ISysFestivalService{
	
	@Autowired
	ISysFestivalRepository sysFestivalRepository;
	
	@Override
	public List<Code> findFestivalsType() {
		// TODO Auto-generated method stub
		return sysFestivalRepository.findFestivalCodeTypes();
	}

	@Override
	public List<SysFestival> findAllFestivals(String year,String festivalType) {
		return sysFestivalRepository.findAllFestivals(year,festivalType);
	}

	@Override
	public void createFestival(SysFestival sysFestival) {
		
		sysFestivalRepository.createFestival(sysFestival);
	}

	@Override
	public void updateFestival(SysFestival sysFestival) {
		sysFestivalRepository.updateFestival(sysFestival);
		
	}

	@Override
	public void removeFestival(String festivalId) {
		sysFestivalRepository.delete(festivalId);
		
	}

}
