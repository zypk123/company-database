package com.huntkey.rx.sceo.formula.provider.function.functionclassify.service;

import com.huntkey.rx.sceo.formula.common.model.FtmFunctionClassify;

import java.util.List;

/**
 * 函数分类Service接口
 *
 * @author zhangyu
 * @create 2017-07-26 11:50
 **/
public interface FunctionClassifyService {

    /**
     * 新建函数分类
     *
     * @param ftmFunctionClassify
     * @return
     * @throws Exception
     */
    int createFunctionClassify(FtmFunctionClassify ftmFunctionClassify) throws Exception;

    /**
     * 通过ID获取编译后的class文件
     *
     * @param fnccId
     * @return
     * @throws Exception
     */
    byte[] getFuccClassifyBinaryById(String fnccId) throws Exception;


    /**
     * 通过ID获取函数名
     *
     * @param fnccId
     * @return
     * @throws Exception
     */
    String getFnccClassifyCodeById(String fnccId) throws Exception;

    /**
     * 删除函数分类
     *
     * @param fnccId
     * @return
     * @throws Exception
     */
    int deleteFunctionClassify(String fnccId) throws Exception;

    /**
     * 修改函数分类
     *
     * @param ftmFunctionClassify
     * @return
     * @throws Exception
     */
    int updateFunctionClassify(FtmFunctionClassify ftmFunctionClassify) throws Exception;

    /**
     * 获取函数分类列表
     *
     * @return
     */
    List<FtmFunctionClassify> getFtmFunctionClassifyList();

    /**
     * 通过id查找函数分类
     *
     * @param fnccId
     * @return
     */
    FtmFunctionClassify selectByFnccId(String fnccId);

    /**
     * 函数名称唯一性校验
     *
     * @param fnccClassifyName
     * @return
     */
    String checkNameUnique(String fnccClassifyName);

    /**
     * 函数编码唯一性校验
     *
     * @param fnccClassifyCode
     * @return
     */
    String checkCodeUnique(String fnccClassifyCode);

    /**
     * 获取有效状态的函数分类的名称
     *
     * @return
     */
    List<FtmFunctionClassify> getClassifyNameList();


}
