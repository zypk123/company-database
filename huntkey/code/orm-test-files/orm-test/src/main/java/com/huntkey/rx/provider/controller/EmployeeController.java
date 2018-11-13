package com.huntkey.rx.provider.controller;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.EmployeeEntity;
import com.huntkey.rx.provider.service.EmployeeService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by linziy on 2017/11/22.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * 分页查询数据
     *
     * @param rempName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getDataList", method = RequestMethod.GET)
    public Result getDataList(@RequestParam(required = false, value = "remp_name") String rempName,
                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            Pagination<EmployeeEntity> dataList = employeeService.getDataList(rempName, pageNum, pageSize);
            result.setData(dataList);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---getDataList接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    /**
     * 根据ID查询数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result load(@PathVariable String id) {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            EmployeeEntity employeeEntity = employeeService.load(id);
            result.setData(employeeEntity);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---add接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    /**
     * 条件查询数据
     *
     * @param rempNo
     * @param rempName
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result query(@RequestParam(required = false, value = "remp_no") String rempNo,
                        @RequestParam(required = false, value = "remp_name") String rempName) {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {

            OrmParam ormParam = new OrmParam();
            //设置返回列的方式(暂时无效)
//            ormParam.addColumn("*"); // 如果edm表中有统计类的话,必须设置返回列的参数，否则报错
            ormParam.addColumn("id").addColumn("remp_no").addColumn("remp_name").addColumn("moduser").addColumn("creuser");

            // SELECT * FROM employee WHERE remp_no = ?
            // String whereCondition = OrmParam.and(ormParam.getEqualXML("remp_no", rempNo));
            // SELECT * FROM employee WHERE ((remp_no = ? AND remp_name = ?) OR creuser = ?)

            ormParam.addWhereParam("remp_no", rempNo);
            ormParam.addWhereParam("remp_name", rempName);

//            String whereExp = "<if test = \"remp_no !=null and remp_no !=''\"> remp_no = #{whereParam.remp_no}</if> " +
//                    "<if test = \"remp_name !=null and remp_name !=''\"> remp_name = #{whereParam.remp_name}</if>";

//            String whereExp = "<if test = \"remp_no !=null and remp_no !=''\"> remp_no = #{whereParam.remp_no}</if> " +
//                    "<if test = \"remp_name !=null and remp_name !=''\"> remp_name = #{whereParam.remp_name}</if>";

//            String whereExp = null;
//            if (!StringUtil.isNullOrEmpty(rempNo)) {
//                whereExp = "remp_no = #{whereParam.remp_no} ";
//            }
//
//            if (!StringUtil.isNullOrEmpty(rempName)) {
//                whereExp = whereExp + " AND remp_name = #{whereParam.remp_name}";
//            }

            String whereExp = OrmParam.and(ormParam.getEqualXML("remp_no", rempNo), ormParam.getEqualXML("remp_name", rempName));

//            String whereExp = "remp_no = #{whereParam.remp_no} AND remp_name = #{whereParam.remp_name}";

            ormParam.setWhereExp(whereExp);

//            String whereCondition = null;
//
//            // 组合查询条件
//            if (!StringUtil.isNullOrEmpty(rempNo) && StringUtil.isNullOrEmpty(rempName)) {
//                whereCondition = OrmParam.or(
//                        OrmParam.and(ormParam.getEqualXML("remp_no", rempNo), ormParam.getEqualXML("creuser", "lex")));
//            }
//            if (StringUtil.isNullOrEmpty(rempNo) && !StringUtil.isNullOrEmpty(rempName)) {
//                whereCondition = OrmParam.or(
//                        OrmParam.and(ormParam.getEqualXML("remp_name", rempName), ormParam.getEqualXML("creuser", "lex")));
//            }
//            if (!StringUtil.isNullOrEmpty(rempNo) && !StringUtil.isNullOrEmpty(rempName)) {
//                whereCondition = OrmParam.or(
//                        OrmParam.and(ormParam.getEqualXML("remp_no", rempNo), ormParam.getEqualXML("remp_name", rempName))
//                        , ormParam.getEqualXML("creuser", "lex"));
//            }
//
//            ormParam.setWhereExp(whereCondition);

            // List<EmployeeEntity> employeeEntity = employeeService.query(ormParam);
            // result.setData(employeeEntity);

            List<Map<String, Object>> data = employeeService.queryEx(ormParam);
            result.setData(data);

        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---add接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    /**
     * 通过自定义sql查询
     *
     * @return
     */
    @RequestMapping(value = "/getDataBySql", method = RequestMethod.GET)
    public Result getDataBySql() {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            String sql = "SELECT * from employee where remp_assu_rela = '同事'";
            List<Map<String, Object>> data = employeeService.queryBySql(sql);
            result.setData(data);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            throw new RuntimeException("自定义查询接口调用失败", e);
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---getDataBySql接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    /**
     * 新增数据(全部插入，不建议使用)
     *
     * @param employeeEntity
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result insert(@RequestBody EmployeeEntity employeeEntity) {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            String data = employeeService.insert(employeeEntity);
            result.setData(data);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            throw new RuntimeException("新增数据接口调用失败", e);
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---insert接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    /**
     * 批量新增数据
     *
     * @param employeeEntityList
     * @return
     */
    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public Result insertBatch(@RequestBody List<EmployeeEntity> employeeEntityList) {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            int data = employeeService.insertBatch(employeeEntityList);
            result.setData(data);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            throw new RuntimeException("新增数据接口调用失败", e);
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---insertBatch接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    /**
     * 修改数据
     *
     * @param employeeEntity
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody EmployeeEntity employeeEntity) {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
//            result.setData(employeeService.update(employeeEntity)); // 更新带数值为null的字段(全列更新)
            result.setData(employeeService.updateSelective(employeeEntity)); // 更新不带数值为null的字段
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---update接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            int data = employeeService.delete(id);
            result.setData(data);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---delete接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    /**
     * 根据条件删除数据
     *
     * @return
     */
    @RequestMapping(value = "/deleteByCondition", method = RequestMethod.DELETE)
    public Result deleteByCondition() {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            // 拼接OrmParam对象
            OrmParam param = new OrmParam();
            param.addWhereParam("creuser", "zypk123");
            String whereExp = "creuser = #{whereParam.creuser}";
            param.setWhereExp(whereExp);

            int data = employeeService.deleteByCondition(param);
            result.setData(data);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            throw new RuntimeException("根据条件删除数据接口调用失败", e);
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---deleteByCondition接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    /**
     * 统计数据个数
     *
     * @return
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Result count() {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            OrmParam ormParam = new OrmParam();
            //设置返回列的方式(暂时无效)
            ormParam.addColumn("*"); // 如果edm表中有统计类的话,必须设置返回列的参数，否则报错
            String whereCondition = OrmParam.or(
                    OrmParam.and(ormParam.getEqualXML("remp_no", "work_no1"), ormParam.getEqualXML("remp_name", "lex"))
                    , ormParam.getEqualXML("creuser", "lex"));
            ormParam.setWhereExp(whereCondition);

            result.setData(employeeService.count(ormParam));
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---count接口执行时间{}ms", endTime - beginTime);
        return result;
    }

}
