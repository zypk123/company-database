package cy.its.syscfg.domain.repository.sysConfig;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.model.config.SysFestival;
import cy.its.syscfg.domain.model.sysCode.Code;

public interface ISysFestivalRepository{
	List<Code> findFestivalCodeTypes();
	List<SysFestival> findAllFestivals(String year,String festivalType);
    String createFestival(SysFestival sysFestival);
    int updateFestival(SysFestival sysFestival);
    int delete(String id);
}
