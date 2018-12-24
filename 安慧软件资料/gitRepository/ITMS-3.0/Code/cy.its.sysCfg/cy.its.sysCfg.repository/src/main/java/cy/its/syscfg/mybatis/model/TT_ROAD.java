package cy.its.syscfg.mybatis.model;

import java.util.List;

public class TT_ROAD {
	private String ROAD_ID;
	private String ROAD_CODE;
	
	private List<TT_ROAD_SECTION> roadSections;
	
	private List<TT_ROAD_SITE> roadSites;

	public String getROAD_ID() {
		return ROAD_ID;
	}

	public void setROAD_ID(String rOAD_ID) {
		ROAD_ID = rOAD_ID;
	}

	public String getROAD_CODE() {
		return ROAD_CODE;
	}

	public void setROAD_CODE(String rOAD_CODE) {
		ROAD_CODE = rOAD_CODE;
	}

	public List<TT_ROAD_SECTION> getRoadSections() {
		return roadSections;
	}

	public void setRoadSections(List<TT_ROAD_SECTION> roadSections) {
		this.roadSections = roadSections;
	}

	public List<TT_ROAD_SITE> getRoadSites() {
		return roadSites;
	}

	public void setRoadSites(List<TT_ROAD_SITE> roadSites) {
		this.roadSites = roadSites;
	}
	
	
	
}
