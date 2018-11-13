import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;

import java.util.Map;

/**
 * Created by chenfei on 2017/5/15.
 */
public class MockDataProvider implements DataProvider {

    private Map<String, Object> dataContext = null;

    public MockDataProvider(Map<String, Object> dataContext) {

        if (null == dataContext) {
            throw new FormulaException("the dataContext is null, please passed a not null data context map.");
        }
        this.dataContext = dataContext;
    }

    public Map<String, Object> getDataContext() {
        return dataContext;
    }

    @Override
    public Object getValue(String varName, String defaultValue) {

        Object varValue = dataContext.get(varName);

        return varValue;
    }

}
