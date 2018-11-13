package com.huntkey.rx.sceo.orm.common.util;


import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.exception.ORMException;

/**
 * Created by linziy on 2017/11/30.
 */
public class OrmAccessUtil {
    /**
     * 检查空字符串数组
     *
     * @param array
     * @throws IllegalArgumentException
     */
    public final static void accessNull(Object... array) {
        for (Object obj : array) {
            if (null == obj) {
                throw new ORMException("Object is not allowed null.");
            }
        }
    }

    public final static void accessNullOrEmputy(String... array) {
        for (String str : array) {
            if (null == str || "".equals(str)) {
                throw new ORMException("String is not allowed null or emputy.");
            }
        }
    }

    /**
     * 校验,判断是否
     *
     * @param sql
     */
    public final static void isValidSelectSql(String sql) {
        accessNullOrEmputy(sql);
        if (!VerifySqlUtil.verifySql(SQLCurdEnum.SELECT, sql)) {
            throw new ORMException("It just allowed to be selected mode");
        }
        return;
    }


}
