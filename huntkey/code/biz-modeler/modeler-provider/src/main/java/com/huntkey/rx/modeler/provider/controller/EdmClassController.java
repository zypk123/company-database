package com.huntkey.rx.modeler.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.common.model.*;
import com.huntkey.rx.modeler.common.model.vo.*;
import com.huntkey.rx.modeler.common.util.Constant;
import com.huntkey.rx.modeler.provider.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by licj on 2017/4/13.
 */
@RestController
@RequestMapping("/classes")
public class EdmClassController {

    private static Logger log = LoggerFactory.getLogger(EdmClassController.class);

    @Autowired
    public EdmClassService edmClassService;

    @Autowired
    public EdmPropertyService edmPropertyService;

    @Autowired
    public EdmAttachmentService edmAttachmentService;

    @Autowired
    public EdmLinkService edmLinkService;

    @Autowired
    public EdmIndexService edmIndexService;

    //version --yml
    @Value("${modeler.version}")
    private String version;

    /**
     * 获取类的基本信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result getClassById(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        EdmClassVO edmClassVO = edmClassService.selectById(id);
        result.setData(edmClassVO);
        return result;
    }

    /**
     * 获取类的简单基本信息，不包括行业
     *
     * @param id
     * @return
     */

    @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
    public Result getSimpleClassById(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        EdmClassVO edmClassVO = edmClassService.selectSimpleClassById(id);
        result.setData(edmClassVO);
        return result;
    }

    /**
     * 根据类id获取类型为整形与字符型的属性(供单位列表中属性选择使用)
     *
     * @param edmcId
     * @param edmpId
     * @return
     */
    @RequestMapping(value = "/edmUnits/properties", method = RequestMethod.GET)
    public Result getDataProperties(@RequestParam(value = "edmcId") String edmcId, String edmpId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmProperty> edmProperties = edmPropertyService.selectDataPropertiesByCid(edmcId, edmpId);
        result.setData(edmProperties);
        return result;
    }

    /**
     * 根据类id获取所有属性
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/properties", method = RequestMethod.GET)
    public Result getProperties(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmPropertyVO> edmProperties = edmPropertyService.selectEdmPropertiesByCid(id);
        result.setData(edmProperties);
        return result;
    }

    /**
     * 根据类id获取所有的父类属性
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/fatherProperties", method = RequestMethod.GET)
    public Result getFatherProperties(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmPropertyVO> edmProperties = edmPropertyService.selectFatherPropertiesByCid(id, false);
        result.setData(edmProperties);
        return result;
    }

    /**
     * 根据类id获取常量属性列表
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/consts", method = RequestMethod.GET)
    public Result getConsts(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmPropertyVO> edmProperties = edmPropertyService.selectEdmConstByCid(id);
        result.setData(edmProperties);
        return result;
    }

    /**
     * 根据类id获取所有的父类常量
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/fatherConsts", method = RequestMethod.GET)
    public Result getFatherConstProperties(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmPropertyVO> edmProperties = edmPropertyService.selectFatherPropertiesByCid(id, true);
        result.setData(edmProperties);
        return result;
    }

    /**
     * 根据模型id获取单位类
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/unitClass/{id}", method = RequestMethod.GET)
    public Result getUnitClass(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        EdmClass unitClass = edmClassService.selectSpecialClass(id, Constant.CLASS_MEAS);
        result.setData(unitClass);
        return result;
    }

    /**
     * 根据模型id获取枚举类
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/enumClass/{id}", method = RequestMethod.GET)
    public Result getEnumClass(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        EdmClass enumClass = edmClassService.selectSpecialClass(id, Constant.CLASS_WORD);
        result.setData(enumClass);
        return result;
    }

    /**
     * 根据模型id获取统计类
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/statisticClass/{id}", method = RequestMethod.GET)
    public Result getStatisticClass(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        EdmClass statisticClass = edmClassService.selectSpecialClass(id, Constant.CLASS_STAT);
        result.setData(statisticClass);
        return result;
    }

    /**
     * 根据类id查找类自身及其相关类
     *
     * @param id
     * @return
     */

    @RequestMapping(value = "/relate/{id}", method = RequestMethod.GET)
    public Result getRelateClass(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmClassVO> edmClassVOList = edmClassService.getRelateClass(id);
        result.setData(edmClassVOList);
        return result;
    }

    /**
     * 根据类id查找类和父类及其相关类
     * @param id
     * @return
     */
    @RequestMapping(value = "/relateClasses/{id}", method = RequestMethod.GET)
    public Result getRelateClasses(@PathVariable String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmClassVO> edmClassVOList = edmClassService.getRelateClasses(id);
        result.setData(edmClassVOList);
        return result;
    }

    /**
     * 根据类id获取所有附件
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/attachments", method = RequestMethod.GET)
    public Result getAttachments(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmAttachmentVO> attachments = edmAttachmentService.selectEdmAttachmentsByCid(id);
        result.setData(attachments);
        return result;
    }

    /**
     * 在进行新增类之前的检查edmcCode是否符合要求
     *
     * @param edmcCode
     * @param edmcEdmdId
     * @return
     */
    @RequestMapping(value = "/edmcCode", method = RequestMethod.GET)
    public Result checkEdmcCode(@RequestParam String edmcCode, @RequestParam String edmcEdmdId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        // 数据异常校验
        String errorStr = edmClassService.checkEdmcCode(edmcCode, edmcEdmdId);
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
        }
        return result;
    }


    /**
     * 在进行新增类之前的检查edmcName是否符合要求
     *
     * @param edmcName
     * @param edmcEdmdId
     * @return
     */
    @RequestMapping(value = "/edmcName", method = RequestMethod.GET)
    public Result checkEdmcName(@RequestParam String edmcName,
                                @RequestParam String edmcEdmdId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        // 数据异常校验
        String errorStr = edmClassService.checkEdmcName(edmcName, edmcEdmdId);
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
        }
        return result;
    }

    /**
     * 在进行新增类之前的检查edmcNameEn是否符合要求
     *
     * @param edmcNameEn
     * @param edmcEdmdId
     * @return
     */
    @RequestMapping(value = "/edmcNameEn", method = RequestMethod.GET)
    public Result checkEdmcNameEn(@RequestParam String edmcNameEn,
                                  @RequestParam String edmcEdmdId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        // 数据异常校验
        String errorStr = edmClassService.checkEdmcNameEn(edmcNameEn, edmcEdmdId);
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
        }
        return result;
    }

    /**
     * 在进行新增类之前的检查edmcShortName是否符合要求
     *
     * @param edmcShortName
     * @param edmcEdmdId
     * @return
     */
    @RequestMapping(value = "/edmcShortName", method = RequestMethod.GET)
    public Result checkEdmcShortName(@RequestParam String edmcShortName,
                                     @RequestParam String edmcEdmdId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = edmClassService.checkEdmcShortName(edmcShortName, edmcEdmdId);
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
        }
        return result;
    }

    /**
     * 在进行新增或者修改索引名称indexName时，验证索引名称是否唯一
     *
     * @param indexName
     * @param edmcId
     * @return
     */
    @RequestMapping(value = "/{edmcId}/indexName/validator", method = RequestMethod.GET)
    public Result checkIndexName(@RequestParam(value = "indexName") String indexName,
                                 @PathVariable(value = "edmcId") String edmcId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = edmIndexService.checkIndexName(indexName, edmcId,null);
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
        }
        return result;
    }

    /**
     * 新增类
     * @param edmClass
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmClass edmClass) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = edmClassService.checkEdmcCode(edmClass.getEdmcCode(), edmClass.getEdmcEdmdId());//验证类编码
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
            return result;
        }
        errorStr = edmClassService.checkEdmcName(edmClass.getEdmcName(), edmClass.getEdmcEdmdId());//验证类名称
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
            return result;
        }
        errorStr = edmClassService.checkEdmcNameEn(edmClass.getEdmcNameEn(), edmClass.getEdmcEdmdId());//验证类英文名
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
            return result;
        }
        errorStr = edmClassService.checkEdmcShortName(edmClass.getEdmcShortName(), edmClass.getEdmcEdmdId());//验证类简称
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
            return result;
        }
        edmClassService.insert(edmClass);
        result.setData(edmClass.getId());
        return result;
    }

    /**
     * 修改类
     *
     * @param edmClass
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody EdmClass edmClass) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = ""; // 校验提交信息
        EdmClass oldEdmClass = edmClassService.selectEdmClassById(edmClass.getId());
        if (!StringUtil.isNullOrEmpty(edmClass.getEdmcCode()) && !edmClass.getEdmcCode().equals(oldEdmClass.getEdmcCode())) {
            errorStr = edmClassService.checkEdmcCode(edmClass.getEdmcCode(), edmClass.getEdmcEdmdId());
            if (!StringUtil.isNullOrEmpty(errorStr)) { // 校验失败
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errorStr);
                return result;
            }
        }
        if (!StringUtil.isNullOrEmpty(edmClass.getEdmcName()) && !edmClass.getEdmcName().equals(oldEdmClass.getEdmcName())) {
            errorStr = edmClassService.checkEdmcName(edmClass.getEdmcName(), edmClass.getEdmcEdmdId());
            if (!StringUtil.isNullOrEmpty(errorStr)) { // 校验失败
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errorStr);
                return result;
            }
        }

        if (!StringUtil.isNullOrEmpty(edmClass.getEdmcNameEn()) && !edmClass.getEdmcNameEn().equals(oldEdmClass.getEdmcNameEn())) {
            errorStr = edmClassService.checkEdmcNameEn(edmClass.getEdmcNameEn(), edmClass.getEdmcEdmdId());
            if (!StringUtil.isNullOrEmpty(errorStr)) { // 校验失败
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errorStr);
                return result;
            }
        }

        if (!StringUtil.isNullOrEmpty(edmClass.getEdmcShortName()) && !edmClass.getEdmcShortName().equals(oldEdmClass.getEdmcShortName())) {
            errorStr = edmClassService.checkEdmcShortName(edmClass.getEdmcShortName(), edmClass.getEdmcEdmdId());
            if (!StringUtil.isNullOrEmpty(errorStr)) { // 校验失败
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errorStr);
                return result;
            }
        }
        edmClassService.update(edmClass);
        return result;
    }

    /**
     * 根据id删除类（逻辑删除）
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmClassService.delete(id);
        return result;
    }

    /**
     * 根据类的id获得类的方法列表
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/methods", method = RequestMethod.GET)
    public Result getMethodByClassId(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmMethodVO> list = edmClassService.selectEdmMethodByClassId(id);
        result.setData(list);
        return result;
    }

    /**
     * 根据类的id获得类所有父类的方法列表
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/father/methods", method = RequestMethod.GET)
    public Result getFatherMethodByClassId(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmMethodVO> methodVOList = edmClassService.selectEdmFatherMethodByClassId(id);
        result.setData(methodVOList);
        return result;
    }


    /**
     * 类的移动
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/move", method = RequestMethod.PUT)
    public Result move(@RequestBody String[] ids) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmClassService.move(ids);
        return result;
    }

    /**
     * 类的复制
     *
     * @param edmClass
     * @return
     */
    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public Result copy(@RequestBody EdmClass edmClass) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmClassService.copy(edmClass);
        return result;
    }

    /**
     * 根据类id 查找属性名
     *
     * @param id
     * @return 属性名称与属性id列表
     */
    @RequestMapping(value = "/{id}/properties/name", method = RequestMethod.GET)
    public Result getPropertyNameAndByEdmClassId(@PathVariable(value = "id") String id,
                                                 @RequestParam(required = false, value = "edmpName", defaultValue = "") String edmpName) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmProperty> list = edmPropertyService.getPropertyNameAndByEdmClassId(id, edmpName);
        result.setData(list);
        return result;
    }

    /**
     * 根据类id判断编辑的类所选择的类是否是该类的子类
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/child/{id}/{sid}", method = RequestMethod.GET)
    public Result isChild(@PathVariable(value = "id") String id, @PathVariable(value = "sid") String sid) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        boolean isChild = edmClassService.isChild(id, sid);
        result.setData(isChild);
        return result;
    }

    /**
     * 删除类下的多个方法
     * @param methodIds
     * @return
     */
    @RequestMapping(value = "/batch/{methodIds}", method = RequestMethod.DELETE)
    public Result updateBatchIsDelByClassIdAndMethodId(@PathVariable String[] methodIds) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmClassService.updateBatchIsDelByClassIdAndMethodIds(methodIds);
        return result;
    }

    /**
     * 类树查询接口
     *
     * @param modelerId   模型id
     * @param edmcNameEns 类英文名称
     * @return
     */
    @RequestMapping(value = "/classTree", method = RequestMethod.GET)
    public Result queryClassTree(@RequestParam(required = false) String modelerId,
                                 @RequestParam(value = "edmcNameEns") String[] edmcNameEns) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        EdmClassVO edmClassVO = edmClassService.queryClassTree(modelerId, edmcNameEns);
        result.setData(edmClassVO);
        return result;
    }

    /**
     * 查询已发布模型下面的所有类
     *
     * @return
     */
    @RequestMapping(value = "/getPublishModelerClass", method = RequestMethod.GET)
    public Result getPublishModelerClass() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmClass> list = edmClassService.getPublishModelerClass();
        result.setData(list);
        return result;
    }

    /**
     * 公式设计器类初始化
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getClassAndRelateClass", method = RequestMethod.GET)
    public Result getClassAndRelateClass(@RequestParam(value = "classId") String classId,
                                         @RequestParam(value = "classIdArray") String[] classIdArray) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Map map = edmClassService.getClassAndRelateClass(classId, classIdArray);
        result.setData(map);
        return result;
    }

    /**
     * 根据类名取所有基本属性 edmCode
     *
     * @param name    类名
     * @param edmdVer 类的版本
     * @return result
     */
    @RequestMapping(value = "/getOrmBaseEdmCode", method = RequestMethod.GET)
    public Result getOrmBaseEdmCode(@RequestParam(value = "edmdVer") String edmdVer,
                                    @RequestParam(value = "name") String name) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Map<String, List<String>> map = edmClassService.getOrmBaseEdmCode(edmdVer, name);
        result.setData(map);

        return result;
    }

    /**
     * 根据参数取属性集属性 edmCode
     *
     * @param name    类名
     * @param edmdVer 类的版本
     * @param attr    属性集
     * @return result
     */
    @RequestMapping(value = "/getOrmAttrEdmCode", method = RequestMethod.GET)
    public Result getOrmAttrEdmCode(@RequestParam(value = "edmdVer") String edmdVer,
                                    @RequestParam(value = "name") String name,
                                    @RequestParam(value = "attr") String attr) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Map<String, List<String>> map = edmClassService.getOrmAttrEdmCode(edmdVer, name, attr);
        result.setData(map);

        return result;
    }

    /**
     * 取所有属性和属性集
     *
     * @param name    类名
     * @param edmdVer 类的版本
     * @param attr    属性集
     * @return result
     */
    @RequestMapping(value = "/getAllEdmCode", method = RequestMethod.GET)
    public Result getAllEdmCode(@RequestParam(value = "edmdVer") String edmdVer,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "attr") String attr) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Map<String, List<String>> map = edmClassService.getAllEdmCode(edmdVer, name, attr);
        result.setData(map);

        return result;
    }


    /**
     * @param edmdVer 模型版本
     * @param name    类英文名
     * @param attr    属性集编码
     * @return
     */
    @RequestMapping(value = "/getOrmFeilds", method = RequestMethod.GET)
    public Result getOrmFeilds(@RequestParam(value = "edmdVer") String edmdVer,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "attr") String attr) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Map<String, Object> list = edmClassService.getOrmFeilds(edmdVer, name, attr);
        result.setData(list);

        return result;
    }

    /**
     * @param edmdVer 模型版本
     * @param name    类英文名
     * @return result
     */
    @RequestMapping(value = "/getOrmIndexs", method = RequestMethod.GET)
    public Result getOrmIndexs(@RequestParam(value = "edmdVer") String edmdVer,
                               @RequestParam(value = "name") String name) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Map<String, List<String>> map = edmClassService.getOrmIndexs(edmdVer, name);
        result.setData(map);
        return result;
    }

    /**
     * 取edm实例数据库类型
     *
     * @param edmdVer 模型版本
     * @param name    类英文名
     * @return result
     */
    @RequestMapping(value = "/getDbType", method = RequestMethod.GET)
    public Result getDbType(@RequestParam(value = "edmdVer") String edmdVer,
                            @RequestParam(value = "name") String name) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String dbtype = edmClassService.getDbType(edmdVer, name);
        result.setData(dbtype);
        return result;
    }

    /**
     * 根据类id查询特征值的属性列表
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/character/properties", method = RequestMethod.GET)
    public Result getClassFormatsByCid(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmProperty> edmProperties = edmPropertyService.selectIsCharacterByCid(id);
        result.setData(edmProperties);
        return result;
    }

    /**
     * 根据类id查询可选的关联属性
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/choose/properties", method = RequestMethod.GET)
    public Result getChooseRelateProperty(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmPropertyVO> list = edmClassService.getChooseRelateProperty(id);
        result.setData(list);
        return result;
    }

    /**
     * 根据类名查简称
     * @param edmdVer
     * @param name
     * @return result
     */
    @RequestMapping(value = "/getEdmShortName", method = RequestMethod.GET)
    public Result getEdmShortName(@RequestParam(value = "edmdVer") String edmdVer,
                                  @RequestParam(value = "name") String name) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String shortName = edmClassService.getEdmShortName(edmdVer, name);
        result.setData(shortName);

        return result;
    }

    /**
     * 根据类名查简称
     * @param edmdVer
     * @param name
     * @return result
     */
    @RequestMapping(value = "/getEdmNameByShortName", method = RequestMethod.GET)
    public Result getEdmNameByShortName(@RequestParam(value = "edmdVer") String edmdVer,
                                  @RequestParam(value = "name") String name) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String edmcName = edmClassService.getEdmNameByShortName(edmdVer, name);
        result.setData(edmcName);

        return result;
    }


    /**
     * 根据类id判断该类是否设置了特征值
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}/set/formats", method = RequestMethod.GET)
    public Result isSet(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        boolean isSet = edmClassService.isSet(id);
        result.setData(isSet);
        return result;
    }

    /**
     * 根据类id获取该类的所有设置了关联的属性（联动算法）
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}/connects", method = RequestMethod.GET)
    public Result getConnectsByCid(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<List<EdmLinkVO>> edmLinks = edmLinkService.getConnectsByCid(id);
        result.setData(edmLinks);
        System.out.println("connect:" + result);
        return result;
    }


    /**
     * 根据模型的版本号和类的英文名称获取所属类子孙类中的实体类（监管树用）
     *
     * @param edmdVer
     * @param edmcNameEn
     * @return
     */
    @RequestMapping(value = "/entity", method = RequestMethod.GET)
    public Result getEntityByVersionAndEnglishName(@RequestParam(value = "edmdVer") String edmdVer,
                                                   @RequestParam(value = "edmcNameEn") String edmcNameEn) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmClass> edmClassList = edmClassService.getEntityByVersionAndEnglishName(edmdVer, edmcNameEn);
        result.setData(edmClassList);
        return result;
    }

    /**
     * 取edmDataType
     *
     * @param name    类名
     * @param edmdVer 类的版本
     * @return result
     */
    @RequestMapping(value = "/getEdmDataType", method = RequestMethod.POST)
    public Result getEdmDataType(@RequestParam(value = "edmdVer") String edmdVer,
                                 @RequestParam(value = "name") String name) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<Map<String, Object>> list = edmClassService.getEdmDataType(edmdVer, name);
        result.setData(list);
        return result;
    }

    /**
     * 查询引用对象的类英文名
     *
     * @param edmdVer
     * @param name
     * @return
     */
    @RequestMapping(value = "/getEdmAttrObject", method = RequestMethod.POST)
    public Result getEdmAttrObject(@RequestParam(value = "edmdVer") String edmdVer,
                                   @RequestParam(value = "name") String name) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Map<String, Map<String, Object>> map = edmClassService.getEdmAttrObject(edmdVer, name);
        result.setData(map);
        return result;
    }

    /**
     * 根据类英文名和类版本来查询类
     * @param edmcNameEn 类英文名
     * @param edmcVer    版本号
     * @return
     */
    @RequestMapping(value = "/getClassByEdmNameEn", method = RequestMethod.GET)
    public Result getClassByEdmNameEn(@RequestParam(value = "edmcNameEn") String edmcNameEn,
                                      @RequestParam(value = "edmcVer") String edmcVer) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.getClassByEdmNameEn(edmcNameEn, edmcVer));
        return result;
    }


    /**
     * 根据类id获取类下的属性集和属性集中属性集
     * @param edmcId
     * @return
     */
    @RequestMapping(value = "/{edmcId}/assemble", method = RequestMethod.GET)
    Result selectAssemblesByEdmcId(@PathVariable(value = "edmcId") String edmcId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.selectAssemblesByEdmcId(edmcId));
        return result;
    }

    /**
     * 获取类下的非属性集属性或者属性集下的非属性集属性
     * @param edmcId
     * @param assembleId
     * @return
     */
    @RequestMapping(value = "/{edmcId}/propertys/assemble", method = RequestMethod.GET)
    Result selectProperties(@PathVariable(value = "edmcId") String edmcId,
                            @RequestParam(value = "assembleId", required = false) String assembleId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.selectProperties(edmcId, assembleId));
        return result;
    }

    /**
     * 根据类id获取索引
     * @param edmcId
     * @return
     */
    @RequestMapping(value = "/{edmcId}/indexes", method = RequestMethod.GET)
    public Result selectIndexByEdmcId(@PathVariable(value = "edmcId") String edmcId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.selectIndexByEdmcId(edmcId));
        return result;
    }

    /**
     * 根据类id获取父类索引
     * @param edmcId
     * @return
     */
    @RequestMapping(value = "/{edmcId}/parentIndexes", method = RequestMethod.GET)
    public Result getParentIndexByEdmcId(@PathVariable(value = "edmcId") String edmcId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.getParentIndexByEdmcId(edmcId));
        return result;
    }

    /**
     * 根据类名和与方法名查询方法信息
     * @param className
     * @param methodName
     * @return
     */
    @RequestMapping(value = "/getMethod/{className}/{methodName}",method = RequestMethod.GET)
    public Result getMethodsByEdmcNameVer(@PathVariable(value = "className") String className,
                                          @PathVariable(value = "methodName") String methodName) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        //edmModelerService.queryIdByVer(version);
        EdmMethod edmMethod = edmClassService.getMethodsByEdmcNameVer(version, className, methodName);
        result.setData(edmMethod);
        return result;
    }


    /**
     * 根据ids获取edmclass集合
     * @param ids
     * @return
     */
    @RequestMapping(value = "/getEdmClasses",method = RequestMethod.POST)
    public Result getEdmClasses(@RequestBody String[] ids){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.getEdmClasses(ids));
        return result;
    }

    /**
     * 根据资源类id获取监管类的集合
     * @param ids
     * @return
     */
    @RequestMapping(value = "/monitorClasses", method = RequestMethod.POST)
    public Result getMonitorClasses(@RequestBody String[] ids){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.getMonitorClasses(ids));
        return result;
    }

    /**
     * 获取被监管的资源类集合
     * @return
     */
    @RequestMapping(value = "/resourcesClasses", method = RequestMethod.GET)
    public Result getResourcesClasses(@RequestParam(value = "version") String version) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.getResourcesClasses(version));
        return result;
    }

    /**
     * 根据类编码获取类及其子类
     * @param edmcCode
     * @return
     */
    @RequestMapping(value = "/{edmcCode}/classes", method = RequestMethod.GET)
    public Result getClassesByEdmcCode(@PathVariable(value = "edmcCode") String edmcCode) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.getEdmClassesByEdmcCode(edmcCode));
        return result;
    }

    /**
     * 获取子类及其上一级父类属性中数据类型为岗位类或部门类的属性
     * @param classId
     * @return
     */
    @RequestMapping(value = "/{classId}/props", method = RequestMethod.GET)
    public Result getPropertiesOfEdmClass(@PathVariable(value = "classId") String classId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.getPropertiesOfEdmClass(classId));
        return result;
    }

    /**
     * 根据类id获取类名，多个类id用逗号隔开
     * @param ids
     * @return
     */
    @RequestMapping(value = "/classNames", method = RequestMethod.GET)
    public Result getClassNamesByIds(@RequestParam(value = "ids") String ids) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.getClassNamesByIds(ids));
        return result;
    }

    /**
     * 根据类编码获取类Id
     * @param edmcCode
     * @return
     */
    @RequestMapping(value = "/getIdByEdmcCode",method = RequestMethod.GET)
    public Result getIdByEdmcCode(@RequestParam(value="edmcCode") String edmcCode){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.getIdByEdmcCode(edmcCode));
        return result;
    }

    /**
     * 根据类id获取卷积方法和关联方法
     * @param classId 类id
     * @param edmmMethodType 1：关联方法 2：卷积方法
     * @return
     */
    @RequestMapping(value = "/linkMethods", method = RequestMethod.GET)
    public Result getLinkMethod(@RequestParam(value = "classId") String classId,
                                @RequestParam(value = "edmmMethodType") String edmmMethodType) {

        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.getLinkMethod(classId, edmmMethodType));
        return result;
    }

    /**
     * 获取类的所有属性,包括属性集中属性，但不包括属性集本身
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/allProps", method = RequestMethod.GET)
    public Result getAllProperties(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.getAllProperties(id));
        return result;
    }

    @RequestMapping(value = "/{ids}/classAndProps", method = RequestMethod.GET)
    public Result getAllClassesAdProps(@PathVariable(value = "ids") String ids) throws Exception {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmClassService.selectEdmClassesAndProps(ids));
        return result;
    }
}
