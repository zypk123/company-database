package cy.its.service.standardization.dictionary.dao;

import java.util.List;

import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.standardization.dictionary.model.Section;

public interface ISectionDAO {

	List<Section> selectSections(DbAccess dbAccess) throws Exception;

}