package cy.its.device.domain.model;

import java.util.Date;

public class DataSheet {
    private String dataSheetId;

    private String serverId;

    private String dataSheetName;

    private String totalSpace;

    private String usedSpace;

    private Date latestExtendTime;

    public String getDataSheetId() {
        return dataSheetId;
    }

    public void setDataSheetId(String dataSheetId) {
        this.dataSheetId = dataSheetId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getDataSheetName() {
        return dataSheetName;
    }

    public void setDataSheetName(String dataSheetName) {
        this.dataSheetName = dataSheetName;
    }

    public String getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(String totalSpace) {
        this.totalSpace = totalSpace;
    }

    public String getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(String usedSpace) {
        this.usedSpace = usedSpace;
    }

    public Date getLatestExtendTime() {
        return latestExtendTime;
    }

    public void setLatestExtendTime(Date latestExtendTime) {
        this.latestExtendTime = latestExtendTime;
    }
}