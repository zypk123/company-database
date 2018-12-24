package cy.its.syscfg.domain.repository.home;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.model.home.SysDailyMenu;

public interface IDailyMenuRepository extends IRepository<SysDailyMenu> {

	List<SysDailyMenu> dailyMenusOfUser(String userId);

	void deleteByUserPermmission();

}
