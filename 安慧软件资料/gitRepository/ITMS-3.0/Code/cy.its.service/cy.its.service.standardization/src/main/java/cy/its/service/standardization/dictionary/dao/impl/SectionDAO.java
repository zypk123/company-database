package cy.its.service.standardization.dictionary.dao.impl;
import java.util.List;
import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.common.ioc.Export;
import cy.its.service.standardization.dictionary.dao.ISectionDAO;
import cy.its.service.standardization.dictionary.model.Section;

@Export
//@Service
public class SectionDAO implements ISectionDAO {

	@Override
	public List<Section> selectSections(DbAccess dbAccess) throws Exception {
		return dbAccess.executeDataset(Section.class, SQL);
	}

	final static String SQL = "SELECT SECTION_ID sectionId, SITE_ID siteId, LANE_NUM laneNum, HAS_EMERGENCY_LANE hasEmergencyLane, HAS_BICYCLE_LANE hasBicycleLane, "
			+ " HAS_PAVEMENT hasPavement, DIRECTION_TYPE directionType, DIRECTION_NAME directionName, ENTER_OR_EXIT enterOrExit, SECTION_MAX_FLOW sectionMaxFlow, MAX_FLOW_TIME maxFlowTime FROM T_SYS_SECTION";
}
