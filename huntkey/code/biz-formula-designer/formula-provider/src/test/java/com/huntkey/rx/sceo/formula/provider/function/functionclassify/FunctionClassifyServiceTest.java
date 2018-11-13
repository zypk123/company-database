package com.huntkey.rx.sceo.formula.provider.function.functionclassify;

import com.huntkey.rx.sceo.formula.common.model.FtmFunctionClassify;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import com.huntkey.rx.sceo.formula.provider.function.functionclassify.service.FunctionClassifyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author zhangyu
 * @create 2017-07-27 11:10
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class FunctionClassifyServiceTest {

    @Autowired
    FunctionClassifyService functionClassifyService;

    @Test
    public void testCreateFunctionClassify() throws Exception {
        FtmFunctionClassify functionClassify = new FtmFunctionClassify();
        functionClassify.setFnccClassifyCode("com.huntkey.rx.sceo.formula.common.function.play.PlayFunction");
        functionClassify.setFnccClassifyName("玩具");
        functionClassify.setFnccClassifyDesc("玩具函数");
        functionClassifyService.createFunctionClassify(functionClassify);
    }

    @Test
    public void testGetFuccClassifyBinaryById() throws Exception {
        System.out.println("=============" + functionClassifyService.getFuccClassifyBinaryById("01a6d892dcd544a2a585b066d9768e66"));
    }

    @Test
    public void testDeleteFunctionClassify() throws Exception {
        functionClassifyService.deleteFunctionClassify("c10da1f3de3143188016f5d1a911c966");
    }

    @Test
    public void testUpdateFunctionClassify() throws Exception {
        FtmFunctionClassify functionClassify = new FtmFunctionClassify();
        functionClassify.setFnccId("c10da1f3de3143188016f5d1a911c966");
        functionClassify.setFnccClassifyCode("com.huntkey.rx.sceo.formula.common.function.good.GoodFunction");
        functionClassify.setFnccClassifyName("商品2");
        functionClassify.setFnccClassifyDesc("商品2描述");
        functionClassifyService.updateFunctionClassify(functionClassify);
    }

    @Test
    public void testGetFtmFunctionClassifyList() throws Exception {
        List<FtmFunctionClassify> ftmFunctionClassifyList = functionClassifyService.getFtmFunctionClassifyList();
        for (FtmFunctionClassify ftmFunctionClassify : ftmFunctionClassifyList) {
            System.out.println("============" + ftmFunctionClassify);
        }
    }
}
