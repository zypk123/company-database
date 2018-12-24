package cy.its.syscfg.domain.repository.home;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.criteria.NoticeCriteria;
import cy.its.syscfg.domain.model.home.SysNotice;

public interface INoticeRepository extends IRepository<SysNotice> {

	List<SysNotice> findNotices(NoticeCriteria criteria);

	int countNotices(NoticeCriteria criteria);

	void sysNoticeChanged();

}
