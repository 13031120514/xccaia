//package com.xc.util;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.*;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;
//import java.time.temporal.TemporalAdjusters;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//import java.util.stream.Stream;
//
//public class DateUtils {
//
//    private static Logger log = LoggerFactory.getLogger(DateUtils.class);
//    public static final String YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
//
//    public static final String YYYYMMDD_HHMMSS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
//
//    public static final String YYYY_MM_DD = "yyyy-MM-dd";
//
//    public static final String YYYYMMDD = "yyyyMMdd";
//
//    public static final String yyyyMM = "yyyyMM";
//
//    public static final String yyyy_MM = "yyyy,MM";
//
//    public static final String YYYY_MM = "yyyy-MM";
//
//    public static final String yyyy_MM_dd = "yyyy,MM,dd";
//    public static final String comma = ",";
//    public static final String commaAndDay = ",01";
//
//    private static final ConcurrentMap<String, DateTimeFormatter> FORMATTER_CACHE = new ConcurrentHashMap<>();
//
//    private static final int PATTERN_CACHE_SIZE = 500;
//
//    private static final String FIRST_TIME = " 00:00:00";
//    private static final String LAST_TIME = " 23:59:59";
//
//    public static long MIN = 60 * 1000;
//    public static long HOUR = 60 * MIN;
//    public static long DAY = 24 * HOUR;
//
//    /**
//     * Date转换为格式化时间
//     *
//     * @param date    date
//     * @param pattern 格式
//     */
//    public static String format(Date date, String pattern) {
//        return format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()), pattern);
//    }
//
//    /**
//     * localDateTime转换为格式化时间
//     *
//     * @param localDateTime localDateTime
//     * @param pattern       格式
//     */
//    public static String format(LocalDateTime localDateTime, String pattern) {
//        DateTimeFormatter formatter = createCacheFormatter(pattern);
//        return localDateTime.format(formatter);
//    }
//
//    /**
//     * 格式化字符串转为Date
//     *
//     * @param time    格式化时间
//     * @param pattern 格式
//     */
//    public static Date parseDate(String time, String pattern) {
//        Date date = null;
//        try {
//            date = Date.from(parseLocalDateTime(time, pattern).atZone(ZoneId.systemDefault()).toInstant());
//        } catch (Exception e) {
//            log.error("parseDateError,time:{},pattern:{}", time, pattern);
//        }
//        return date;
//    }
//
//
//    /**
//     * 格式化字符串转为Date
//     *
//     * @param time 格式化时间
//     */
//    public static Date strToDateTime(String time) {
//        Date date = null;
//        if (null == time || "".equalsIgnoreCase(time)) {
//            return null;
//        }
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            date = sf.parse(time);
//        } catch (ParseException e) {
//            log.error(e.getMessage());
//        }
//        return date;
//    }
//
//
//    public static Date strToDate(String time) {
//        Date date = null;
//        if (null == time || "".equalsIgnoreCase(time)) {
//            return null;
//        }
//        SimpleDateFormat sf = new SimpleDateFormat(YYYY_MM_DD);
//        try {
//            date = sf.parse(time);
//        } catch (ParseException e) {
//            log.error(e.getMessage());
//        }
//        return date;
//    }
//
//    /**
//     * 标准时间字符串转为Date 字符串中的"T"替换为" "，"Z"替换为"" 转换为北京时间
//     *
//     * @param time    格式化时间
//     * @param pattern 格式
//     * @return
//     */
//    public static Date parseDateByStandard(String time, String pattern) {
//        if (time.startsWith("0001-")) {
//            log.info("日期格式异常，{}", time);
//            return null;
//        }
//        time = time.replace("T", " ").replace("Z", "");
//        if (StringUtils.isNotBlank(time)){
//            if (time.indexOf(".")!=-1){
//                time=time.substring(0,time.indexOf("."));
//            }
//        }
//        Date date = parseDate(time, pattern);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
//        return calendar.getTime();
//    }
//
//    /**
//     * 格式化字符串转为LocalDateTime
//     *
//     * @param time    格式化时间
//     * @param pattern 格式
//     */
//    public static LocalDateTime parseLocalDateTime(String time, String pattern) {
//        DateTimeFormatter formatter = createCacheFormatter(pattern);
//        return LocalDateTime.parse(time, formatter);
//    }
//
//    /**
//     * 在缓存中创建DateTimeFormatter
//     *
//     * @param pattern 格式
//     */
//    private static DateTimeFormatter createCacheFormatter(String pattern) {
//        if (pattern == null || pattern.length() == 0) {
//            throw new IllegalArgumentException("Invalid pattern specification");
//        }
//        DateTimeFormatter formatter = FORMATTER_CACHE.get(pattern);
//        if (formatter == null) {
//            if (FORMATTER_CACHE.size() < PATTERN_CACHE_SIZE) {
//                formatter = DateTimeFormatter.ofPattern(pattern);
//                DateTimeFormatter oldFormatter = FORMATTER_CACHE.putIfAbsent(pattern, formatter);
//                if (oldFormatter != null) {
//                    formatter = oldFormatter;
//                }
//            }
//        }
//
//        return formatter;
//    }
//
//    /**
//     * getLastMonth
//     *
//     * @return
//     */
//    public static String getLastMonth() {
//        LocalDate today = LocalDate.now();
//        today = today.minusMonths(1);
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(yyyy_MM);
//        return formatters.format(today);
//    }
//
//    public static Long timeToLong(String time) {
//        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(YYYYMMDD_HHMMSS);
//        LocalDateTime parse = LocalDateTime.parse(time, ftf);
//        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//    }
//
//    /**
//     * 时间计算
//     */
//    public static String dateCalculation(Date date, int days) {
//        if (null == date) {
//            return null;
//        }
//        String strDate = null;
//        try {
//            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date newDate = new Date(date.getTime() + days * 60 * 60 * 1000L);
//            strDate = sf.format(newDate);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return strDate;
//    }
//
//    /**
//     * DateTimeToString
//     */
//    public static String dateTimeToString(Date date) {
//        if (null == date) {
//            return null;
//        }
//        String strDate = null;
//        try {
//            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            strDate = sf.format(date);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return strDate;
//    }
//
//    /**
//     * 再原有时间上减去天数
//     *
//     * @param date
//     * @param day
//     * @return
//     */
//    public static Date reduceDay(Date date, int day) {
//        if(null == date){
//            return null;
//        }
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - day);
//        return calendar.getTime();
//    }
//
//    /**
//     * 再原有时间上减去小时
//     *
//     * @param date
//     * @param hour
//     * @return
//     */
//    public static Date reduceHour(Date date, int hour) {
//        if(null == date){
//            return null;
//        }
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - hour);
//        return calendar.getTime();
//    }
//
//
//    /**
//     * 再原有时间上减去分钟
//     * @param date
//     * @param min
//     * @return
//     */
//    public static Date reduceMin(Date date, int min) {
//        if(null == date){
//            return null;
//        }
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - min);
//        return calendar.getTime();
//    }
//
//
//    /**
//     * LongToString
//     */
//    public static String LongToString(Long ldate) {
//        String sDate = null;
//        if (null == ldate) {
//            return sDate;
//        }
//        try {
//            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = new Date(ldate);
//            sDate = sf.format(date);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return sDate;
//    }
//
//    public static String nowDateTime() {
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(YYYYMMDD_HHMMSS);
//        return LocalDateTime.now().format(formatters);
//    }
//
//
//    public static String randomDateTime() {
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(YYYYMMDD_HHMMSS);
//        return randomLocalDateTime(-190, -10).format(formatters);
//    }
//
//    public static String getLastMonth(String year, String month) {
//        return getLastMonth(year + comma + month + commaAndDay);
//    }
//
//    public static String getLastMonth(String date) {
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(yyyy_MM_dd);
//        LocalDate today = LocalDate.parse(date, formatters);
//        today = today.minusMonths(1);
//        return formatters.format(today);
//    }
//
//    /**
//     * getLastMonth
//     *
//     * @return
//     */
//    public static String getLastMonth(int month) {
//        LocalDate today = LocalDate.now();
//        today = today.minusMonths(month);
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(yyyy_MM);
//        return formatters.format(today);
//    }
//
//
//    public static String getLastYears() {
//        LocalDate today = LocalDate.now();
//        today = today.minusYears(1);
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy");
//        return formatters.format(today);
//    }
//
//    public static String getLastYears(int i) {
//        LocalDate today = LocalDate.now();
//        today = today.minusYears(i);
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy");
//        return formatters.format(today);
//    }
//
//    public static String getLastYears(String year) {
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(yyyy_MM_dd);
//        LocalDate today = LocalDate.parse(year + commaAndDay + commaAndDay, formatters);
//        today = today.minusYears(1);
//        return formatters.format(today).split(comma)[0];
//    }
//
//
//    public static String firstDayOfMonth() {
//        return LocalDate.now().minusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
//    }
//
//    public static String lastDayOfMonth() {
//        return LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
//    }
//
//    public static String firstDayOfMonth(int i) {
//        return LocalDate.now().minusMonths(i).with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
//    }
//
//    public static String lastDayOfMonth(int i) {
//        return LocalDate.now().minusMonths(i).with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
//    }
//
//
//    public static String firstDayOfMonth(LocalDate localDate) {
//        return localDate.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
//    }
//
//    public static String lastDayOfMonth(LocalDate localDate) {
//        return localDate.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
//    }
//
//
//    public static String getLastYearsByNumber(int year) {
//        LocalDate today = LocalDate.now();
//        today = today.minusYears(year);
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy");
//        return formatters.format(today);
//    }
//
//
//    public static String getLastYearsAll() {
//        StringBuilder lastYearsAll = new StringBuilder("");
//        LocalDate today = LocalDate.now();
//        today = today.minusYears(1);
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy");
//        String year = formatters.format(today);
//        for (int i = 1; i < 13; i++) {
//            lastYearsAll.append(year).append(",").append(i).append(";");
//        }
//        return lastYearsAll.toString();
//    }
//
//    /**
//     * TODO 未完成 取两个日期之间所有月份
//     *
//     * @param start
//     * @param end
//     * @return
//     */
//    public static List<String> getBetweenDate(String start, String end) {
//        List<String> list = new ArrayList<>();
//        LocalDate startDate = null;
//        LocalDate endDate = null;
//        try {
//            startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM"));
//            endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM"));
//        } catch (Exception e) {
//            throw new RuntimeException("日期格式不正确。（日期示例：2019-12-26）");
//        }
//
//        if (ObjectUtils.equals(start, end)) {
//            list.add(start);
//            return list;
//        }
//
//        long distance = ChronoUnit.DAYS.between(startDate, endDate);
//        if (distance < 1) {
//            return list;
//        }
//        Stream.iterate(startDate, d -> {
//            return d.plusMonths(1);
//        }).limit(distance + 1).forEach(f -> {
//            list.add(f.toString());
//        });
//        return list;
//    }
//
//    public static Date getDate(Long time) {
//        return null == time ? null : new Date(time);
//    }
//
//    public static String firstDayTimeOfMonth(int i) {
//        return firstDayOfMonth(i) + FIRST_TIME;
//    }
//
//    public static String lastDayTimeOfMonth(int i) {
//        return lastDayOfMonth(i) + LAST_TIME;
//    }
//
//    public static String firstDayTimeOfMonth() {
//        return firstDayOfMonth(1) + FIRST_TIME;
//    }
//
//    public static String lastDayTimeOfMonth() {
//        return lastDayOfMonth(1) + LAST_TIME;
//    }
//
//    public static String firstDayTimeOfMonth(String year, String month) {
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(yyyy_MM_dd);
//        LocalDate today = LocalDate.parse(year + comma + month + commaAndDay, formatters);
//        return today.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern(YYYY_MM_DD)) + FIRST_TIME;
//    }
//
//
//    public static String lastDayTimeOfMonth(String year, String month) {
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(yyyy_MM_dd);
//        LocalDate today = LocalDate.parse(year + comma + month + commaAndDay, formatters);
//        return today.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern(YYYY_MM_DD)) + LAST_TIME;
//    }
//
//
//    /**
//     * 取范围日期的随机日期时间,不含边界
//     *
//     * @param startDay
//     * @param endDay
//     * @return
//     */
//    public static LocalDateTime randomLocalDateTime(int startDay, int endDay) {
//
//        int plusMinus = 1;
//        if (startDay < 0 && endDay > 0) {
//            plusMinus = Math.random() > 0.5 ? 1 : -1;
//            if (plusMinus > 0) {
//                startDay = 0;
//            } else {
//                endDay = Math.abs(startDay);
//                startDay = 0;
//            }
//        } else if (startDay < 0 && endDay < 0) {
//            plusMinus = -1;
//
//            //两个数交换
//            startDay = startDay + endDay;
//            endDay = startDay - endDay;
//            startDay = startDay - endDay;
//
//            //取绝对值
//            startDay = Math.abs(startDay);
//            endDay = Math.abs(endDay);
//
//        }
//
//        LocalDate day = LocalDate.now().plusDays(plusMinus * RandomUtils.nextInt(startDay, endDay));
//        int hour = RandomUtils.nextInt(1, 24);
//        int minute = RandomUtils.nextInt(0, 60);
//        int second = RandomUtils.nextInt(0, 60);
//        LocalTime time = LocalTime.of(hour, minute, second);
//        return LocalDateTime.of(day, time);
//    }
//
//    /**
//     * 取范围日期的随机日期时间,不含边界
//     *
//     * @param startDay
//     * @param endDay
//     * @return
//     */
//    public static Date randomDateTime(int startDay, int endDay) {
//        LocalDateTime ldt = randomLocalDateTime(startDay, endDay);
//        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
//        return Date.from(zdt.toInstant());
//    }
//
//    /**
//     * 计算两个时间差
//     *
//     * @param startTime
//     * @param endTime
//     * @return
//     */
//    public static long dateSubtraction(String startTime, String endTime) {
//      if (null == startTime || null == endTime) {
//        return 0;
//      }
//      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//      SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//      SimpleDateFormat df3 = new SimpleDateFormat(YYYYMMDD_HHMMSS);
//      try {
//        Date date1 = null;
//        if (!startTime.contains("T")) {
//          date1 = df3.parse(startTime);
//        } else if (startTime.contains(".")) {
//          date1 = df.parse(startTime);
//        } else {
//          date1 = df2.parse(startTime);
//        }
//        Date date2 = null;
//        if (!endTime.contains("T")) {
//          date2 = df3.parse(endTime);
//        } else if (endTime.contains(".")) {
//          date2 = df.parse(endTime);
//        } else {
//          date2 = df2.parse(endTime);
//        }
//        return date2.getTime() - date1.getTime();
//      } catch (ParseException e) {
//        log.error("计算两个时间差异常{}", e.getMessage());
//        e.printStackTrace();
//      }
//      return 0;
//    }
//
//    /**
//     * 计算两个时间差
//     *
//     * @param startTime
//     * @param endTime
//     * @return
//     */
//    public static long dateSubtraction(Date startTime, Date endTime) {
//        return endTime.getTime() - startTime.getTime();
//    }
//
//    /**
//     * 给时间戳后面补零
//     *
//     * @param timeStamp
//     * @return
//     */
//    public static long fillZero(long timeStamp, int totalLength) {
//        String time = String.valueOf(timeStamp);
//        int length = totalLength - time.length();
//        if (length > 0) {
//            timeStamp = timeStamp * (long) Math.pow(10, length);
//        }
//        return timeStamp;
//    }
//
//    public static String caleTotalTime(long time) {
//        StringBuffer desc = new StringBuffer();
//        boolean dayFlag = false;
//        long d = time / DAY;
//        if (d > 0) {
//            desc.append(d).append("天");
//            time = time - d * DAY;
//            dayFlag = true;
//        }
//        long h = time / HOUR;
//        if (h > 0) {
//            desc.append(h).append("小时");
//            time = time - h * HOUR;
//        } else {
//            if (dayFlag) {
//                desc.setLength(0);
//                desc.append("24小时");
//            }
//        }
//        long m = time / MIN;
//        if (m > 0) {
//            desc.append(m).append("分");
//        }
//        return desc.toString();
//    }
//
//    /**
//     * 返回昨天（yyyy-MM-dd）
//     * @return
//     */
//    public static String getYesterday(){
//        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
//        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY,-24);
//        String yesterdayDate = dateFormat.format(calendar.getTime());
//
//        return yesterdayDate;
//    }
//}