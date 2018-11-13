package com.huntkey.rx.sceo.formula.provider.engine.service.impl;

import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.model.FtmFunctionClassify;
import com.huntkey.rx.sceo.formula.engine.function.FunctionHandlerBuildIn;
import com.huntkey.rx.sceo.formula.provider.engine.dao.TfmFunctionDefinitionMapper;
import com.huntkey.rx.sceo.formula.provider.engine.service.FunctionDescriberService;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.service.impl.DefineFunctionServiceImpl;
import com.huntkey.rx.sceo.formula.provider.function.functionclassify.dao.FtmFunctionClassifyMapper;
import com.huntkey.rx.sceo.formula.provider.function.functionclassify.service.FunctionClassifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author chenfei on 2017/6/15.
 */
@Service
public class FunctionDescriberServiceImpl implements FunctionDescriberService {

    private Logger logger = LoggerFactory.getLogger(FunctionDescriberServiceImpl.class);

    @Autowired
    private TfmFunctionDefinitionMapper definitionMapper;

    @Autowired
    private FtmFunctionClassifyMapper classifyMapper;

    @Autowired
    private FunctionHandlerBuildIn functionHandlerBuildIn;

    @Autowired
    private FunctionClassifyService functionClassifyService;

    @Override
    public List<FunctionDescriber> loadAllFunctions() {

        try {

            List<FunctionDescriber> list = functionHandlerBuildIn.loadFunctionDescriber();
            List<FunctionDescriber> listSorted = new ArrayList<FunctionDescriber>();
            sortClassifyOrder(list);
            sortFunOrder(list, listSorted);

            return listSorted;
        } catch (Exception e) {
            String errMsg = "Some error occured.";
            throw new RuntimeException(errMsg, e);
        }
    }


    /**
     * @param list
     * @deprecated
     */
    private void wrapDesc(List<FunctionDescriber> list) {

        for (FunctionDescriber fd : list) {
            String funDesc = fd.getFunDesc();

            funDesc = funDesc.trim();

            System.out.println(funDesc);
        }
    }

    private void sortFunOrder(List<FunctionDescriber> list, List<FunctionDescriber> listSorted) {

        for (String classify : orderMap.keySet()) {

            List<FunctionDescriber> tmp = new ArrayList<FunctionDescriber>();
            for (FunctionDescriber fd : list) {
                if (fd.getFunClassify().equals(classify)) {
                    tmp.add(fd);
                }
            }

            Collections.sort(tmp, new Comparator<FunctionDescriber>() {
                @Override
                public int compare(FunctionDescriber o1, FunctionDescriber o2) {

                    if (o1.getFunName().compareTo(o2.getFunName()) > 0) {
                        return 1;
                    } else if (o1.getFunName().compareTo(o2.getFunName()) < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });

            int counter = 0;

            for (FunctionDescriber fd : tmp) {
                fd.setFunOrder(counter++);
            }

            listSorted.addAll(tmp);
        }
    }

    private Map<String, Integer> orderMap = new HashMap<String, Integer>();

    @PostConstruct
    public void init() {

        int counter = 0;
        orderMap.put("math", counter ++);
        orderMap.put("logic", counter ++);
        orderMap.put("text", counter ++);
        orderMap.put("date", counter ++);
        orderMap.put("object", counter ++);

        List<FtmFunctionClassify> funcClassifyList = functionClassifyService.getFtmFunctionClassifyList();
        List<String> classifyList = new ArrayList<String>();

        for (FtmFunctionClassify classify : funcClassifyList) {
            String classifyCode = classify.getFnccClassifyCode();
            classifyList.add(classifyCode);
        }

        Collections.sort(classifyList);

        logger.info("classify list: {}", classifyList);

        for (String cf : classifyList) {
            String classifyShortName = cf.substring(cf.lastIndexOf(".") + 1)
                    .toLowerCase().replace("function", "");
            orderMap.put(classifyShortName, counter++);
        }

        logger.info("orderMap: {}", orderMap);
    }

    @Override
    public void addClassify(String classify) {

        String classifyShortName = classify.substring(classify.lastIndexOf(".") + 1)
                .toLowerCase().replace("function", "");
        if (orderMap.get(classifyShortName) == null) {
            orderMap.put(classifyShortName, orderMap.size());
        }
    }

    @Override
    public void removeClassify(String classify) {
        String classifyShortName = classify.substring(classify.lastIndexOf(".") + 1)
                .toLowerCase().replace("function", "");
        if (orderMap.get(classifyShortName) == null) {
            orderMap.remove(classifyShortName);
        }
    }

    private void sortClassifyOrder(List<FunctionDescriber> list) {

        for (FunctionDescriber fd : list) {

            logger.info("classify: {}", fd.getFunClassify());

            try {
                fd.setFunClassifyOrder(orderMap.get(fd.getFunClassify()));
            } catch (Exception e) {
                logger.error("classify: {} not exisits in orderMap: {}", fd.getFunClassify(), orderMap);
                e.printStackTrace();
            }
        }

    }

    /**
     * 加载所有自定义函数描述
     * @deprecated
     */
    @Override
    public List<FunctionDescriber> loadCustomizeFunctions() {
        // nothing to do.
        return new ArrayList<FunctionDescriber>();
    }

}
