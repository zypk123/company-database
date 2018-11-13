package com.huntkey.rx.sceo.method.provider.entity;

public class ObjVo {


    private String formulaId;
    private String methodUrl;
    private String reqType;
    private String params;
    private String execRate;

    public ObjVo(String formulaId, String methodUrl, String reqType, String params, String execRate){
        this.formulaId = formulaId;
        this.methodUrl = methodUrl;
        this.reqType = reqType;
        this.params = params;
        this.execRate = execRate;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result =  ((formulaId == null) ? 0 : formulaId.hashCode())+
                  ((methodUrl == null) ? 0 : methodUrl.hashCode())+
                  ((reqType == null) ? 0 : reqType.hashCode())+
                  ((params == null) ? 0 : params.hashCode())+
                  ((execRate == null) ? 0 : execRate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ObjVo other = (ObjVo) obj;

        if (formulaId == null) {
            if (other.formulaId != null)
                return false;
        } else if (!formulaId.equals(other.formulaId)) {
            return false;
        }

        if (methodUrl == null) {
            if (other.methodUrl != null)
                return false;
        } else if (!methodUrl.equals(other.methodUrl)) {
            return false;
        }

        if (reqType == null) {
            if (other.reqType != null)
                return false;
        } else if (!reqType.equals(other.reqType)) {
            return false;
        }

        if (params == null) {
            if (other.params != null)
                return false;
        } else if (!params.equals(other.params)) {
            return false;
        }

        if (execRate == null) {
            if (other.execRate != null)
                return false;
        } else if (!execRate.equals(other.execRate)) {
            return false;
        }


        return true;
    }


    public String getFormulaId() {
        return formulaId;
    }

    public String getMethodUrl() {
        return methodUrl;
    }

    public String getReqType() {
        return reqType;
    }

    public String getParams() {
        return params;
    }

    public String getExecRate() {
        return execRate;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public void setMethodUrl(String methodUrl) {
        this.methodUrl = methodUrl;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setExecRate(String execRate) {
        this.execRate = execRate;
    }
}
