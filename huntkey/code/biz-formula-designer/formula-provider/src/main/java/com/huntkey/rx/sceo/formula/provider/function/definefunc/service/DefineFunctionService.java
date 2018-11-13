package com.huntkey.rx.sceo.formula.provider.function.definefunc.service;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.model.TfmFunctionCompiled;
import com.huntkey.rx.sceo.formula.common.model.TfmFunctionDefinition;

import java.util.List;

/**
 * @author nidongx on 2017/7/26.
 */
public interface DefineFunctionService {

    /**
     * 查询自定义函数列表
     *
     * @param classifyId
     * @param fundName
     * @param fundState
     * @param sortName
     * @param sortOrder
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pagination<TfmFunctionDefinition> queryDefineFunction(String classifyId, String fundName, String fundState, String sortName, String sortOrder, int pageNum, int pageSize);


    /**
     * 新建自定义函数
     *
     * @param funcDefinition
     * @param fileName
     * @param sourceCode
     * @param classFullName
     * @param classifyCode
     * @param jarPath
     * @return
     */
    String createCustomizeFunction(TfmFunctionDefinition funcDefinition, String fileName,
                                   String sourceCode, String classFullName, String classifyCode, String jarPath);

    /**
     * 根据ID查找自定义函数编译数据
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    TfmFunctionCompiled getCompileFunByCompileId(String funcId);

    /**
     * 根据ID查找自定义函数定义数据
     *
     * @param fundId
     * @return TfmFunctionCompiled
     */
    TfmFunctionDefinition getDefineFunByDefineId(String fundId);

    /**
     * 更新自定义函数
     *
     * @param funcDefinition
     * @param fileName
     * @param sourceCode
     * @param classFullName
     * @param classifyCode
     * @param jarPath
     * @return
     */
    String updateCustomizeFunction(TfmFunctionDefinition funcDefinition, String fileName,
                                   String sourceCode, String classFullName, String classifyCode, String jarPath);

    /**
     * 删除自定义函数
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    int deleteCustomizeFunction(String funcId);

    /**
     * 根据函数分类ID查找自定义函数
     *
     * @param classifyId
     * @return
     */
    List<TfmFunctionDefinition> getFunctionDefinitionByClassifyId(String classifyId);

    /**
     * 根据函数id查找函数描述
     *
     * @param fundId
     * @return
     */
    FunctionDescriber getFunctionDescriberById(String fundId);

    /**
     * 检查修改函数时，函数名是否重复
     *
     * @param fundId
     * @param fundFunName
     * @return
     */
    int checkFuncName(String fundId, String fundFunName);

}
