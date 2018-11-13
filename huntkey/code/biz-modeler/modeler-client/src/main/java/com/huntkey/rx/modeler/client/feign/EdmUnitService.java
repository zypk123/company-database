package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmUnitHystrix;
import com.huntkey.rx.modeler.common.model.EdmUnit;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by linziy on 2017/4/19.
 */
@FeignClient(value = "modeler-provider", fallback = EdmUnitHystrix.class)
public interface EdmUnitService {
    @RequestMapping(value="/units",method = RequestMethod.POST)
    public Result add(@RequestBody EdmUnit edmUnit);

    @RequestMapping(value = "/units/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id);

    @RequestMapping(value = "/units/batch/{ids}", method = RequestMethod.DELETE)
    public Result deleteIds(@PathVariable("ids") String[] ids);

    @RequestMapping(value = "/units",method = RequestMethod.PUT)
    public Result update(@RequestBody EdmUnit edmUnit);

    @RequestMapping(value = "/units/{id}", method = RequestMethod.GET)
    public Result getbyid(@PathVariable(value="id") String id);

    @RequestMapping(value = "/units",method=RequestMethod.GET)
    public Result select(@RequestParam(required = false, value = "edunEdmpId") String edunEdmpId,
                        @RequestParam(required = false, value = "edunQtyEdmpId")String edunQtyEdmpId,
                        @RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,@RequestParam(required = false,defaultValue = "10",value = "pageSize") int pageSize);
}
