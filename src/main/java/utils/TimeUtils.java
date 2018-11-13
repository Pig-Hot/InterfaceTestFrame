package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by zhuran on 2018/11/13 0013
 * 获取时间工具类
 */
public class TimeUtils {

    private TimeUtils() {
        throw new AssertionError();
    }

    public static String getNowTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return formatter.format(currentTime);
    }

    public static String getHoursAfterDate(long start, int i) {
        start += i * 60 * 60 * 1000;
        Date da = new Date(start);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return dateFormat.format(da);
    }

    public static String getHoursBeforeDate(long start, int i) {
        start -= i * 60 * 60 * 1000;
        Date da = new Date(start);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return dateFormat.format(da);
    }

}
