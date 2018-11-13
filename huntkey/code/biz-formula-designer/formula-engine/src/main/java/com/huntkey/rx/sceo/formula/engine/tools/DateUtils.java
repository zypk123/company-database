package com.huntkey.rx.sceo.formula.engine.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenfei1 on 2017/8/29 0029.
 */
public final class DateUtils {

    /**
     * hack for orm date type.
     *
     * @param dateStr
     * @return
     */
    public static Date format(String dateStr) {
        try {
            DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setLenient(true);
            dateFormat.parse(dateStr);
            Date date = (Date) dateFormat.parse(dateStr);
            return date;

        } catch (Exception e) {
            Date date = null;
            try {
                DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(true);
                dateFormat.parse(dateStr);
                date = (Date) dateFormat.parse(dateStr);
            } catch (Exception e1) {
                return null;
            }
            return date;
        }
    }

}
