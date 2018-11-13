package com.huntkey.rx.hr.provider.client;

import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author weijian
 */
//开发调试可以指定URL，不允许提交到GIT
//@FeignClient(value = "serviceCenter-provider",url = "http://10.3.98.155:2008")
@FeignClient(value = "serviceCenter-provider")
public interface ORMClient {
    /**
     * add:添加edm类数据
     * @author caozhenx
     * @param data
     * @return
     */
    @RequestMapping(value = "/servicecenter/add", method = RequestMethod.POST)
    Result add(@RequestBody String data);
    /**
     * delete:根据条件删除edm类数据
     * @author caozhenx
     * @param data
     * @return
     */
    @RequestMapping(value = "/servicecenter/delete", method = RequestMethod.POST)
    Result delete(@RequestBody String data);
    /**
     * update:修改edm类数据
     * @author caozhenx
     * @param data
     * @return
     */
    @RequestMapping(value = "/servicecenter/update", method = RequestMethod.POST)
    Result update(@RequestBody String data);
    /**
     * find:根据条件查询edm类数据
     * @author caozhenx
     * @param data orm查询条件
     * @return
     */
    @RequestMapping(value = "/servicecenter/find", method = RequestMethod.POST)
    Result find(@RequestBody String data);
    /**
     * load:根据ID查询edm类数据
     * @author caozhenx
     * @param data orm查询条件
     * @return
     */
    @RequestMapping(value = "/servicecenter/load", method = RequestMethod.POST)
    Result load(@RequestBody String data);
    /**
     * query:根据原生SQL查询edm类数据
     * @author caozhenx
     * @param sql orm查询条件
     * @return
     */
    @RequestMapping(value = "/servicecenter/query", method = RequestMethod.GET)
    Result query(@RequestParam(value = "sql") String sql);

    /**
     * 获取具体枚举对象
     * @param parCode
     * @param code
     * @return
     */
    @RequestMapping(value = "/biz/enums/object", method = RequestMethod.GET)
    Result getEnumsObj(@RequestParam(value = "parCode") String parCode, @RequestParam(value = "code") String code);
}
