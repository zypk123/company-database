package com.huntkey.rx.sceo.formula.provider.function.functionclassify.dao;

import com.huntkey.rx.sceo.formula.common.model.FtmFunctionClassify;
import com.huntkey.rx.sceo.formula.common.model.FtmFunctionClassifyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface FtmFunctionClassifyMapper {

    /**
     * countByExample
     *
     * @param example
     * @return
     */
    int countByExample(FtmFunctionClassifyExample example);

    /**
     * deleteByExample
     *
     * @param example
     * @return
     */
    int deleteByExample(FtmFunctionClassifyExample example);

    /**
     * deleteByPrimaryKey
     *
     * @param fnccId
     * @return
     */
    int deleteByPrimaryKey(String fnccId);

    /**
     * insert
     *
     * @param record
     * @return
     */
    int insert(FtmFunctionClassify record);

    /**
     * insertSelective
     *
     * @param record
     * @return
     */
    int insertSelective(FtmFunctionClassify record);

    /**
     * selectByExampleWithBLOBs
     *
     * @param example
     * @return
     */
    List<FtmFunctionClassify> selectByExampleWithBLOBs(FtmFunctionClassifyExample example);

    /**
     * selectByExample
     *
     * @param example
     * @return
     */
    List<FtmFunctionClassify> selectByExample(FtmFunctionClassifyExample example);

    /**
     * selectByPrimaryKey
     *
     * @param fnccId
     * @return
     */
    FtmFunctionClassify selectByPrimaryKey(String fnccId);

    /**
     * updateByExampleSelective
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") FtmFunctionClassify record, @Param("example") FtmFunctionClassifyExample example);

    /**
     * updateByExampleWithBLOBs
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleWithBLOBs(@Param("record") FtmFunctionClassify record, @Param("example") FtmFunctionClassifyExample example);

    /**
     * updateByExample
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") FtmFunctionClassify record, @Param("example") FtmFunctionClassifyExample example);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(FtmFunctionClassify record);

    /**
     * updateByPrimaryKeyWithBLOBs
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(FtmFunctionClassify record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(FtmFunctionClassify record);

    /**
     * 通过主键删除函数分类
     *
     * @param fnccId
     * @return
     */
    int updateIsenableByPrimaryKey(String fnccId);

    /**
     * 更新编译后的二进制文件
     *
     * @param fnccId
     * @param classCode
     * @return
     */
    int updateBinary(@Param("fnccId") String fnccId, @Param("classCode") byte[] classCode);

    /**
     * 更新函数分类名称和函数描述
     *
     * @param fnccId           函数分类ID
     * @param fnccClassifyName 函数分类名称
     * @param fnccClassifyDesc 函数分类描述
     * @return
     */
    int updateClassifyNameAndDesc(@Param("fnccId") String fnccId, @Param("fnccClassifyName") String fnccClassifyName, @Param("fnccClassifyDesc") String fnccClassifyDesc);


    /**
     * 函数名称唯一性校验
     *
     * @param fnccClassifyName
     * @return
     */
    int checkNameUnique(String fnccClassifyName);

    /**
     * 函数编码唯一性校验
     *
     * @param fnccClassifyCode
     * @return
     */
    int checkCodeUnique(String fnccClassifyCode);

    /**
     * 获取有效状态的函数分类的名称
     *
     * @return
     */
    List<String> getClassifyNameList();

}