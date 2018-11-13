package com.huntkey.rx.sceo.formula.provider.function.definefunc.controller;

import com.huntkey.rx.commons.utils.file.FileUtils;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.constant.FormulaCode;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.model.TfmFunctionCompiled;
import com.huntkey.rx.sceo.formula.common.model.TfmFunctionDefinition;
import com.huntkey.rx.sceo.formula.provider.function.FuncionDefinedHelper;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.service.DefineFunctionService;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.utils.FunctionDefinedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义函数Controller
 *
 * @author nidongx
 * @create 2017-07-26
 **/
@RestController
@RequestMapping("/defineFunction")
@CrossOrigin
public class DefineFunctionController {

    private Logger log = LoggerFactory.getLogger(DefineFunctionController.class);

    @Autowired
    private DefineFunctionService defineFunctionService;

    @Autowired
    private FuncionDefinedHelper funcionDefinedHelper;

    @Value("${jarUploadPath}")
    private String jarUploadPath;

    /**
     * 查询自定义函数列表查询自定义函数列表
     *
     * @param fundFunCatagoryId
     * @param fundFunName
     * @param fundState
     * @param sortName
     * @param sortOrder
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/queryDefineFunction", method = RequestMethod.GET)
    public Result queryDefineFunction(@RequestParam(value = "fundFunCatagoryId", required = false) String fundFunCatagoryId,
                                      @RequestParam(value = "fundFunName", required = false) String fundFunName,
                                      @RequestParam(value = "fundState", required = false) String fundState,
                                      @RequestParam(required = false, value = "sortName") String sortName,
                                      @RequestParam(required = false, value = "sortOrder") String sortOrder,
                                      @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);

        log.info("fundFunCatagoryId: {}, fundFunName: {}, fundState: {}", fundFunCatagoryId, fundFunName, fundState);

        try {
            log.info("call defineFunction queryDefineFunction service success!");
            Pagination<TfmFunctionDefinition> page = defineFunctionService.queryDefineFunction(fundFunCatagoryId, fundFunName, fundState, sortName, sortOrder, pageNum, pageSize);
            result.setData(page);
        } catch (Exception e) {
            String errMsg = "call defineFunction queryDefineFunction service fail!" + " -> " + e.getMessage();
            result.setRetCode(FormulaCode.FUNCTION_DEFINE_ERR.getStateCode());
            result.setErrMsg(errMsg);
            log.error(errMsg);
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 新建自定义函数
     *
     * @param files
     * @param fundState
     * @param fundFunName
     * @param fundFunDesc
     * @param fundModifyRemarks
     * @param fundFunClassifyId
     * @param fundFunClassifyCode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/createCustomizeFunction", method = RequestMethod.POST)
    public Result createCustomizeFunction(@RequestPart("files") MultipartFile[] files,
                                          @RequestParam("fundState") String fundState,
                                          @RequestParam("fundFunName") String fundFunName,
                                          @RequestParam("fundFunDesc") String fundFunDesc,
                                          @RequestParam("fundModifyRemarks") String fundModifyRemarks,
                                          @RequestParam("fundFunClassifyId") String fundFunClassifyId,
                                          @RequestParam("fundFunClassifyCode") String fundFunClassifyCode) throws Exception {

        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);

        try {
            TfmFunctionDefinition funcDefinition = new TfmFunctionDefinition();
            funcDefinition.setFundState(fundState);
            funcDefinition.setFundFunCatagoryId(fundFunClassifyId);
            funcDefinition.setFundFunDesc(fundFunDesc);
            funcDefinition.setFundFunName(fundFunName);
            funcDefinition.setFundModifyRemarks(fundModifyRemarks);

            // 获取上传的文件
            MultipartFile javaFile = null;
            List<MultipartFile> jarFiles = new ArrayList<MultipartFile>();
            for (MultipartFile file : files) {
                if (file.getOriginalFilename().endsWith("java")) {
                    javaFile = file;
                } else {
                    jarFiles.add(file);
                }
            }

            String[] arr = FunctionDefinedUtils.getSourceCodeAndClassFullName(javaFile.getInputStream());
            String sourceCode = arr[0];
            String classFullName = arr[1];

            // 判断自定义函数是否已经存在
            if (!funcionDefinedHelper.isExistsInCustomizeFunction(classFullName)) {
                throw new RuntimeException("自定义函数: " + classFullName.substring(classFullName.lastIndexOf(".") + 1) + "已经存在了，请勿重复添加！");
            }

            // 判断自定义函数名称是否与系统函数重复
            if (!funcionDefinedHelper.isExistsInSystemFunction(classFullName)) {
                throw new RuntimeException("自定义函数: " + classFullName.substring(classFullName.lastIndexOf(".") + 1) + "名称与系统函数重复，请修改名称重试！");
            }

            String classShortName = classFullName.substring(classFullName.lastIndexOf(".") + 1);
            // 判断前台输入的函数名是否和上传的函数名一致，如果不一致，直接报错
            if (!StringUtil.isNullOrEmpty(classShortName) && !classShortName.equals(fundFunName)) {
                throw new RuntimeException("输入的函数名与待上传的文件名不一致，请重新输入！");
            }

            // 保存jar到指定目录
            String jarPath = null;
            if (!jarFiles.isEmpty()) {
                jarPath = System.getProperty("user.dir") + File.separator + jarUploadPath + fundFunName + File.separator;
//                jarPath = this.getClass().getResource("/").getPath() + jarUploadPath + fundFunName + "/";
                FileUtils.createDirectory(jarPath);
            }
            for (MultipartFile jar : jarFiles) {
                File target = new File(jarPath + jar.getOriginalFilename());
                target.createNewFile();
                // 将jar文件传送到target文件夹中
                jar.transferTo(target);
            }

            log.info("call defineFunction createCustomizeFunction service success!");
            defineFunctionService.createCustomizeFunction(funcDefinition, javaFile.getOriginalFilename(),
                    sourceCode, classFullName, fundFunClassifyCode, jarPath);
            result.setData(funcDefinition.getFundId());
        } catch (Exception e) {
            String errMsg = "新增自定义函数失败!" + " -> " + e.getMessage();
            result.setRetCode(FormulaCode.FUNCTION_DEFINE_ERR.getStateCode());
            result.setErrMsg(errMsg);
            log.error(errMsg);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据ID查找自定义函数编译数据
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    @RequestMapping(value = "/getCompileFunByCompileId/{funcId}", method = RequestMethod.GET)
    public Result getCompileFunByCompileId(@PathVariable("funcId") String funcId) {

        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);

        try {
            log.info("call defineFunction getCompileFunByCompileId service success!");
            TfmFunctionCompiled tfmFunctionCompiled = defineFunctionService.getCompileFunByCompileId(funcId);
            result.setData(tfmFunctionCompiled);
        } catch (Exception e) {
            String errMsg = "call defineFunction getCompileFunByCompileId service fail!" + " -> " + e.getMessage();
            result.setRetCode(FormulaCode.FUNCTION_DEFINE_ERR.getStateCode());
            result.setErrMsg(errMsg);
            log.error(errMsg);
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 根据ID查找自定义函数定义数据
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    @RequestMapping(value = "/getDefineFunByDefineId/{funcId}", method = RequestMethod.GET)
    public Result getDefineFunByDefineId(@PathVariable("funcId") String funcId) {

        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);

        try {
            log.info("call defineFunction getDefineFunByDefineId service success!");
            TfmFunctionDefinition funcDefinition = defineFunctionService.getDefineFunByDefineId(funcId);
            result.setData(funcDefinition);
        } catch (Exception e) {
            String errMsg = "call defineFunction getDefineFunByDefineId service fail!" + " -> " + e.getMessage();
            result.setRetCode(FormulaCode.FUNCTION_DEFINE_ERR.getStateCode());
            result.setErrMsg(errMsg);
            log.error(errMsg);
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 修改自定义函数
     *
     * @param files
     * @param fundId
     * @param fundFunName
     * @param fundState
     * @param fundFunDesc
     * @param fundFunClassifyId
     * @param fundFunClassifyCode
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateCustomizeFunction", method = RequestMethod.POST)
    public Result updateCustomizeFunction(@RequestPart("files") MultipartFile[] files,
//                                          @RequestParam("jarNameList") List<String> jarNameList,
                                          @RequestParam("fundId") String fundId,
                                          @RequestParam("fundFunName") String fundFunName,
                                          @RequestParam("fundState") String fundState,
                                          @RequestParam("fundFunDesc") String fundFunDesc,
                                          @RequestParam("fundFunClassifyId") String fundFunClassifyId,
                                          @RequestParam("fundFunClassifyCode") String fundFunClassifyCode) throws Exception {

        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);

        try {
            TfmFunctionDefinition funcDefinition = new TfmFunctionDefinition();
            funcDefinition.setFundId(fundId);
            funcDefinition.setFundState(fundState);
            funcDefinition.setFundFunCatagoryId(fundFunClassifyId);
            funcDefinition.setFundFunDesc(fundFunDesc);
            funcDefinition.setFundFunName(fundFunName);

            // FIXME 如何处理jar文件比较多或者jar文件比较大的问题？ 编译和加载都会变的很慢。。。
            // TODO 限制上传文件数量和大小

            // 因为jar内容也可能变化，不能直接根据jar文件名称来判断文件是否发生变化，所以这里不管新上传的jar是怎样的，都先删除之前的配置，重新加载和编译
            // 编译和加载逻辑和新增自定义函数时一致，参考DefineFunctionController.createCustomizeFunction
            // 获取上传的文件
            MultipartFile javaFile = null;
            List<MultipartFile> jarFiles = new ArrayList<MultipartFile>();
            for (MultipartFile file : files) {
                if (file.getOriginalFilename().endsWith("java")) {
                    javaFile = file;
                } else {
                    jarFiles.add(file);
                }
            }

            String[] arr = FunctionDefinedUtils.getSourceCodeAndClassFullName(javaFile.getInputStream());
            String sourceCode = arr[0];
            String classFullName = arr[1];

            // 函数名唯一性校验
            int retInt = defineFunctionService.checkFuncName(fundId, fundFunName);
            if (retInt != 0) {
                throw new RuntimeException("函数名: " + fundFunName + "已经存在，请重新输入！");
            }

            String classShortName = classFullName.substring(classFullName.lastIndexOf(".") + 1);
            // 判断前台输入的函数名是否和上传的函数名一致，如果不一致，直接报错
            if (!StringUtil.isNullOrEmpty(classShortName) && !classShortName.equals(fundFunName)) {
                throw new RuntimeException("输入的函数名与待上传的文件名不一致，请重新输入！");
            }

            // 保存jar到指定目录
            String jarPath = null;
            if (!jarFiles.isEmpty()) {
//                jarPath = this.getClass().getResource("/").getPath() + jarUploadPath + fundFunName + "/";
                jarPath = System.getProperty("user.dir") + File.separator + jarUploadPath + fundFunName + File.separator;
                // 如果源文件存在，则直接删除
                if (new File(jarPath).exists()) {
                    FileUtils.deleteDirectory(jarPath);
                }
                // 不存在，则创建文件夹
                FileUtils.createDirectory(jarPath);
            }

            for (MultipartFile jar : jarFiles) {
                File target = new File(jarPath + jar.getOriginalFilename());
                target.createNewFile();
                // 将jar文件传送到target文件夹中
                jar.transferTo(target);
            }
            log.info("call defineFunction updateCustomizeFunction service success!");
            defineFunctionService.updateCustomizeFunction(funcDefinition, javaFile.getOriginalFilename(),
                    sourceCode, classFullName, fundFunClassifyCode, jarPath);
            result.setData(funcDefinition.getFundId());
        } catch (Exception e) {
            String errMsg = "自定义函数修改失败：" + " -> " + e.getMessage();
            result.setRetCode(FormulaCode.FUNCTION_DEFINE_ERR.getStateCode());
            result.setErrMsg(errMsg);
            log.error(errMsg);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除自定义函数
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    @RequestMapping(value = "/deleteCustomizeFunction/{funcId}", method = RequestMethod.DELETE)
    public Result deleteCustomizeFunction(@PathVariable("funcId") String funcId) {

        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);

        try {
            log.info("call defineFunction deleteCustomizeFunction service success!");
            int ret = defineFunctionService.deleteCustomizeFunction(funcId);
            if (ret == 0) {
                throw new RuntimeException("该函数正在被使用,不允许删除！");
            }
        } catch (Exception e) {
            String errMsg = "call defineFunction deleteCustomizeFunction service fail!" + " -> " + e.getMessage();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(errMsg);
            log.error(errMsg);
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 根据函数分类ID查找自定义函数
     *
     * @param classifyId
     * @return
     */
    @RequestMapping(value = "/getFunctionDefinitionByClassifyId/{classifyId}", method = RequestMethod.GET)
    public Result getFunctionDefinitionByClassifyId(@PathVariable("classifyId") String classifyId) {

        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);

        try {
            log.info("call defineFunction getFunctionDefinitionByClassifyId service success!");
            List<TfmFunctionDefinition> funcDefinitionList = defineFunctionService.getFunctionDefinitionByClassifyId(classifyId);
            result.setData(funcDefinitionList);
        } catch (Exception e) {
            String errMsg = "call defineFunction getFunctionDefinitionByClassifyId service fail!" + " -> " + e.getMessage();
            result.setRetCode(FormulaCode.FUNCTION_DEFINE_ERR.getStateCode());
            result.setErrMsg(errMsg);
            log.error(errMsg);
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping(value = "/getFunctionDescriberById/{compileId}", method = RequestMethod.GET)
    public Result getFunctionDescriberById(@PathVariable("compileId") String compileId) {

        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);

        try {
            log.info("call defineFunction getFunctionDescriberById service success!");
            FunctionDescriber describer = defineFunctionService.getFunctionDescriberById(compileId);
            result.setData(describer);
        } catch (Exception e) {
            String errMsg = "call defineFunction getFunctionDescriberById service fail!" + " -> " + e.getMessage();
            result.setRetCode(FormulaCode.FUNCTION_DEFINE_ERR.getStateCode());
            result.setErrMsg(errMsg);
            log.error(errMsg);
            e.printStackTrace();
        }

        return result;
    }
}
