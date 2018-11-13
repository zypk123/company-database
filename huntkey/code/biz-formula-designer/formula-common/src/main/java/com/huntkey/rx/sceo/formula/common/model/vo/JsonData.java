package com.huntkey.rx.sceo.formula.common.model.vo;

/**
 * @author chenfei on 2017/5/15.
 */
public class JsonData {
    private String stat001;
    private String stat002;
    private String stat003;
    private String stat006;
    private String stat008;
    private String stat009;
    private String stat011;

    public JsonData() {
    }

    public JsonData(String stat001, String stat002, String stat003, String stat006, String stat008, String stat009, String stat011) {
        //class code
        this.stat001 = stat001;
        //object code
        this.stat002 = stat002;
        //属性id
        this.stat003 = stat003;
        //周期
        this.stat006 = stat006;
        //起始时间
        this.stat008 = stat008;
        //结束时间
        this.stat009 = stat009;
        //累计值
        this.stat011 = stat011;
    }

    public String getStat001() {
        return stat001;
    }

    public void setStat001(String stat001) {
        this.stat001 = stat001;
    }

    public String getStat002() {
        return stat002;
    }

    public void setStat002(String stat002) {
        this.stat002 = stat002;
    }

    public String getStat003() {
        return stat003;
    }

    public void setStat003(String stat003) {
        this.stat003 = stat003;
    }

    public String getStat006() {
        return stat006;
    }

    public void setStat006(String stat006) {
        this.stat006 = stat006;
    }

    public String getStat008() {
        return stat008;
    }

    public void setStat008(String stat008) {
        this.stat008 = stat008;
    }

    public String getStat009() {
        return stat009;
    }

    public void setStat009(String stat009) {
        this.stat009 = stat009;
    }

    public String getStat011() {
        return stat011;
    }

    public void setStat011(String stat011) {
        this.stat011 = stat011;
    }
}
