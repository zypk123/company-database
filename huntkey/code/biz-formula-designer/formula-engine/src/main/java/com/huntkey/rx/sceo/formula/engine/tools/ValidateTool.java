package com.huntkey.rx.sceo.formula.engine.tools;

import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lulx on 2017/6/2 0002.
 */
public class ValidateTool {

    private static HashMap<String,String> REGEX_MAP ;
    private static String REGEX_COMMON_REPSTR ;


    static {
        RegexStr regexStr = new RegexStr();
        REGEX_MAP = regexStr.getAllRegex();
        REGEX_COMMON_REPSTR = RegexStr.REGEX_COMMON_REPSTR;
    }

    public static Result validateByallRegex(String expressionStr){
        return validateStr(expressionStr,REGEX_MAP);
    }

    protected static Result validateStr(String expressionStr, HashMap<String,String> regexMap){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        if(StringUtils.isEmpty(StringUtils.trimAllWhitespace(expressionStr))){
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("函数公式不能为空");
            return result;
        }

        String str ;
        String oldExpression = expressionStr;
        while (true){
            for (Map.Entry<String,String> regex :regexMap.entrySet()){
                str = regex.getValue();
                expressionStr = expressionStr.replaceAll(" ","");
                expressionStr = expressionStr.replaceAll(str,REGEX_COMMON_REPSTR);
            }
            if("".equalsIgnoreCase(expressionStr.replaceAll(REGEX_COMMON_REPSTR,""))){
                result.setRetCode(Result.RECODE_SUCCESS);
                result.setErrMsg("校验通过");
                break;
            }
            if(oldExpression.equals(expressionStr)){
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("请检查当前正在校验函数公式："+expressionStr);
                break;
            }else{
                oldExpression = expressionStr;
            }
        }
        return result;
    }
}
