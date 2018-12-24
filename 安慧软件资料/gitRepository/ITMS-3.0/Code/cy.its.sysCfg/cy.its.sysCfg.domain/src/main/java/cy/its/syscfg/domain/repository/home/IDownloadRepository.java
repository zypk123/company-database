package cy.its.syscfg.domain.repository.home;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.criteria.DownloadCriteria;
import cy.its.syscfg.domain.model.home.SysDownload;

public interface IDownloadRepository extends IRepository<SysDownload> {

	List<SysDownload> allDownloads();

	void downloadChanged();

	List<SysDownload> findDownloads(DownloadCriteria criteria);

}
