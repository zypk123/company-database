package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.common.model.EdmProperty;
import com.huntkey.rx.modeler.common.model.vo.EdmIndexVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by zhuyj on 2017/4/16.
 */
@FeignClient(value = "modeler-provider")
public interface EdmIndexService {

    @RequestMapping(value = "/indexes", method = RequestMethod.POST)
    Result insert(@RequestBody EdmIndexVO edmIndexVO);

    @RequestMapping(value = "/indexes", method = RequestMethod.PUT)
    Result update(@RequestBody EdmIndexVO edmIndexVO);

    @RequestMapping(value = "/indexes/batch/{ids}", method = RequestMethod.DELETE)
    Result deletes(@PathVariable(value = "ids") String ids[]);

    @RequestMapping(value = "/classes/{edmcId}/indexName/validator", method = RequestMethod.GET)
    Result checkIndexName(@RequestParam(value = "indexName") String indexName,
                          @PathVariable(value = "edmcId") String edmcId);

}
