package cy.its.device.domain.repository.surveySystem;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.SurveySystemCriteria;
import cy.its.device.domain.model.SurveySys;

public interface ISurveySysRepository extends IRepository<SurveySys> {
	 List<SurveySys> findSurveySystems(SurveySystemCriteria criteria);

	int countSurveySystems(SurveySystemCriteria criteria);
}
