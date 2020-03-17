package com.android.xzl.myapplication.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wulei on 2019/12/10.
 */
public class TimeUtils {

    /**
     * 日期选择
     * @param context
     * @param themeResId
     * @param tv
     * @param calendar
     */
    public static void showDatePickerDialog(Context context, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(context, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                String realMonth;
                String realDay;

                if (monthOfYear + 1 > 9) {
                    realMonth = (monthOfYear + 1) + "";
                } else {
                    realMonth = "0" + (monthOfYear + 1);
                }

                if (dayOfMonth > 9) {
                    realDay = dayOfMonth + "";
                }else {
                    realDay = "0" + dayOfMonth;
                }

                tv.setText(year + "-" + realMonth + "-" + realDay);
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    /**
   获取该月份的第一天
     */
    public static String getSupportBeginDayofMonth() {
        //获取前月的第一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cal_1.getTime());
        return firstDay;
    }

    /*
    获取当前时间
    */
    public static String getCurrentDay(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String realmonth;
        String realDay;
        if (month < 10) {
            realmonth = "0" + month;
        }else {
            realmonth = "" + month;
        }
        if (day < 10) {
            realDay = "0" + day;
        } else {
            realDay = "" + day;
        }

        return year + "-" + realmonth + "-" + realDay;
    }


    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    /**
     * 判断是否在同一个月
     * @param startDate yyyy-MM-dd
     * @param endDate yyyy-MM-dd
     * @return false:不在同一个月内，true在同一个月内
     */
    public static boolean isMonth(String startDate,String endDate){
        if(margin(startDate, endDate)>31){
            return false;
        }
        int startMonth = Integer.parseInt(startDate.substring(5, 7));
        int endMonth = Integer.parseInt(endDate.substring(5, 7));
        if(startMonth==endMonth){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 计算开始日期和结束日期差
     * @param startDate yyyy-MM-dd
     * @param endDate yyyy-MM-dd
     * @return
     */
    private static int margin(String startDate,String endDate){
        ParsePosition pos = new ParsePosition(0);
        ParsePosition pos2 = new ParsePosition(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date ds = sdf.parse(startDate, pos);
        Date de = sdf.parse(endDate, pos2);
        long l = de.getTime()-ds.getTime();
        int margin = (int)(l/24*60*60*1000);
        return margin;
    }


    /**
     * 计算时间
     * @param startTime ： 开始时间
     * @param endTime  ： 结束时间
     * @return
     */
    public static int caculateTotalTime(String startTime,String endTime) {
        SimpleDateFormat formatter =   new SimpleDateFormat( "yyyy-MM-dd");
        Date date1=null;
        Date date = null;
        Long l = 0L;
        try {
            date = formatter.parse(startTime);
            long ts = date.getTime();
            date1 =  formatter.parse(endTime);
            long ts1 = date1.getTime();

            l = (ts1 - ts) / (1000 * 60 * 60 * 24);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l.intValue()+1;
    }

    public static String[] getNextWeek(String queryDate) throws
            ParseException{

        String[] dateMap = new String[2];

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal1 = Calendar.getInstance();

        Calendar cal2 =Calendar.getInstance();

        cal1.setTime(sdf.parse(queryDate));

        cal2.setTime(sdf.parse(queryDate));

        int dayWeek = cal1.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天

        if(dayWeek == 1){

            cal1.add(Calendar.DAY_OF_MONTH, 1);

            cal2.add(Calendar.DAY_OF_MONTH, 7);
        } else {

            cal1.add(Calendar.DAY_OF_MONTH, 1-dayWeek+8);

            cal2.add(Calendar.DAY_OF_MONTH, 1-dayWeek+14);

        }

        // 下周 周一日期

        String     beginDateNext = sdf.format(cal1.getTime());

        // 下周 周日日期

        String endDateNext  = sdf.format(cal2.getTime());
        dateMap[0] = beginDateNext;
        dateMap[1] = endDateNext;
//        dateMap.put("beginDateNext", beginDateNext);
//
//        dateMap.put("endDateNext", endDateNext);

        return dateMap;
    }

    public static String getThisWeekMonday() {
        Date date1 = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        Date time = cal.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(time);
        return format;
    }

    public static void onYearDayPicker(Activity activity, final TextView textView,int year,int month,int day) {
        cn.qqtheme.framework.picker.DatePicker picker = new cn.qqtheme.framework.picker.DatePicker(activity, cn.qqtheme.framework.picker.DatePicker.YEAR_MONTH_DAY);
        picker.setGravity(Gravity.CENTER_HORIZONTAL);
        picker.setWidth((int) (picker.getScreenWidthPixels() * 0.8));
        picker.setRangeStart(2010, 1, 1);
        picker.setRangeEnd(2050, 12, 31);
        picker.setSelectedItem(year, month, day);
        picker.setOnDatePickListener(new cn.qqtheme.framework.picker.DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                textView.setText(year + "-" + month + "-" + day);
            }
        });
        picker.show();
    }

    /**
     * 去掉指定字符串的开头的指定字符
     * @param stream 原始字符串
     * @param trim 要删除的字符串
     * @return
     */
    public static String StringStartTrim(String stream, String trim) {
        // null或者空字符串的时候不处理
        if (stream == null || stream.length() == 0 || trim == null || trim.length() == 0) {
            return stream;
        }
        // 要删除的字符串结束位置
        int end;
        // 正规表达式
        String regPattern = "[" + trim + "]*+";
        Pattern pattern = Pattern.compile(regPattern, Pattern.CASE_INSENSITIVE);
        // 去掉原始字符串开头位置的指定字符
        Matcher matcher = pattern.matcher(stream);
        if (matcher.lookingAt()) {
            end = matcher.end();
            stream = stream.substring(end);
        }
        // 返回处理后的字符串
        return stream;
    }
}
