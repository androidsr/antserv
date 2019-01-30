package cn.zft.antserv.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class StaticFunc {
    static DateFormat dateTime = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
    static DateFormat date_time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static DateFormat date = new java.text.SimpleDateFormat("yyyyMMdd");
    static DateFormat time = new java.text.SimpleDateFormat("HHmmss");

    public static String date() {
        return date.format(new Date());
    }

    public static String dateTime() {
        return dateTime.format(new Date());
    }

    public static String dateTime(Object date) {
        return dateTime.format(date);
    }

    public static String dateFmtTime() {
        return date_time.format(new Date());
    }

    public static String dateFmtTime(Object date) {
        return date_time.format(date);
    }

    public static String time() {
        return time.format(new Date());
    }

    public static String dateAddDay(String dateStr, int day) {
        Date dt;
        try {
            dt = date.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr;
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DAY_OF_YEAR, day);
        Date dt1 = rightNow.getTime();
        return date.format(dt1);
    }

    public static void page(Map<String, Object> params) {
        Integer cur = Integer.parseInt(params.remove("cur").toString());
        Integer size = Integer.parseInt(params.remove("size").toString());
        params.put("offset", (cur - 1) * size + "");
        params.put("limit", size);
    }

    public static String getRstr(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
