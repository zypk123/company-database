package cy.its.device.domain.model;

public class Server {
    private String serverId;

    private String surveySystemId;

    private String serverIp;

    private String connUserName;

    private String connPassword;

    private String otalDiskResource;

    private String usedDiskResource;

    private String totalBackupService;

    private String runningBackupService;

    private String totalDataSheet;

    private String shortageDataSheet;

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getSurveySystemId() {
        return surveySystemId;
    }

    public void setSurveySystemId(String surveySystemId) {
        this.surveySystemId = surveySystemId;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getConnUserName() {
        return connUserName;
    }

    public void setConnUserName(String connUserName) {
        this.connUserName = connUserName;
    }

    public String getConnPassword() {
        return connPassword;
    }

    public void setConnPassword(String connPassword) {
        this.connPassword = connPassword;
    }

    public String getOtalDiskResource() {
        return otalDiskResource;
    }

    public void setOtalDiskResource(String otalDiskResource) {
        this.otalDiskResource = otalDiskResource;
    }

    public String getUsedDiskResource() {
        return usedDiskResource;
    }

    public void setUsedDiskResource(String usedDiskResource) {
        this.usedDiskResource = usedDiskResource;
    }

    public String getTotalBackupService() {
        return totalBackupService;
    }

    public void setTotalBackupService(String totalBackupService) {
        this.totalBackupService = totalBackupService;
    }

    public String getRunningBackupService() {
        return runningBackupService;
    }

    public void setRunningBackupService(String runningBackupService) {
        this.runningBackupService = runningBackupService;
    }

    public String getTotalDataSheet() {
        return totalDataSheet;
    }

    public void setTotalDataSheet(String totalDataSheet) {
        this.totalDataSheet = totalDataSheet;
    }

    public String getShortageDataSheet() {
        return shortageDataSheet;
    }

    public void setShortageDataSheet(String shortageDataSheet) {
        this.shortageDataSheet = shortageDataSheet;
    }
}