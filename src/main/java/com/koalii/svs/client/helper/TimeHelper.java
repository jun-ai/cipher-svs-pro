package com.koalii.svs.client.helper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeHelper {

    private static SimpleDateFormat defaultSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat defaultShortSdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @Title: dateToLongString @Description: (时间类型转化成长时间格式字符串) @param time
     * 时间参数 @return 返回“yyyy-MM-dd HH:mm:ss”格式的字符串 @throws
     */
    public synchronized static String dateToLongString(Date time) {
        if(time==null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = sdf.format(time);
        return result;
    }

    /***
     * @Title: dateToLongString @Description:
     * TODO(获取当前时间的字符串) @return返回“yyyy-MM-dd HH:mm:ss”格式的字符串 @throws
     */
    public synchronized static String dateToLongString() {
        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = sdf.format(nowTime);
        return result;
    }

    /**
     * @Title: dateToShortString @Description: TODO(时间类型转化成短时间格式字符串) @param time
     * 时间参数 @return 返回“yyyy-MM-dd”格式的字符串 @throws
     */
    public synchronized static String dateToShortString(Date time) {
        if(time!=null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String result = sdf.format(time);
            return result;
        }
        return "";
    }

    /**
     * @Title: dateToString @Description: TODO(将时间类型转化为指定格式的字符串) @param time
     * 时间参数 @param format 需转化成的数据格式,如“yyyyMMdd”等 @return
     * 指定格式的时间字符串 @throws
     */
    public synchronized static String dateToString(Date time, String format) {
        if(time!=null) {
        if (format == null || format.equals(""))
            return defaultSdf.format(time);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String result = sdf.format(time);
        return result;
        }
        return "";
    }

    public synchronized static String sqlDateToString(java.sql.Date time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String result = sdf.format(time);
        return result;
    }

    /**
     * @param time   时间字符串
     * @param format 对应时间字符串的时间格式,如"yyyy-MM-dd"
     * @return Date类型的时间
     * @throws ParseException
     * @throws ParseException 格式转换异常
     * @Title: StringToDate
     * @Description: TODO(将指定字符串格式转化为时间类型)
     */
    public synchronized static Date StringToDate(String time, String format) throws ParseException {
        SimpleDateFormat sdf;
        if (format == null || format.equals(""))
            sdf = defaultSdf;
        else sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            if (time != null && !time.equals("")) {
                date = sdf.parse(time);
            } else
                return null;
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }

    /**
     * 根据格式化时间获取时间长度
     * @param strDate
     * @param format
     * @return
     * @throws ParseException
     */
    public synchronized static long StringToDateTime(String strDate, String format) throws ParseException {
        SimpleDateFormat sdf;
        if (format == null || format.equals(""))
            sdf = defaultSdf;
        else sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            if (strDate != null && !strDate.equals("")) {
                date = sdf.parse(strDate);
                return date.getTime();
            } else
                return -1;
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * 返回当前时间作为ID
     *
     * @return
     */
    public synchronized static String getUniqueString() {
        SimpleDateFormat dfmoth = new SimpleDateFormat("yyyyMMddHHmmss");
        String current = dfmoth.format(Calendar.getInstance().getTime());
        if(current.equals(currentTime)){
            numKey++;
        }else {
            currentTime = current;
            numKey = 0;
        }
        String ID = current + String.format("%08d", numKey);
        return ID;
    }
    private static long numKey = 0;
    private static String currentTime = "";

    /**
     * 返回当前时间（14位）+N位随机数作为编号ID
     *
     * @param randomNum 时间戳后需要增加的随机数数量
     * @return
     */
    public static String getUniqueId(int randomNum) {
        if (randomNum <= 0) randomNum = 2;
        SimpleDateFormat dfmoth = new SimpleDateFormat("yyyyMMddHHmmss");

        String ID = dfmoth.format(Calendar.getInstance().getTime()) + genRandomNum(randomNum);
        return ID;
    }

    /**
     * 返回时间戳作为ID long
     *
     * @return
     */
    public static long getLongTime() {
        Date d = new Date();
        long longtime = d.getTime();
        return longtime;
    }

    /**
     * 返回时间戳作为ID 10位
     *
     * @return
     */
    public static String getTimeId() {
        long time = System.currentTimeMillis() / 1000;
        return String.valueOf(time);
    }


    /**
     * 生成指定长度的随机数
     *
     * @param pwd_len 生成随机数的总长度
     * @return 随机数字符串
     */
    public static String genRandomNum(int pwd_len) {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 10;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        /*
         * char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
         * 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
         * 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
         */

        char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            // 生成随机数，取绝对值，防止生成负数，

            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }

        return pwd.toString();
    }

    /**
     * 时间加减分钟
     * @param date
     * @param n
     * @return
     */
    public synchronized static Date addMinute(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, n);
        return cal.getTime();
    }

    /**
     * @Title: addDate @Description: TODO(时间加减天数) @param time 时间参数 @param num
     * 天数 @return 新的时间 @throws
     */
    public synchronized static Date addDate(Date time, int num) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(time);
        gc.add(Calendar.DAY_OF_MONTH, num);
        return gc.getTime();
    }

    /**
     * @param time 时间参数
     * @param num  天数
     * @return 新的时间
     * @throws
     * @Title: addDate
     * @Description: TODO(时间加减天数)
     */
    public synchronized static String addDate(String time, int num) throws ParseException {
        try {
            Date date = StringToDate(time, "yyyy-MM-dd");
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(date);
            gc.add(Calendar.DAY_OF_MONTH, num);
            return dateToString(gc.getTime(), "yyyy-MM-dd");
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * @Title: addMonth @Description: TODO(时间加减月数量) @param date 时间参数 @param n
     * 月数量 @return 返回新的时间 @throws
     */
    public synchronized static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * @Title: addYear @Description: TODO(时间加减年数量) @param date 时间参数 @param n
     * 年数量 @return 返回新时间 @throws
     */
    public synchronized static Date addYear(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, n);
        return cal.getTime();
    }

    /**
     * @Title: dayOfWeek @Description: TODO(一星期中的第几天，星期一为0，以此类推) @param date
     * 时间 @return 返回一星期中的第几天 @throws
     */
    public synchronized static int dayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return dayOfWeek;
    }

    /**
     * 获取所选日期所在周的周N
     *
     * @param date 所选日期
     * @param num  该周的第几天(1-7,7为周日)
     * @return
     */
    public synchronized static String getDayOfWeek(Date date, int num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (day_of_week == 0)// 判断是否周日
                day_of_week = 7;
            cal.add(Calendar.DATE, -day_of_week + num);
            return sdf.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return sdf.format(new Date());
        }
    }

    /**
     * @param time   时间字符串
     * @param format 指定时间格式
     * @return 时间数字yyyymmdd类型
     * @throws ParseException
     * @throws ParseException 格式转换异常
     * @Title: StringToLongNum
     * @Description: TODO(时间字符串转化成yyyymmdd数字格式)
     */
    public synchronized static int StringToLongNum(String time, String format) throws ParseException {
        Date date = StringToDate(time, format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int result = cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100
                + cal.get(Calendar.DAY_OF_MONTH);
        return result;
    }

    /**
     * @param time   时间字符串
     * @param format 时间格式字符串
     * @return 时间数字yyyymm类型
     * @throws ParseException
     * @throws ParseException 格式转换异常
     * @Title: StringToShortNum
     * @Description: TODO(时间字符串转化成yyyymm数字格式)
     */
    public synchronized static int StringToShortNum(String time, String format) throws ParseException {
        Date date = StringToDate(time, format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int result = cal.get(Calendar.YEAR) * 100 + cal.get(Calendar.MONTH) + 1;
        return result;
    }

    /**
     * @Title: getYear @Description: TODO(获取时间的年) @param date (时间类型) @return
     * year 年份 @throws
     */
    public synchronized static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * @Title: toFirstDay @Description: TODO(将日期转化为指定月的第一天) @param date
     * 指定日期 @return Date 指定月的第一天 @throws
     */
    public synchronized static Date toFirstDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar c = Calendar.getInstance();
        c.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
        return c.getTime();
    }

    /**
     * 获取一个月的最后一天
     *
     * @param date
     * @return
     */
    public synchronized static String toLastDay(Date date, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);// 设置改约第一天
        cal.add(Calendar.MONTH, 1);// 在设置第一天后增加一个月
        cal.add(Calendar.DAY_OF_MONTH, -1);// 获取改约最后一天
        Date lastDayOfMonth = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(lastDayOfMonth);
    }

    /**
     * @Title: setDate @Description: TODO(初始设置日期) @param year 年份 @param month
     * 月份 @param day 天 @return Date 日期 @throws
     */
    public synchronized static Date setDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal.getTime();
    }

    public synchronized static String longToString(long time, String format) {
        int year = (int) (time / 10000);
        int month = (int) (time % 10000 / 100);
        int day = (int) (time % 1000000);
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        Date date = cal.getTime();
        return dateToString(date, format);
    }

    /**
     * @Title: differentDaysByMillisecond @Description: TODO(比较两个时间相差的天数) @param
     * date1 (前) @param date2 (后) @return int 天数 @throws
     */
    public synchronized static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    public static List<String> findDates(Date dBegin, Date dEnd)
    {
        List<String> lDate = new ArrayList();
        lDate.add(defaultShortSdf.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime()))
        {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(defaultShortSdf.format(calBegin.getTime()));
        }
        return lDate;
    }

    public static List<String> findMonths(Date dBegin, Date dEnd)
    {
        List<String> lDate = new ArrayList();
        lDate.add(defaultShortSdf.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime()))
        {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.MONTH, 1);
            lDate.add(defaultShortSdf.format(calBegin.getTime()));
        }
        return lDate;
    }

    @SuppressWarnings("deprecation")
    public synchronized static long getLongDateTime(Date date) {
        try {
            long day = date.getYear() * 10000 + date.getMonth() * 100 + date.getDay();
            return day;
        } catch (Exception ex) {
            return 0;
        }
    }


    /**
     * @param time   时间字符串
     * @param format 对应时间字符串的时间格式,如"yyyy-MM-dd",且此处设置时区
     * @return java.sql.Date类型的时间
     * @throws ParseException
     * @throws ParseException 格式转换异常
     * @Title: StringToSqlDate
     * @Description: TODO(将指定字符串格式转化为时间类型)
     */
    public synchronized static java.sql.Date StringToSqlDate(String time, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 如需在Linux上运行代码，因Linux与本地时区不一致，故需设置，否则相差8小时。
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        Date utilDate = null;
        java.sql.Date sqlDate = null;
        try {
            if (time != null && !time.equals("")) {
                utilDate = sdf.parse(time);
                sqlDate = new java.sql.Date(utilDate.getTime());
            } else
                return null;
        } catch (ParseException e) {
            throw e;
        }
        return sqlDate;
    }

    /**
     * @param time   时间字符串
     * @param format 对应时间字符串的时间格式,如"yyyy-MM-dd HH:mm:ss",且此处设置时区
     * @return Timestamp类型的时间
     * @throws ParseException
     * @throws ParseException 格式转换异常
     * @Title: StringToTimestamp
     * @Description: TODO(将指定字符串格式转化为时间类型)
     */
    public synchronized static Timestamp StringToTimestamp(String time, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 如需在Linux上运行代码，因Linux与本地时区不一致，故需设置，否则相差8小时。
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        Timestamp timestamp = null;
        try {
            if (time != null && !time.equals("")) {
                timestamp = new Timestamp(sdf.parse(time).getTime());
            } else
                return null;
        } catch (ParseException e) {
            throw e;
        }
        return timestamp;
    }

    /**
     * @param date   日期
     * @param format 对应时间字符串的时间格式,如"yyyy-MM-dd",且此处设置时区
     * @return Timestamp类型的时间
     * @throws ParseException
     * @throws ParseException 格式转换异常
     * @Title: dateToTimestamp
     * @Description: TODO(将util.Date转化为TimeStamp类型)
     */
    public synchronized static Timestamp dateToTimestamp(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 如需在Linux上运行代码，因Linux与本地时区不一致，故需设置，否则相差8小时。
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String time = sdf.format(date);
        Timestamp timestamp = null;
        try {
            if (time != null && !time.equals("")) {
                timestamp = new Timestamp(sdf.parse(time).getTime());
            } else
                return null;
        } catch (ParseException e) {
            System.out.println("error dateToTimestamp ParseException : " + e.getMessage());
        }
        return timestamp;
    }
    
    /**根据传入的当前时间获取当前天所在周的周一时间
     * @param time   日期
     * @return:周一的时间字符串，格式为：yyyy-MM-dd
     */
    public synchronized static String convertWeekByDate(Date time) {  
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(time);  
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
        if(1 == dayWeek) {  
          cal.add(Calendar.DAY_OF_MONTH, -1);  
        }  
        System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期  
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值   
        String imptimeBegin = sdf.format(cal.getTime());  
        System.out.println("所在周星期一的日期："+imptimeBegin);  
        cal.add(Calendar.DATE, 2);  
        String imptimeMi = sdf.format(cal.getTime());  
        System.out.println("所在周星期三的日期："+imptimeMi);  
        cal.add(Calendar.DATE, 4);  
        String imptimeEnd = sdf.format(cal.getTime());  
        System.out.println("所在周星期五的日期："+imptimeEnd); 
        return imptimeBegin;
   } 
   
   /**
     * 计算两天时间中间想差多少天
     * @param startDate 开始时间；
     * @param endDate 结束时间；
     * @return 想差的天数；
     * @throws Exception
     */
   public synchronized static int getDifferDay(String startDate,String endDate)throws Exception{
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   Date d1 = format.parse(startDate);
	   Date d2 = format.parse(endDate);
       int diff = (int) (d2.getTime() - d1.getTime());
       int day = diff / (24 * 60 * 60 * 1000);
       return day;
   } 
   
   /***
    * 计算当前时间前多少分钟的时间，min传正，则为当前时间后多少分钟，传负，则为前多少分钟；
    * @param min
    * @return 时间格式为 yyyy-MM-dd HH:mm:ss
    */
   public synchronized static String getTimeBeforeMins(int min){
	   Calendar beforeTime = Calendar.getInstance();
	   beforeTime.add(Calendar.MINUTE,min);// 5分钟之前的时间
	   Date beforeD = beforeTime.getTime();
	   String before = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beforeD);  
	   return before;
   }
   
   public synchronized static Long getDatePoor(String endTime, String startTime)throws Exception {
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date endDate = format.parse(endTime);
	   Date nowDate = format.parse(startTime);
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    long result = hour*60+min;
	    return result;
	}
    public synchronized static Long countDate(Date endDate, Date nowDate){
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        long result = hour*60+min;
        return result;
    }

    /**
     * 毫秒字符串转时间格式
     * @param timeStr
     * @return
     */
    public static synchronized String getTimeFormat(String timeStr) {
        long time = Long.parseLong(timeStr);
        Date date = new Date(time);
        return dateToLongString(date);
      /*  Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        String monthStr = addZero(month);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dayStr = addZero(day);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);//24小时制
        String hourStr = addZero(hour);
        int minute = calendar.get(Calendar.MINUTE);
        String minuteStr = addZero(minute);
        int second = calendar.get(Calendar.SECOND);
        String secondStr =addZero(second);
        return(year + "-" + monthStr  + "-" + dayStr + " "
                + hourStr + ":" + minuteStr + ":" + secondStr);*/
    }
    
    private static String addZero(int param) {
        String paramStr= param<10 ? "0"+param : "" + param ;
        return paramStr;
    }


    private static SimpleDateFormat[] sdfs = {new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"),
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"), new SimpleDateFormat("yyyy-MM-dd"), new SimpleDateFormat("yyyy/MM/dd"),
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSSSSSSSS"), new SimpleDateFormat("yyyyMMddHHmmss"),
            new SimpleDateFormat("yyyy-MM-dd HH-mm-ss")};
    public static String strDate2LongFormat(String strDate) {
        Date date;
        for (SimpleDateFormat sdf : sdfs) {
            try {
                date = sdf.parse(strDate);
                return defaultSdf.format(date);
            }
            catch (Exception e){

            }
        }
        return null;
    }
    /**
     * 获取今天开始时间
     */
    public static Long getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

    /**
     * 获取今天结束时间
     */
    public static Long getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }


    public synchronized static Date StringLongToDate(String strDate) {
        try {
            return defaultSdf.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getBeginDay(Date date){
        Calendar currentDate = new GregorianCalendar();
        currentDate.setTime(date);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        return currentDate.getTime();
    }

    public static Date getEndDay(Date date){
        Calendar currentDate = new GregorianCalendar();
        currentDate.setTime(date);
        currentDate.set(Calendar.HOUR, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.MILLISECOND, 999);
        return currentDate.getTime();
    }

    public static String getStartShortStrByWeekNo(int year,int weekNo){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
    }

    public static String getEndShortStrByWeekNo(int year,int weekNo){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
    }

    public static Date getStartDateByWeekNo(int year,int weekNo){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return cal.getTime();
    }

    public static Date getEndDateByWeekNo(int year,int weekNo){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.getTime();
    }

    /**
     * 对比两个时间，第二个更晚的时候返回true，相同或者第一个时间大的时候返回false
     * @param d1
     * @param d2
     * @return
     */
    public static boolean d2IsLater(Date d1,Date d2){
        return d1.getTime() < d2.getTime();
    }
}
