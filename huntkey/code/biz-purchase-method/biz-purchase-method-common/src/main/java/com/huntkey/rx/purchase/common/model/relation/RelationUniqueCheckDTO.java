package com.huntkey.rx.purchase.common.model.relation;

/**
 * Created by xuyf on 2018/2/10 0010.
 */
public class RelationUniqueCheckDTO {

    private String relationId;

    private String relaUscc;

    private String relaCode;

    private String relaShortName;

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getRelaUscc() {
        return relaUscc;
    }

    public void setRelaUscc(String relaUscc) {
        this.relaUscc = relaUscc;
    }

    public String getRelaCode() {
        return relaCode;
    }

    public void setRelaCode(String relaCode) {
        this.relaCode = relaCode;
    }

    public String getRelaShortName() {
        return relaShortName;
    }

    public void setRelaShortName(String relaShortName) {
        this.relaShortName = relaShortName;
    }
}
