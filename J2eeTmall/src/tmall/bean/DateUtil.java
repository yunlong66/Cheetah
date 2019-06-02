package tmall.bean;

import java.util.Date;
import java.sql.Timestamp;

/*
用于java.util.Date类与java.sql.Timestamp 类的互相转换
Mysql中使用的是datetime类型字段，JDBC采用java.sql.Timestamp来获取
 */
public class DateUtil {
    public static Timestamp d2t(Date d) {
        if (null == d)
            return null;
        return new Timestamp(d.getTime());
    }

    public static Date t2d(Timestamp t) {
        if (null == t)
            return null;
        return new Date(t.getTime());
    }
}
