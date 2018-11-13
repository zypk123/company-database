package ${classifyCode?substring(0,classifyCode?last_index_of("."))};

import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.engine.function.Function;

/**
 * ${classifyName}超类
 */
public abstract class ${classifyCode?substring(classifyCode?last_index_of(".")+1)} extends Function {

    @Override
    public FunctionDescriber buildFunctionDescriber() {
        FunctionDescriber describer = new FunctionDescriber();
        describer.setFunClassify("${classifyCode?substring(0,classifyCode?last_index_of("."))?substring(((classifyCode?substring(0,classifyCode?last_index_of(".")))?last_index_of(".")+1))}");
        describer.setClassifyDesc("${classifyName}");
        describer.setFunName(this.getClass().getSimpleName());
        buildParamsDesc(describer);
        return describer;
    }
}