package cy.its.device.mybatis.model;

public class DynamicDataPathDetail {
    private String deviceSysNbr;

    private Integer svrDelay;

    private Integer afsvrDelay;

    private Integer imDelay;

    private Integer preDelay;

    private Integer afDelay;

    private Integer upd0Delay;

    private Integer avgDelay;

    private String deviceName;

    public String getDeviceSysNbr() {
        return deviceSysNbr;
    }

    public void setDeviceSysNbr(String deviceSysNbr) {
        this.deviceSysNbr = deviceSysNbr;
    }

    public Integer getSvrDelay() {
        return svrDelay;
    }

    public void setSvrDelay(Integer svrDelay) {
        this.svrDelay = svrDelay;
    }

    public Integer getAfsvrDelay() {
        return afsvrDelay;
    }

    public void setAfsvrDelay(Integer afsvrDelay) {
        this.afsvrDelay = afsvrDelay;
    }

    public Integer getImDelay() {
        return imDelay;
    }

    public void setImDelay(Integer imDelay) {
        this.imDelay = imDelay;
    }

    public Integer getPreDelay() {
        return preDelay;
    }

    public void setPreDelay(Integer preDelay) {
        this.preDelay = preDelay;
    }

    public Integer getAfDelay() {
        return afDelay;
    }

    public void setAfDelay(Integer afDelay) {
        this.afDelay = afDelay;
    }

    public Integer getUpd0Delay() {
        return upd0Delay;
    }

    public void setUpd0Delay(Integer upd0Delay) {
        this.upd0Delay = upd0Delay;
    }

    public Integer getAvgDelay() {
        return avgDelay;
    }

    public void setAvgDelay(Integer avgDelay) {
        this.avgDelay = avgDelay;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}