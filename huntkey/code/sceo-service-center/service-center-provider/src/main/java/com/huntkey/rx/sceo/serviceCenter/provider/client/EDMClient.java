package com.huntkey.rx.sceo.serviceCenter.provider.client;

import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/***********************************************************************
 * @author chenxj
 *
 * @email: kaleson@163.com											  
 *
 * @date : 2017年7月13日 下午2:58:31											 
 *
 **********************************************************************/
//@FeignClient(value = "MODELER-PROVIDER",url = "http://192.168.13.34:2002") //单机调试使用(注意不要提交此行)

//@FeignClient(value = "MODELER-PROVIDER",url = "http://10.3.98.82:2002")

@FeignClient(value = "MODELER-PROVIDER")
//@FeignClient(value = "MODELER-PROVIDER", url = "http://10.3.98.154:2002")
public interface EDMClient {
    /**
     * 根据类名取索引属性
     *
     * @param name    类名
     * @param edmdVer 类的版本
     * @return result
     */
    @RequestMapping(value = "/classes/getOrmIndexs", method = RequestMethod.GET)
    Result getOrmIndexs(@RequestParam(value = "edmdVer") String edmdVer, @RequestParam(value = "name") String name);


    /**
     * 根据类名取所有基本属性
     * http://10.3.98.154:2002/classes/getDbType?edmdVer=V1.0&name=wordlist
     *
     * @param name    类名
     * @param edmdVer 类的版本
     * @return result
     */
    @RequestMapping(value = "/classes/getOrmBaseEdmCode", method = RequestMethod.GET)
    Result getOrmBaseEdmCode(@RequestParam(value = "edmdVer") String edmdVer, @RequestParam(value = "name") String name);


    /**
     * 根据类名取所有属性集
     *
     * @param name    类名
     * @param edmdVer 类的版本
     * @param attr    属性集
     * @return result
     */
    @RequestMapping(value = "/classes/getOrmAttrEdmCode", method = RequestMethod.GET)
    Result getOrmAttrEdmCode(@RequestParam(value = "edmdVer") String edmdVer, @RequestParam(value = "name") String name, @RequestParam(value = "attr") String attr);


    /**
     * 整个edm模型，取所有属性（普通属性和属性集）的字段属性
     *
     * @param name    类名
     * @param edmdVer 类的版本
     * @param attr    属性集，如果null默认查询全部，有值代表查询属性集的属性
     * @return result
     */
    @RequestMapping(value = "/classes/getOrmFeilds", method = RequestMethod.GET)
    Result getOrmFeilds(@RequestParam(value = "edmdVer") String edmdVer, @RequestParam(value = "name") String name, @RequestParam(value = "attr") String attr);

    /**
     * 根据类名取数据库类型 data_type
     *
     * @param name    类名
     * @param edmdVer 类的版本
     * @return result
     */
    @RequestMapping(value = "/classes/getDbType", method = RequestMethod.GET)
    Result getDbType(@RequestParam(value = "edmdVer") String edmdVer, @RequestParam(value = "name") String name);


    /**
     * 根据类名取所有属性集和普通属性  属性集打平
     *
     * @param name    类名
     * @param edmdVer 类的版本
     * @param attr    属性集
     * @return result
     */
    @RequestMapping(value = "/classes/getAllEdmCode", method = RequestMethod.GET)
    Result getAllEdmCode(@RequestParam(value = "edmdVer") String edmdVer, @RequestParam(value = "name") String name, @RequestParam(value = "attr") String attr);

    /**
     * 根据简称查类名
     *
     * @param edmdVer
     * @param name
     * @return result
     */
    @RequestMapping(value = "/classes/getEdmNameByShortName", method = RequestMethod.GET)
    Result getEdmNameByShortName(@RequestParam(value = "edmdVer") String edmdVer, @RequestParam(value = "name") String name);

    /**
     * 根据类名取类简称
     *
     * @param edmdVer
     * @param name
     * @return result
     */
    @RequestMapping(value = "/classes/getEdmShortName", method = RequestMethod.GET)
    Result getEdmShortName(@RequestParam(value = "edmdVer") String edmdVer, @RequestParam(value = "name") String name);

    /**
     * 根据modeler版本和类英文名查询类的所有实体子孙类
     *
     * @param edmdVer
     * @param edmcNameEn
     * @return
     */
    @RequestMapping(value = "/classes/entity", method = RequestMethod.GET)
    Result getEntityByVersionAndEnglishName(@RequestParam(value = "edmdVer") String edmdVer,
                                            @RequestParam(value = "edmcNameEn") String edmcNameEn);

    /**
     * 查询资源类信息
     *
     * @param classId  监管树类ID
     * @param edmpCode 属性编码
     * @return
     */
    @RequestMapping(value = "/properties/values", method = RequestMethod.GET)
    Result getPropertyValue(@RequestParam(value = "classId") String classId, @RequestParam(value = "edmpCode") String edmpCode);

    /**
     * 根据EDM ID 查询edm详细信息
     *
     * @param edmId
     * @return
     */
    @RequestMapping(value = "/classes/{edmId}", method = RequestMethod.GET)
    Result getEdmByid(@PathVariable(value = "edmId") String edmId);

    /**
     * 根据类id 查询特征值字段集合和格式化样式
     *
     * @param classId
     * @return
     */
    @RequestMapping(value = {"/classFormats/getCharacterAndFormat"}, method = {RequestMethod.GET})
    Result getCharacterAndFormat(@RequestParam(value = "classId") String classId);

    /**
     * 查询引用对象的类英文名
     *
     * @param edmdVer EDM版本
     * @param name    类名
     * @return result
     */
    @RequestMapping(value = "/classes/getEdmAttrObject", method = RequestMethod.POST)
    Result getEdmAttrObject(@RequestParam(value = "edmdVer") String edmdVer, @RequestParam(value = "name") String name);

    /**
     * 查询类所有属性的data_type
     *
     * @param edmdVer EDM版本
     * @param name    类名
     * @return result
     */
    @RequestMapping(value = "/classes/getEdmDataType", method = RequestMethod.POST)
    Result getEdmDataType(@RequestParam(value = "edmdVer") String edmdVer, @RequestParam(value = "name") String name);


}
