package com.huntkey.rx.sceo.formula.common.function;

import com.huntkey.rx.sceo.formula.common.constant.FunInOrOutType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfei on 2017/7/18.
 */
public class ParamType implements Serializable {

    private static final long serialVersionUID = -4107932455658844175L;

    private int seq;
    private ParamOrderType paramOrderType;
    private OptionType option;
    private List<FunInOrOutType> types = new ArrayList<FunInOrOutType>();

    public ParamType(int seq, ParamOrderType paramOrderType, OptionType option) {
        this.seq = seq;
        this.paramOrderType = paramOrderType;
        this.option = option;
    }

    public OptionType getOption() {
        return option;
    }



    public void setOption(Object option) {
        this.option = OptionType.getTypeByName(option.toString());
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public ParamOrderType getParamOrderType() {
        return paramOrderType;
    }

    public void setParamOrderType(Object paramOrderType) {
        this.paramOrderType = ParamOrderType.getTypeByName(paramOrderType.toString());
    }

    public void setParamOrderType(String paramOrderType) {
        this.paramOrderType = ParamOrderType.getTypeByName(paramOrderType);
    }

    public ParamType addTypes(FunInOrOutType... types) {

        for (FunInOrOutType fiot : types) {
            this.types.add(fiot);
        }

        return this;
    }

    public List<FunInOrOutType> getTypes() {
        return types;
    }

    public void setTypes(List<FunInOrOutType> types) {
        this.types = types;
    }


}
