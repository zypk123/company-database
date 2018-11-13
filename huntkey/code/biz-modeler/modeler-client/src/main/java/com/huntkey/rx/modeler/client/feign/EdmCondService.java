package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmCondHystrix;
import com.huntkey.rx.modeler.common.model.EdmCond;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by linziy on 2017/4/19.
 */
@FeignClient(value = "modeler-provider", fallback = EdmCondHystrix.class)
public interface EdmCondService {

    @RequestMapping(value="/conds",method = RequestMethod.POST)
    Result addConds(@RequestBody EdmCond edmCond);

    @RequestMapping(value="/conds",method = RequestMethod.PUT)
    Result updateConds(@RequestBody EdmCond edmCond);

    /**
     * 根据触发条件id逻辑上删除触发条件
     * @param id
     * @return
     */
    @RequestMapping(value = "/conds/{id}", method = RequestMethod.DELETE)
    Result delete(@PathVariable(value = "id") String id);

    @RequestMapping(value = "/conds/deleteByEdcoCondId/{condId}", method = RequestMethod.DELETE)
    Result deleteByEdcoCondId(@PathVariable(value = "condId") String condId);

    /**
     * 清空触发条件公式
     * @param id
     * @return
     */
    @RequestMapping(value = "/conds/{id}/cond", method = RequestMethod.PUT)
    Result clearCond(@PathVariable(value = "id") String id);

    @RequestMapping(value = "/conds/cond/{id}", method = RequestMethod.GET)
    Result selectByPrimaryKey(@PathVariable(value = "id") String id);
}
