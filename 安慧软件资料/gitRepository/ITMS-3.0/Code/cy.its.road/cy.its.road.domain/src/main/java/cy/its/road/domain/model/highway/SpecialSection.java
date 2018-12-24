package cy.its.road.domain.model.highway;

public class SpecialSection {
    private String specialSectionId;

    private String roadId;

    private String specialSectionName;

    private String beginSiteAddress;

    private String endSiteAddress;

    private String specialSectionLength;

    private String specialSectionType;

    private String signalBoardNum;

    private String videoIdList;

    private String ledIdList;

    public String getSpecialSectionId() {
        return specialSectionId;
    }

    public void setSpecialSectionId(String specialSectionId) {
        this.specialSectionId = specialSectionId;
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public String getSpecialSectionName() {
        return specialSectionName;
    }

    public void setSpecialSectionName(String specialSectionName) {
        this.specialSectionName = specialSectionName;
    }

    public String getBeginSiteAddress() {
        return beginSiteAddress;
    }

    public void setBeginSiteAddress(String beginSiteAddress) {
        this.beginSiteAddress = beginSiteAddress;
    }

    public String getEndSiteAddress() {
        return endSiteAddress;
    }

    public void setEndSiteAddress(String endSiteAddress) {
        this.endSiteAddress = endSiteAddress;
    }

    public String getSpecialSectionLength() {
        return specialSectionLength;
    }

    public void setSpecialSectionLength(String specialSectionLength) {
        this.specialSectionLength = specialSectionLength;
    }

    public String getSpecialSectionType() {
        return specialSectionType;
    }

    public void setSpecialSectionType(String specialSectionType) {
        this.specialSectionType = specialSectionType;
    }

    public String getSignalBoardNum() {
        return signalBoardNum;
    }

    public void setSignalBoardNum(String signalBoardNum) {
        this.signalBoardNum = signalBoardNum;
    }

    public String getVideoIdList() {
        return videoIdList;
    }

    public void setVideoIdList(String videoIdList) {
        this.videoIdList = videoIdList;
    }

    public String getLedIdList() {
        return ledIdList;
    }

    public void setLedIdList(String ledIdList) {
        this.ledIdList = ledIdList;
    }
}