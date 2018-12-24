package cy.its.trafficSituation.rest.dto;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.traffic.OverRunCheckPoint;

public class OverRunCheckPointDto {
	private String checkpointId;

    private String checkpointName;

    private String manageOrg;

    private String address;

    private String lonLat;

    private String headerName;

    private String headerPhone;
    
    private String lon;
    private String lat;

    /**
	 * getter method
	 * @return the lon
	 */
	
	public String getLon() {
		return lon;
	}

	/**
	 * setter method
	 * @param lon the lon to set
	 */
	
	public void setLon(String lon) {
		this.lon = lon;
	}

	/**
	 * getter method
	 * @return the lat
	 */
	
	public String getLat() {
		return lat;
	}

	/**
	 * setter method
	 * @param lat the lat to set
	 */
	
	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(String checkpointId) {
        this.checkpointId = checkpointId;
    }

    public String getCheckpointName() {
        return checkpointName;
    }

    public void setCheckpointName(String checkpointName) {
        this.checkpointName = checkpointName;
    }

    public String getManageOrg() {
        return manageOrg;
    }

    public void setManageOrg(String manageOrg) {
        this.manageOrg = manageOrg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLonLat() {
        return lonLat;
    }

    public void setLonLat(String lonLat) {
        this.lonLat = lonLat;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderPhone() {
        return headerPhone;
    }

    public void setHeaderPhone(String headerPhone) {
        this.headerPhone = headerPhone;
    }
    public OverRunCheckPointDto(){
    	
    }
    public OverRunCheckPointDto(OverRunCheckPoint overRunCheckPoint){
    	ObjectMapUtils.parseObject(this, overRunCheckPoint);
    	if (!StringUtil.isNullOrEmpty(overRunCheckPoint.getLonLat())) {
			String tempStr = overRunCheckPoint.getLonLat().trim();
			String[] lonlat = tempStr.substring(tempStr.indexOf("(") + 1, tempStr.indexOf(")")).split(" ");
			if (lonlat.length > 1) {
				this.setLon(lonlat[0]);
				this.setLat(lonlat[1]);
			}
		}
    }
}
