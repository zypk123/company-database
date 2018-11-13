package com.huntkey.rx.modeler.provider.controller;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.common.model.EdmModeler;
import com.huntkey.rx.modeler.common.model.vo.EdmClassVO;
import com.huntkey.rx.modeler.common.model.vo.EdmModelerVO;
import com.huntkey.rx.modeler.common.util.ExcelUtil;
import com.huntkey.rx.modeler.provider.service.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by liangh on 2017/4/12.
 */
@RestController
@RequestMapping("/modelers")
public class EdmModelerController {

    private static Logger log = LoggerFactory.getLogger(EdmModelerController.class);

    @Autowired
    private EdmModelerService edmModelerService;

    @Autowired
    private EdmClassService edmClassService;
    
    @Autowired
    private EdmPropertyService edmPropertyService;

    @Autowired
    private EdmConvoluteService edmConvoluteService;

    @Autowired
    private EdmLinkService edmLinkService;

    @Autowired
    private EdmConnectService edmConnectService;

    @Autowired
    private EdmUnitService edmUnitService;

    @Autowired
    private EdmAttachmentService edmAttachmentService;

    @Autowired
    private EdmMethodService edmMethodService;

    @Autowired
    private EdmIndexService edmIndexService;

    @Value("${modeler.version}")
    public String version;
    /**
     * 根据模型id获取类树
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/classes", method = RequestMethod.GET)
    public Result getClasses(@PathVariable(value = "id") String id) throws Exception{
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmClassVO> edmClassList = edmClassService.selectEdmClassesByMid(id);
        result.setData(edmClassList);
        return result;
    }

    /**
     * 根据模型版本和模型更新说明查询模型
     * @param edmdVer
     * @param edmdUpdateDesc
     * @param pageNum
     * @param pageSize
     * @return EdmModelerVO
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result queryList(@RequestParam(required = false) String edmdVer,@RequestParam(required = false) String edmdUpdateDesc,
                            @RequestParam(required = false, defaultValue = "1") int pageNum,
                            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Pagination<EdmModelerVO> edmModelerVoList = edmModelerService.selectModelerListByExample(edmdVer, edmdUpdateDesc,
                pageNum, pageSize);
        result.setData(edmModelerVoList);
        return result;
    }

    /**
     * 获取所有版本名称
     * @return
     */
    @RequestMapping(value = "/versions",method = RequestMethod.GET)
    public Result getAllVers() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<String> edmModelerVoList = edmModelerService.getAllVers();
        result.setData(edmModelerVoList);
        return result;
    }

    /**
     * 根据模型id查询模型
     * @param id
     * @return EdmModelerVO
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result queryObject(@PathVariable("id") String id){//根据主键id查询模型
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        EdmModelerVO edmModelerVo = edmModelerService.selectModelerByPrimaryKey(id);
        result.setData(edmModelerVo);
        return result;
    }

    /**
     * 查询当前可用的最大版本号
     * @param
     * @return newModelerVer
     */
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public Result queryModelerVer(){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String newModelerVer = edmModelerService.selectModelerVer();
        result.setData(newModelerVer);
        return result;
    }

    /**
     * 在进行新增模型之前的判断，如有处于编辑状态的模型时，addStatus返回0，否则返回1
     * @param
     * @return addStatus
     */
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Result status(){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        EdmModelerVO edmModelerVO = edmModelerService.beforeAdd();
        result.setData(edmModelerVO);
        return result;
    }

    /**
     * 验证模型版本
     * @param edmdVer
     * @return Result
     */
    @RequestMapping(value = "/edmdVer", method = RequestMethod.GET)
    public Result checkData(@RequestParam String edmdVer) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = edmModelerService.checkUnique(edmdVer);
        if(!StringUtil.isNullOrEmpty(errorStr)){
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
        }
        return result;
    }

    /**
     * 新增模型
     * @param edmModeler
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmModeler edmModeler){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = edmModelerService.checkUnique(edmModeler.getEdmdVer());
        if(!StringUtil.isNullOrEmpty(errorStr)){
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
            return result;
        }
        edmModelerService.insert(edmModeler);
        return result;
    }

    /**
     * 更新模型
     * @param edmModeler
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody EdmModeler edmModeler){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        //查询出未修改之前的模型数据对象
        EdmModelerVO oldEdmModeler = edmModelerService.selectModelerByPrimaryKey(edmModeler.getId());
        if( !StringUtil.isNullOrEmpty(edmModeler.getEdmdVer()) && !oldEdmModeler.getEdmdVer().equals(edmModeler.getEdmdVer())){//修改了版本号的情况，先验证数据，再更新数据
            String errorStr = edmModelerService.checkUnique(edmModeler.getEdmdVer());
           if(!StringUtil.isNullOrEmpty(errorStr)){
               result.setRetCode(Result.RECODE_VALIDATE_ERROR);
               result.setErrMsg(errorStr);
               return result;
           }
        }
        edmModelerService.updateByPrimaryKey(edmModeler);
        return result;
    }

    /**
     * 根据id删除模型
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmModelerService.updateIsDelByPrimaryKey(id);
        return result;
    }

    /**
     * 导出模型数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    public Result exportExcel(@RequestParam("id") String id,
                              HttpServletResponse response){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call edmModeler exportExcel service");
            //默认放在D盘下modelerData文件夹下
            //String fileUrl = null;
            //判断保存路径是否存在，如果不存在，则新生成
            //fileUrl = edmModelerService.judgeFilePath(fileUrl);

           // HSSFWorkbook wb = new HSSFWorkbook();
            HSSFWorkbook wb = edmModelerService.exportExcel(id);
            ExcelUtil.writeExcel(wb,response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("call edmModeler exportExcel service fail");
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("系统异常");
        }
        return result;
    }

    @RequestMapping(value = "/queryClasses",method = RequestMethod.GET)
    public Result queryIdByVer() throws Exception {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String id = edmModelerService.queryModelerIdByVer(version);
        result.setData(edmClassService.selectEdmClassesByMid(id));
        return result;

    }

}
