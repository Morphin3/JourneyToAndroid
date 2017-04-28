package com.taoism.journeytoandroid.utils;

import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhengwen on 15-6-12.
 */
public class TimeUtil {
    //TODO 格式化应该考虑
    public static final int second = 1000;

    public static final int minute = 60 * 1000;
    public static final int hour = 60 * 60 * 1000;
    public static final int day = 24 * 60 * 60 * 1000;
    private static final SimpleDateFormat TODAY_FORMAT = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat YEAR_MONTH_DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat MONTH_DAY_FORMAT = new SimpleDateFormat("MM-dd");

    public static String format(long time) {
        long timebefore = SystemUtil.getCurrentTimeMillis() - time;
        if (timebefore >= 0) {
            if (timebefore < minute) {
                return ("刚刚");
            } else if (timebefore < hour) {
                return (String.format("%d分钟前", timebefore / minute));

            } else if (timebefore < day && isToday(time)) {
                Date curDate = new Date(time);
                return TODAY_FORMAT.format(curDate);
            } else if (isCurrentYear(time)) {
                Date curDate = new Date(time);
                return MONTH_DAY_FORMAT.format(curDate);
            } else {
                Date curDate = new Date(time);
                return YEAR_MONTH_DAY_FORMAT.format(curDate);
            }
        } else {
            timebefore = -timebefore;
            if (timebefore < minute) {
                return ("马上");
            } else if (timebefore < hour) {
                return (String.format("%d分钟后", timebefore / minute));

            } else if (timebefore < day) {
                return (String.format("%d小时后", timebefore / hour));
            } else {
                return (String.format("%d天后", timebefore / day));
            }
        }
    }

    /**
     * 消息页距离活动结束时间
     **/
    public static String formatActivityEndTime(long time) {
        long timebefore = SystemUtil.getCurrentTimeMillis() - time;
        if (timebefore >= 0) {
            if (timebefore < minute) {
                return ("刚刚");
            } else if (timebefore < hour) {
                return (String.format("%d分钟前", timebefore / minute));

            } else if (timebefore < day && isToday(time)) {
                Date curDate = new Date(time);
                return TODAY_FORMAT.format(curDate);
            } else if (isCurrentYear(time)) {
                Date curDate = new Date(time);
                return MONTH_DAY_FORMAT.format(curDate);
            } else {
                Date curDate = new Date(time);
                return YEAR_MONTH_DAY_FORMAT.format(curDate);
            }
        } else {
            timebefore = -timebefore;
            if (timebefore < minute) {
                return ("马上");
            } else if (timebefore < hour) {
                return (String.format("%d", timebefore / minute));

            } else if (timebefore < day) {
                return (String.format("%d", timebefore / hour));
            } else {
                return (String.format("%d", timebefore / day));
            }
        }
    }

    public static String formatWithoutFuture(long time) {
        long timebefore = SystemUtil.getCurrentTimeMillis() - time;
        if (timebefore >= 0) {
            if (timebefore < minute) {
                return ("刚刚");
            } else if (timebefore < hour) {
                return (String.format("%d分钟前", timebefore / minute));

            } else if (timebefore < day && isToday(time)) {
                Date curDate = new Date(time);
                return TODAY_FORMAT.format(curDate);
            } else if (isCurrentYear(time)) {
                Date curDate = new Date(time);
                return MONTH_DAY_FORMAT.format(curDate);
            } else {
                Date curDate = new Date(time);
                return YEAR_MONTH_DAY_FORMAT.format(curDate);
            }
        } else {
            return ("刚刚");
        }
    }


    public static String formatToMMdd(long time) {
        if (isCurrentYear(time)) {
            Date curDate = new Date(time);
            return MONTH_DAY_FORMAT.format(curDate);
        } else {
            Date curDate = new Date(time);
            return YEAR_MONTH_DAY_FORMAT.format(curDate);
        }
    }

    public static boolean isToday(long when) {
        Time time = new Time();
        time.set(when);

        int thenYear = time.year;
        int thenMonth = time.month;
        int thenMonthDay = time.monthDay;

        time.set(SystemUtil.getCurrentTimeMillis());
        return (thenYear == time.year)
                && (thenMonth == time.month)
                && (thenMonthDay == time.monthDay);
    }

    public static boolean isCurrentYear(long when) {
        Time time = new Time();
        time.set(when);

        int thenYear = time.year;

        time.set(SystemUtil.getCurrentTimeMillis());
        return (thenYear == time.year);
    }


    /**
     * 时间差是否超过 differ 分钟
     *
     * @param differ
     * @param timeNew
     * @param timeOld
     * @return
     */
    public static boolean isDifferMinutes(int differ, long timeNew, long timeOld) {
        return ((timeNew - timeOld) / minute > differ - 1);
    }

    public static int getMonth(long when) {
        Time time = new Time();
        time.set(when);
        return time.month + 1;
    }

    public static String getMonthStr(long when) {

        switch (getMonth(when)) {
            case 1:
                return "JAN";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "APR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AUG";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            case 12:
                return "DEC";
            default:
                return "null";
        }
    }

    public static String getMonthDay(long when) {
        Time time = new Time();
        time.set(when);
        return time.monthDay + "";
    }

    public static String ms(long delta) {
        return String.format("%d ms", delta);
    }

}

