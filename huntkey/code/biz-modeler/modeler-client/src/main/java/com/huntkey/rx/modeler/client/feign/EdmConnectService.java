package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmConnectHystrix;
import com.huntkey.rx.modeler.common.model.EdmConnect;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by linziy on 2017/4/17.
 */
@FeignClient(value = "modeler-provider", fallback = EdmConnectHystrix.class)
public interface EdmConnectService {
    @RequestMapping(value="/connects",method= RequestMethod.POST)
    public Result add(@RequestBody EdmConnect edmConnect);

    @RequestMapping(value = "/connects/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id);

    @RequestMapping(value = "/connects/batch/{ids}", method = RequestMethod.DELETE)
    public Result deleteIds(@PathVariable("ids") String[] ids);

    @RequestMapping(value="/connects",method = RequestMethod.PUT)
    public Result update(@RequestBody EdmConnect edmConnect);

    @RequestMapping(value = "/connects/{id}", method = RequestMethod.GET)
    public Result getConnectById(@PathVariable(value="id") String id);

    @RequestMapping(value="/connects",method=RequestMethod.GET)
    public Result select(@RequestParam(required = false, value = "edcnEdmpId")String edcnEdmpId,
                        @RequestParam(required = false, value = "edcnLinkPreservable")Byte edcnLinkPreservable,
                        @RequestParam(required = false, value = "edcnUpdateType")Byte edcnUpdateType,
                        @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,@RequestParam(required = false,defaultValue = "10",value = "pageSize") int pageSize);

    @RequestMapping(value = "/connects/{classId}/connects", method=RequestMethod.GET)
    Result getConnectsOfClass(@PathVariable(value = "classId")String classId);
}
