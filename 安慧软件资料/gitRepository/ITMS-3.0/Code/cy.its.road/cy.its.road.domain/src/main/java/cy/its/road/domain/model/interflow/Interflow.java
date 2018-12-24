package cy.its.road.domain.model.interflow;

public class Interflow {
    private String interflowId;

    private String interflowName;

    private String interflowType;

    private String districtCode;

    private String connectRoadList;

    private String mileage;

    private String remark;

    public String getInterflowId() {
        return interflowId;
    }

    public void setInterflowId(String interflowId) {
        this.interflowId = interflowId;
    }

    public String getInterflowName() {
        return interflowName;
    }

    public void setInterflowName(String interflowName) {
        this.interflowName = interflowName;
    }

    public String getInterflowType() {
        return interflowType;
    }

    public void setInterflowType(String interflowType) {
        this.interflowType = interflowType;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getConnectRoadList() {
        return connectRoadList;
    }

    public void setConnectRoadList(String connectRoadList) {
        this.connectRoadList = connectRoadList;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}