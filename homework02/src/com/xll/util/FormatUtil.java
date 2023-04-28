package com.xll.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public class FormatUtil {

    public static String formatMoney(BigDecimal money) {
        return DecimalFormat.getCurrencyInstance(Locale.CHINA).format(money);
    }

    public static BigDecimal parseMoney(String money) {
        try {
            return (BigDecimal) DecimalFormat.getCurrencyInstance(Locale.CHINA).parse(money);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatDateShort(Date date) {

        return new SimpleDateFormat("yyyy年M月", Locale.CHINA).format(date);
    }

    public static String formatDateLong(Date date) {

        return new SimpleDateFormat("yyyy年M月d日", Locale.CHINA).format(date);
    }

    public static Date parseDateShort(String sDate) {
        try {
            return new Date(new SimpleDateFormat("yyyy年M月", Locale.CHINA).parse(sDate).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parseDateLong(String sDate) {
        try {
            return new Date(new SimpleDateFormat("yyyy年M月d日", Locale.CHINA).parse(sDate).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatDateTime(long date) {
        return new SimpleDateFormat("yyyy-M-d HH:mm:ss", Locale.CHINA).format(date);
    }


}

