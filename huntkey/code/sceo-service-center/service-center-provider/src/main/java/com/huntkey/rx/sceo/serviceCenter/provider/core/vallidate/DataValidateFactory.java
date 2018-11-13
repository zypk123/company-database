package com.huntkey.rx.sceo.serviceCenter.provider.core.vallidate;

import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DBType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/***********************************************************************
 * @author chenxj
 *
 * @email: kaleson@163.com											  
 *
 * @date : 2017年9月27日 下午3:51:01											 
 *
 **********************************************************************/
@Component(value = "dataValidateFactory")
public class DataValidateFactory {

    @Qualifier("mysqlValidate")
    @Autowired
    private DataValidate mysqlValidate;

    @Qualifier("hbaseValidate")
    @Autowired
    private DataValidate hbaseValidate;

    public DataValidate getDataValidate(DBType type) {
        if (DBType.HBASE == type) {
            return hbaseValidate;
        } else if (DBType.MYSQL == type) {
            return mysqlValidate;
        } else {
            return null;
        }
    }
}
