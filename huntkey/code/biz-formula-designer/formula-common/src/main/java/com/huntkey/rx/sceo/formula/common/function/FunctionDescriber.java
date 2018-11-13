package com.huntkey.rx.sceo.formula.common.function;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 函数描述符实体
 *
 * @author chenfei on 2017/6/15.
 */
public class FunctionDescriber implements Serializable{

    private static final long serialVersionUID = -5064164706946621227L;

    /**
     * 函数名称
     */
    private String funName;

    /**
     * 函数分类
     */
    private String funClassify;

    /**
     * 分类描述
     */
    private String classifyDesc;

    /**
     * 函数描述
     */
    private String funDesc;

    /**
     * 函数分类排序
     */
    private int funClassifyOrder;

    /**
     * 函数类型

     */
    private FunInOrOutType funType;

    /**
     * 函数分组内排序
     */
    private int funOrder;

    /**
     * 函数参数类型列表
     */
    protected List<ParamType> paramTypes;

    /**
     * 用户常用函数排序
     */
    private int behaivorOrder;

    public List<ParamType> getParamTypes() {

        if (null == paramTypes) {
            synchronized (this.getClass()) {
                if (null == paramTypes) {
                    paramTypes = new ArrayList<ParamType>();
                }
            }
        }

        return paramTypes;
    }

    public void addParamTypes(ParamType... pts) {

        List<ParamType> paramTypes = getParamTypes();
        for (ParamType pt : pts) {
            paramTypes.add(pt);
        }
    }

    public FunInOrOutType getFunType() {
        return funType;
    }



    public void setFunType(Object funType) {
        this.funType = FunInOrOutType.getTypeByName(((FunInOrOutType)funType).getType());
    }

    public int getFunClassifyOrder() {
        return funClassifyOrder;
    }

    public void setFunClassifyOrder(int funClassifyOrder) {
        this.funClassifyOrder = funClassifyOrder;
    }

    public int getFunOrder() {
        return funOrder;
    }

    public void setFunOrder(int funOrder) {
        this.funOrder = funOrder;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public String getFunClassify() {
        return funClassify;
    }

    public void setFunClassify(String funClassify) {
        this.funClassify = funClassify;
    }

    public String getClassifyDesc() {
        return classifyDesc;
    }

    public void setClassifyDesc(String classifyDesc) {
        this.classifyDesc = classifyDesc;
    }

    public String getFunDesc() {
        return funDesc;
    }

    public void setFunDesc(String funDesc) {
        this.funDesc = funDesc;
    }

    @Override
    public String toString() {
        return "FunctionDescriber{" +
                "funName='" + funName + '\'' +
                ", funClassify='" + funClassify + '\'' +
                ", classifyDesc='" + classifyDesc + '\'' +
                ", funDesc='" + funDesc + '\'' +
                ", funClassifyOrder=" + funClassifyOrder +
                ", funType='" + funType + '\'' +
                ", funOrder=" + funOrder +
                ", behaivorOrder=" + behaivorOrder +
                '}';
    }

    public void setBehaivorOrder(int behaivorOrder) {
        this.behaivorOrder = behaivorOrder;
    }

    public int getBehaivorOrder() {
        return behaivorOrder;
    }

}
