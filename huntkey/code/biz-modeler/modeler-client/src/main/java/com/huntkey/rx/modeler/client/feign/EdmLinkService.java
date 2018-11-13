package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmLinkHystrix;
import com.huntkey.rx.modeler.common.model.EdmCond;
import com.huntkey.rx.modeler.common.model.vo.EdmLinkVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by linziy on 2017/4/19.
 */
@FeignClient(value = "modeler-provider", fallback = EdmLinkHystrix.class)
public interface EdmLinkService {

    @RequestMapping(value = "/links/{id}", method = RequestMethod.GET)
    Result getLinkById(String id);

    @RequestMapping(value="/links",method = RequestMethod.POST)
    Result add(@RequestBody EdmLinkVO edmLinkVO) ;

    @RequestMapping(value="/links",method = RequestMethod.PUT)
    Result update(@RequestBody EdmLinkVO edmLinkVO);

    @RequestMapping(value = "/links/{id}", method = RequestMethod.DELETE)
    Result delete(@PathVariable("id") String id);

    @RequestMapping(value = "/links/batch/{ids}", method = RequestMethod.DELETE)
    Result deleteIds(@PathVariable("ids") String[] ids);

    @RequestMapping(value = "/links",method= RequestMethod.GET)
    Result select(@RequestParam(required = false, value = "edmlEdmpId")String edmlEdmpId,
                        @RequestParam(required = false, value="edmlEdmpLinkId")String edmlEdmpLinkId,
                        @RequestParam(required = false, value="edmlCond")String edmlCond,
                        @RequestParam(required = false, value="edmlFormula")String edmlFormula,
                        @RequestParam(required = false, defaultValue = "1",value = "pageNum")int pageNum,@RequestParam(required = false,defaultValue = "10",value="pageSize") int pageSize);

    @RequestMapping(value = "/links/edmlEdmpLinkId",method = RequestMethod.GET)
    Result checkLinedId(@RequestParam(value = "edmpId") String edmpId, @RequestParam(value = "linkedId")String linkedId);


    @RequestMapping(value = "/links/formula/{id}", method = RequestMethod.PUT)
    Result clearEdmlFormula(@PathVariable(value = "id") String id);

    @RequestMapping(value = "/links/edmLinks", method = RequestMethod.GET)
    Result getEdmLinks(@RequestParam(value = "edmpId") String edmpId,
                       @RequestParam(value = "edmlCond") String edmlCond);

    @RequestMapping(value = "/links/{id}/edmLink", method = RequestMethod.PUT)
    Result moveEdmLink(@PathVariable(value = "id") String id,
                       @RequestBody EdmCond edmCond);
}
