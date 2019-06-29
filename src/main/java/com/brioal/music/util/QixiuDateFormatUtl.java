package com.brioal.music.util;


import com.brioal.music.bean.tool.EntityBean;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QixiuDateFormatUtl {

    /**
     * 给数据实体设置创建时间
     *
     * @param entityBean
     * @return
     */
    public static void handleEdit(EntityBean entityBean) {
        entityBean.setEditTime(getCurrentTime());
    }

    /**
     * 给数据实体设置创建时间
     *
     * @param entityBean
     * @return
     */
    public static void handleCreate(EntityBean entityBean) {
        entityBean.setCreateTime(getCurrentTime());
        entityBean.setEditTime(getCurrentTime());
    }


    /**
     * 返回当前的时间
     *
     * @return
     */
    public static Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 返回今天凌晨的时间
     *
     * @return
     */
    public static Timestamp getTodayZeroTime() {
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        Timestamp timeStamp = new Timestamp(date.getTime());
        return timeStamp;
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int currentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 格式转换
     *
     * @param timestamp
     * @param format
     * @return
     */
    public static String formatDate(Timestamp timestamp, String format) {
        SimpleDateFormat util = new SimpleDateFormat(format);
        return util.format(timestamp);
    }

    /**
     * 格式转换
     *
     * @param str
     * @param format
     * @return
     */
    public static Timestamp parseDate(String str, String format) {
        try {
            SimpleDateFormat util = new SimpleDateFormat(format);
            Date date = util.parse(str);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析日期
     *
     * @param str
     * @return
     */
    public static Timestamp parseDateAndTime(String str) {
        return parseDate(str, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 返回完整的日期
     *
     * @param timestamp
     * @return
     */
    public static String formatFullDate(Timestamp timestamp) {
        if (timestamp == null) {
            return "";
        }
        return formatDate(timestamp, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回今天的日期用作文件的路径
     *
     * @return
     */
    public static String getTodayDateForFilePath() {
        String result = formatDate(new Timestamp(System.currentTimeMillis()), "yyyy_MM_dd");
        return result;
    }

    /**
     * 获取当前的完整时间 yyyy-MM-dd mm:HH:ss
     *
     * @return
     */
    public static String getDetailDate() {
        return formatFullDate(getCurrentTime());
    }

    /**
     * 是否处于时间区间当中
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isDuring(Timestamp startTime, Timestamp endTime) {
        if (startTime == null || endTime == null) {
            return true;
        }
        Timestamp current = getCurrentTime();
        if (!current.after(startTime)) {
            return false;
        }
        if (!current.before(endTime)) {
            return false;
        }
        return true;
    }

    /**
     * 返回活动剩余的时间
     *
     * @param endTime
     * @return
     */
    public static String getSaleActivityLeftTimeStr(Timestamp endTime) {
        if (endTime == null) {
            return "";
        }
        Timestamp currentTime = QixiuDateFormatUtl.getCurrentTime();
        if (currentTime.after(endTime)) {
            // 过期了
            return "0 天 0 小时 0 分钟";
        }
        // 剩余的时间
        long currentTimeMill = currentTime.getTime();
        // 结束时间的毫秒值
        long endTimeMill = endTime.getTime();
        // 剩余时间
        long leftTime = endTimeMill - currentTimeMill;
        if (leftTime <= 0) {
            return "0 天 0 小时 0 分钟";
        }
        // 获取剩余的天数
        long days = leftTime % (24 * 60 * 60 * 1000);
        leftTime = leftTime - (days * 24 * 60 * 60 * 1000);
        // 剩余的小时数量
        long hours = leftTime % (60 * 60 * 1000);
        leftTime = leftTime - (hours * 60 * 60 * 1000);
        // 剩余的分钟
        long mis = leftTime % (60 * 1000);
        // 返回格式化之后的字符串
        return days + " 天 " + hours + " 小时 " + mis + " 分钟";
    }
}
