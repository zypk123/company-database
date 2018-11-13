package com.huntkey.rx.sceo.formula.provider.utils;

import com.huntkey.rx.sceo.formula.provider.engine.entity.SimpleDataProvider;
import com.huntkey.rx.sceo.formula.provider.engine.service.FormulaEngineService;
import com.huntkey.rx.sceo.formula.provider.variant.service.VariantMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author lulx on 2017/8/21 0021 下午 5:25
 */
public enum RelCondValType {

    /**
     * 常量
     */
    CONST("const", "常量", new ConstRelCOndVal()),

    /**
     * 变量
     */
    VARIABLE("variable", "变量", new VariableRelCOndVal()),

    /**
     * 类
     */
    CLASS("class", "类", new ClassRelCOndVal());

    String valType;
    String val;
    RelCondVal relCondVal;

    RelCondValType(String valType, String val, RelCondVal relCondVal) {
        this.valType = valType;
        this.val = val;
        this.relCondVal = relCondVal;
    }

    public String getValType() {
        return valType;
    }

    public RelCondVal getRelCondVal() {
        return relCondVal;
    }

    public static RelCondValType getRelCondValType(String valType) {
        RelCondValType[] types = RelCondValType.values();
        for (RelCondValType t : types) {
            if (t.getValType().equalsIgnoreCase(valType.trim())) {
                return t;
            }
        }

        throw new RuntimeException(valType + " 类型不支持");
    }
}

class ConstRelCOndVal implements RelCondVal {

    @Override
    public Object getRelCondVal(String val) {
        return val;
    }
}

@Service
@Lazy(false)
class VariableRelCOndVal implements RelCondVal {

    @Autowired
    private FormulaEngineService formulaEngineService;

    @Autowired
    private VariantMgrService variantMgrService;

    private static FormulaEngineService formulaEngineServiceHolder;
    private static VariantMgrService variantMgrServiceHolder;

    @PostConstruct
    public void init() {
        formulaEngineServiceHolder = formulaEngineService;
        variantMgrServiceHolder = variantMgrService;
    }

    @Override
    public Object getRelCondVal(String val) {
        return formulaEngineServiceHolder.variantCalc(variantMgrServiceHolder.getVariantByName(val), new SimpleDataProvider());
    }
}

class ClassRelCOndVal implements RelCondVal {

    @Override
    public Object getRelCondVal(String val) {
        return val;
    }
}
