package com.xunmeng.youxuan.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ClassName: DateTimeUtil
 * Package: com.xunmeng.youxuan.utils
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/23 14:02
 * @Version 1.0
 */
public class DateTimeUtil {

    /**
     * description: 结束时间转换成localDateTime
     * @param:
     * @return: java.lang.String
     * @author LTM
     * @date: 2023/5/23 14:06
     */
    public static String getYearMonth(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMM");
        return LocalDateTime.now().format(df);
    }

    public static String getYearMonth(int month) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
        return LocalDateTime.now().plusMonths(month).format(df);
    }

    public static String getYearMonth(LocalDate localDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
        return localDate.format(df);
    }

    /**
     *  结束时间转换为LocalDateTime 格式：年月日
     * @return
     */
    public static String getYearMonthDay() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDateTime.now().format(df);
    }

    /**
     *  将LocalDateTime转换为 2020-01-02形式
     * @param date
     * @return
     */
    public static String getTimeYMDByLocalDateTime(LocalDateTime date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(dtf);
    }
}
