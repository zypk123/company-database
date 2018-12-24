package cy.its.syscfg.domain.model.traffic;

public class OverRunCheckPoint {
    private String checkpointId;

    private String checkpointName;

    private String manageOrg;

    private String address;

    private String lonLat;

    private String headerName;

    private String headerPhone;

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
}