package com.huntkey.rx.sceo.formula.common.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouyou on 2017/11/6.
 */
public class Jobposition {

    private String jobId;

    private String rposDept;

    private String rposPpost;

    private String rposName;

    private List<Jobposition> childPosition;

    public Jobposition() {
        jobId = "";
        rposDept = "";
        rposPpost = "";
        rposName = "";
        childPosition = new ArrayList<Jobposition>();
    }

    public List<Jobposition> getChildPosition() {
        return childPosition;
    }

    public void setChildPosition(List<Jobposition> childPosition) {
        this.childPosition = childPosition;
    }

    public String getRposName() {
        return rposName;
    }

    public void setRposName(String rposName) {
        this.rposName = rposName;
    }



    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getRposDept() {
        return rposDept;
    }

    public void setRposDept(String rposDept) {
        this.rposDept = rposDept;
    }

    public String getRposPpost() {
        return rposPpost;
    }

    public void setRposPpost(String rposPpost) {
        this.rposPpost = rposPpost;
    }

    @Override
    public String toString() {
        return "jobposition{" +
                "jobId='" + jobId + '\'' +
                ", rposDept='" + rposDept + '\'' +
                ", rposPpost='" + rposPpost + '\'' +
                ", rposName=" + rposName +
                '}';
    }
}
