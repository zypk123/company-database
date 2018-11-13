package com.huntkey.rx.sceo.formula.provider.record;

import com.huntkey.rx.sceo.formula.common.model.SourceSystemRecordMapping;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import com.huntkey.rx.sceo.formula.provider.record.service.RecordMappingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhangyu
 * @create 2017-07-21 17:55
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class RecordMappingServiceTest {

    @Autowired
    RecordMappingService recordMappingService;

    @Test
    public void testAddSourceSystemRecordMapping() {
        SourceSystemRecordMapping sourceSystemRecordMapping = new SourceSystemRecordMapping();
        sourceSystemRecordMapping.setSourceName("Modeler系统");
        sourceSystemRecordMapping.setSourceMappingId("1234566987");
        sourceSystemRecordMapping.setSourceMappingType("1");
        sourceSystemRecordMapping.setInsideId("987654321");
        sourceSystemRecordMapping.setInsideType("1");
        int renInt = recordMappingService.addSourceSystemRecordMapping(sourceSystemRecordMapping);
        System.out.println("===================" + renInt);
    }

    @Test
    public void testUpdateSourceSystemRecordMapping() {
        SourceSystemRecordMapping sourceSystemRecordMapping = new SourceSystemRecordMapping();
        sourceSystemRecordMapping.setRecdId("d94a73cc836f47d78152e7ecbe64fe49");
        sourceSystemRecordMapping.setSourceName("ID系统");
        sourceSystemRecordMapping.setSourceMappingId("1234566987");
        sourceSystemRecordMapping.setSourceMappingType("1");
        sourceSystemRecordMapping.setInsideId("987654321");
        sourceSystemRecordMapping.setInsideType("1");
        System.out.println("===================" + recordMappingService.updateSourceSystemRecordMapping(sourceSystemRecordMapping));
    }


}
