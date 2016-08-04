package com.ya.deliverydemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YaoFengjuan on 2016/6/20.
 */
public class Utils {
    /**
     * 获取日期(2014-01-23)
     *
     * @param str
     * @return
     */
    public static String getDate(String str) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date date = sdr.parse(str);
            return sdr.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取时间( 23:21:27)
     *
     * @param str
     * @return
     */
    public static String getTime(String str) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        try {
            Date date = sdr.parse(str);
            int hour = date.getHours();
            int minute = date.getMinutes();
            if (hour < 10) {
                if (minute < 10) {
                    return "0" + hour + ":" + "0" + minute;
                } else {
                    return "0" + hour + ":" + minute;
                }

            } else {
                if (minute < 10) {
                    return hour + ":" + "0" + minute;
                } else {
                    return hour + ":" + minute;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}
