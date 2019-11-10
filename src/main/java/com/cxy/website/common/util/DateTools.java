package com.cxy.website.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: 石文静
 * @Description: 日期时间工具类
 * @CreateDate: 2019/9/27  15:55
 * @UpdateDate: 2019/9/27  15:55
 * @Version: V1
 */
public class DateTools {

    /*
     * @Author 石文静
     * @Description 获取系统时间"yyyy-MM-dd"
     * @CreateDate 2019/9/27 16:13
     * @Version V1
     */
    public static String getSystemDateYYYYMMDD() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(
                System.currentTimeMillis()));
    }

    /*
     * @Author 石文静
     * @Description 获取系统时间"yyyy-MM-dd HH:mm:ss"
     * @CreateDate 2019/9/27 16:24
     * @Version V1
     */
    public static String getStandSystemDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(
                System.currentTimeMillis()));
    }

    /*
     * @Author 石文静
     * @Description 获取系统时间yyyyMMddHHmmss
     * @CreateDate 2019/9/27 16:20
     * @Version V1
     */
    public static String getDateyyMMddHHmmss(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format((System.currentTimeMillis()));
    }
    /*
     * @Author 余发
     * @Description 获取格式化时间yyyyMMddHHmmss
     * @CreateDate 2019/9/27 16:20
     * @Version V1
     */
    public static String getDateyyMMddHHmmss(Date datetime){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(datetime);
    }
    /*
     * @Author 石文静
     * @Description Date转String   yyyyMMdd格式
     * @CreateDate 2019/9/27 16:26
     * @Version V1
     */
    public static String getDateyyMMdd(Date date) throws Exception{
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /*
     * @Author 石文静
     * @Description Date转String   yyyy-MM-dd格式
     * @CreateDate 2019/9/27 16:22
     * @Version V1
     */
    public static String getDateYYYYMMDD(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /*
     * @Author 石文静
     * @Description Date转String   yyyy-MM-dd HH:mm:ss格式
     * @CreateDate 2019/9/27 16:21
     * @Version V1
     */
    public static String getDateyyyy_MM_dd_HH_mm_ss(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * String类型转Date  返回2018-12-17 17:00:00
     * @param datetime
     * @return String
     */
    public static Date dateFormat3(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * String 类型转Date  返回2018-12-17
     * @param datetime
     * @return
     */
    public static Date dateFormat2(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取月末最后一天  返回具体日期
     * @param sDate
     * @return
     */
    public static Date getMonthMaxDay2(String sDate) {
        SimpleDateFormat sdf_full = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf_full.parse(sDate + "-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        int last = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, last);
        return cal.getTime();
    }
    /**
     * 获取月初第一天 返回具体日期
     * @param sDate
     * @return
     */
    public static Date getMonthMinDay2(String sDate) {
        SimpleDateFormat sdf_full = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf_full.parse(sDate + "-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        int first = cal.getActualMinimum(Calendar.DATE);
        cal.set(Calendar.DATE, first);
        return cal.getTime();
    }


    /**
     * 时间+分
     * @param date
     * @param minute
     * @return
     */
    public static String datePlusMinute (Date date,int minute) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MINUTE, minute);
        date = cl.getTime();
        return getDateyyMMddHHmmss(date);
    }
    /**
     * 时间+天
     * @param date
     * @param day
     * @return
     */
    public static Date datePlusDay(Date date,int day) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.DATE, day);
        date = cl.getTime();
        return date;
    }
    /**
     * 时间+月
     * @param date
     * @param month
     * @return
     */
    public static Date datePlusMonth(Date date,int month) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MONTH, month);
        date = cl.getTime();
        return date;
    }
    /**
     * 时间+年
     * @param date
     * @param year
     * @return
     */
    public static Date datePlusYear(Date date,int year) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.YEAR, year);
        date = cl.getTime();
        return date;
    }

    /**
     * 计算两个日期之间相差的天数      返回int
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的月数
     */
    public static int monthsBetween(Date date1,Date date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
//        c1.setTime(sdf.parse(sdf.format(new Date())));
        c1.setTime(sdf.parse(sdf.format(date1)));
        c2.setTime(sdf.parse(sdf.format(date2)));
        int month=c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        int result = yearsBetween(sdf.parse(sdf.format(date1)), sdf.parse(sdf.format(date2))) * 12 + month;
        return result;
    }
    /**
     * 计算两个日期之间相差的年数
     */
    public static int yearsBetween(Date start, Date end) throws ParseException {
        Calendar cstart = Calendar.getInstance();
        Calendar cend = Calendar.getInstance();
        cstart.setTime(start);
        cend.setTime(end);
        return (cend.get(Calendar.YEAR) - cstart.get(Calendar.YEAR));
    }

    /*
     * @Author 石文静
     * @Description 判断是否是日期格式
     * @CreateDate 2019/9/27 16:20
     * @Version V1
     */
    public static boolean IsTimeDormat(String data,String format){
        if(data==null||data==""){//判断是否是空值
            return false;
        }
        SimpleDateFormat simple=new SimpleDateFormat(format);
        try{//时间成功转换  说明格式正确
            simple.parse(data);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
