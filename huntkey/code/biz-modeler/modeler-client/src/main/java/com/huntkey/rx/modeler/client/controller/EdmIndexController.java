package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.client.feign.EdmIndexService;
import com.huntkey.rx.modeler.client.feign.EdmPropertyService;
import com.huntkey.rx.modeler.common.model.EdmProperty;
import com.huntkey.rx.modeler.common.model.vo.EdmIndexVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 * @ClassName: EdmIndexController
 * @Description: 索引接口Controller
 * @author: zhangyu
 * @date: 2017年4月12日上午11:35:15
 *
 */
@RestController
@RequestMapping("/v1/indexes")
public class EdmIndexController {

    @Autowired
    public EdmIndexService edmIndexService;

    /**
     * 新增索引
     * @param edmIndexVO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmIndexVO edmIndexVO) {
        Result result =  edmIndexService.insert(edmIndexVO);
        return result;
    }

    /**
     * 修改索引
     * @param edmIndexVO
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@Validated @RequestBody EdmIndexVO edmIndexVO) {
        Result result =    edmIndexService.update(edmIndexVO);
        return result;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "ids") String [] ids) {
        Result result = edmIndexService.deletes(ids);
        return result;
    }
}
