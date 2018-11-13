import com.hunkey.SpringBootDemo21Application;
import com.hunkey.entity.ParamsVo;
import com.hunkey.feign.DriverMethodService;
import com.hunkey.util.ExecUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lulx on 2017/10/18 0018 下午 5:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootDemo21Application.class)
public class DriverMethodTest {

    @Autowired
    private DriverMethodService driverMethodService;

    /**
     * 同步执行
     */
    @Test
    public void testExecSync() {
        ParamsVo params = new ParamsVo();
        Map<String, Object> map = new HashMap<>();
        map.put("id", "00e9a2b759114522a4dab5be28a3c19b");
        params.setClassName("salesposition");
        params.setMethodName("load");
        params.setParamObj(map);
        //Result exec = driverMethodService.exec(params);
        Result exec = ExecUtil.exec(params);
        System.out.println(exec);
    }
}
