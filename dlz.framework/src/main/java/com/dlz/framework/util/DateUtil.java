package com.dlz.framework.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.dlz.framework.logger.MyLogger;

public class DateUtil {
	private static MyLogger logger = MyLogger.getLogger(DateUtil.class);
	private static final long ONE_MINUTE = 60000L;
	private static final long ONE_HOUR = 3600000L;
	private static final long ONE_DAY = 86400000L;
	private static final long ONE_WEEK = 604800000L;
	private static final String ONE_SECOND_AGO = "秒前";
	private static final String ONE_MINUTE_AGO = "分钟前";
	private static final String ONE_HOUR_AGO = "小时前";
	private static final String ONE_DAY_AGO = "天前";
	private static final String ONE_MONTH_AGO = "月前";
	private static final String ONE_YEAR_AGO = "年前";
	private static final DateFormat DATEFORMAT_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 日期时间转换
	 * 
	 * @param df
	 * @param current
	 * @param hCreatedate
	 * @return
	 * @throws ParseException
	 */
	public static String transformTime(String hCreatedate) throws ParseException {
		if("null".equals(hCreatedate)){
			return "";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DATEFORMAT_TIME.parse(hCreatedate));

		long time = Calendar.getInstance().getTimeInMillis() - calendar.getTimeInMillis();
		long hour = time / 1000 / 3600;

		int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
		int currentMinute = calendar.get(Calendar.MINUTE);
		int calendarHour = calendar.get(Calendar.HOUR_OF_DAY);
		int calendarMinute = calendar.get(Calendar.MINUTE);

		// 过去
		if (hour - currentHour > 24 * 2) {
			hCreatedate = hCreatedate.substring(5, 10);
		}
		// 前天
		else if (hour - currentHour > 24) {
			hCreatedate = "前天" + hCreatedate.substring(11, 16);
		}
		// 昨天
		else if (hour - currentHour > 0) {
			hCreatedate = "昨天" + hCreatedate.substring(11, 16);
		}
		// 小时
		else if (currentHour - calendarHour > 1) {
			hCreatedate = (currentHour - calendarHour) + "小时前";
		}
		// 分钟
		else if (currentMinute - calendarMinute > 15) {
			hCreatedate = currentMinute - calendarMinute + "分钟前";
		}
		// 刚刚
		else {
			hCreatedate = "刚刚";
		}
		return hCreatedate;
	}
	
	
	
	// add by eleven
	public static String convertToNeerTimeOrDate(String d) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date defineDate = format.parse(d);
			Date curDate = new Date();
			long l = curDate.getTime() - defineDate.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
//			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
			
			String defineTime = "";
			
			
			
			
			if(day > 31){
				format = new SimpleDateFormat("MM月dd日 HH:mm");
				defineTime = format.format(defineDate);
			}else if (day < 31 && day != 0) {
				defineTime = day + "天前";
			} else if (day < 1  && hour != 0) {
				defineTime = hour + "小时前";
			} else if (hour < 1 && min != 0) {
				defineTime = min + "分钟前";
			} else if (min < 1) {
				defineTime = "1分钟前";
			} else{
				defineTime = d;
			}
			
			return defineTime;// + "  " + d;
			
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
			return d;
		}
//		return format.parse(s);
//		return d;
	}
	
	/**
	 * 传入 "yyyy-MM-dd HH:mm:ss"格式的字符串，只返回日期部分
	 * @param dayStr
	 * @return
	 */
	static public String getDayOnly(String dayStr){
		return dayStr.substring(0,10);
	}

	/**
	 * getDateStr get a string with format YYYY-MM-DD from a Date object
	 * 
	 * @param date
	 *            date
	 * @return String
	 */
	static public String getDateStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	static public String getYear(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return format.format(date);
	}

	static public String getDateStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(date);
	}

	static public String getDateStrCompact(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		return str;
	}

	/**
	 * getDateStr get a string with format YYYY-MM-DD HH:mm:ss from a Date
	 * object
	 * 
	 * @param date
	 *            date
	 * @return String
	 */
	static public String getDateTimeStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	static public String getNowTimeStr() {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return getDateTimeStr(new Date());
	}

	static public String getDateTimeStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.format(date);
	}

	public synchronized static String getCurDateStr(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}
	
	public static String getDateStr(Date date ,String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		if(date == null){
			return "";
		}
		return format.format(date);
	}
	public static Date  parseData(String dataVal , String formatVal) {
		SimpleDateFormat  formatter = new SimpleDateFormat (formatVal);
		ParsePosition pos = new ParsePosition(0);
		java.util.Date cDate= formatter.parse(dataVal,pos);
		return cDate ; 
	}
	/**
	 * @author penghao
	 * 根据出生日期计算年龄
	 */
	@SuppressWarnings("deprecation")
	public static int getAge(Date age){
		if(age==null){
			return 0;
		}
		Date date=new Date();
		return date.getYear()-age.getYear();
	}

	/**
	 * Parses text in 'YYYY-MM-DD' format to produce a date.
	 * 
	 * @param s
	 *            the text
	 * @return Date
	 * @throws ParseException
	 */
	static public Date parseDate(String s)  {
		if(s == null || "".equals(s)){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(s);
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
		}
		return null ;
	}
	
	/**
	 * Parses from 'MM/dd/yyyy' to 'yyyy/MM/dd'
	 * @param s
	 * @return
	 */
	static public Date parseDateA(String s)  {
		if(s == null || "".equals(s)){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat  new_format= new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		try {
			Date date = sdf.parse(s);
			String local_date = new_format.format(date);
			date = new_format.parse(local_date); 
			local_date = format.format(date);
			return format.parse(local_date);
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
		} 
		return null ;
	}

	static public Date parseDateC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.parse(s);
	}

	/**
	 * Parses text in 'YYYY-MM-DD' format to produce a date.
	 * 
	 * @param s
	 *            the text
	 * @return Date
	 * @throws ParseException
	 */
	static public Date parseDateTime(String s){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(s);
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
		}
		return null ;
	}

	static public Date parseDateTimeC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.parse(s);
	}

	/**
	 * Parses text in 'HH:mm:ss' format to produce a time.
	 * 
	 * @param s
	 *            the text
	 * @return Date
	 * @throws ParseException
	 */
	static public Date parseTime(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.parse(s);
	}

	static public Date parseTimeC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH时mm分ss秒");
		return format.parse(s);
	}

	static public int yearOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(0, 4));
	}

	static public int monthOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(5, 7));
	}

	static public int dayOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(8, 10));
	}

//	static public String getDateTimeStr(java.sql.Date date, double time) {
//		int year = date.getYear() + 1900;
//		int month = date.getMonth() + 1;
//		int day = date.getDate();
//		String dateStr = year + "-" + month + "-" + day;
//		Double d = new Double(time);
//		String timeStr = String.valueOf(d.intValue()) + ":00:00";
//
//		return dateStr + " " + timeStr;
//	}

	/**
	 * Get the total month from two date.
	 * 
	 * @param sd
	 *            the start date
	 * @param ed
	 *            the end date
	 * @return int month form the start to end date
	 * @throws ParseException
	 */
//	static public int diffDateM(Date sd, Date ed) throws ParseException {
//		return (ed.getYear() - sd.getYear()) * 12 + ed.getMonth()
//				- sd.getMonth() + 1;
//	}

	static public int diffDateD(Date sd, Date ed) throws ParseException {
		return Math.round((ed.getTime() - sd.getTime()) / 86400000) + 1;
	}
	static public boolean compare(Date sd, Date ed){
		return sd.getTime() > ed.getTime();
	}
	static public boolean between(Date date, Date begin, Date end){
		return date.getTime() > begin.getTime() && date.getTime() < end.getTime();
	}

	static public int diffDateM(int sym, int eym) throws ParseException {
		return (Math.round(eym / 100) - Math.round(sym / 100)) * 12
				+ (eym % 100 - sym % 100) + 1;
	}

	static public java.sql.Date getNextMonthFirstDate(java.sql.Date date)
			throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.set(Calendar.DATE, 1);
		return new java.sql.Date(scalendar.getTime().getTime());
	}

	static public java.sql.Date getFrontDateByDayCount(java.sql.Date date,
			int dayCount) throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.DATE, -dayCount);
		return new java.sql.Date(scalendar.getTime().getTime());
	}

	/**
	 * Get first day of the month.
	 * 
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @return Date first day of the month.
	 * @throws ParseException
	 */
	static public Date getFirstDay(String year, String month)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	static public Date getFirstDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	static public Date getLastDay(String year, String month)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.add(Calendar.DATE, -1);
		date = scalendar.getTime();
		return date;
	}

	static public Date getLastDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.add(Calendar.DATE, -1);
		date = scalendar.getTime();
		return date;
	}

	/**
	 * getToday get todat string with format YYYY-MM-DD from a Date object
	 * 
	 * @param date
	 *            date
	 * @return String
	 */

	static public String getTodayStr() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		return getDateStr(calendar.getTime());
	}

	static public Date getToday() throws ParseException {
		return new Date(System.currentTimeMillis());
	}

	static public String getTodayAndTime() {
		return new Timestamp(System.currentTimeMillis()).toString();
	}

	static public String getTodayC() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		return getDateStrC(calendar.getTime());
	}


	// 获取相隔天数
	static public long getDistinceDay(String beforedate, String afterdate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		long dayCount = 0;
		try {
			java.util.Date d1 = d.parse(beforedate);
			java.util.Date d2 = d.parse(afterdate);

			dayCount = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);

		} catch (ParseException e) {
			logger.error("Date parse error!");
			// throw e;
		}
		return dayCount;
	}

	// 获取相隔天数
	static public long getDistinceDay(Date beforedate, Date afterdate) {
		long dayCount = 0;

			dayCount = (afterdate.getTime() - beforedate.getTime())
					/ (24 * 60 * 60 * 1000);
		return dayCount;
	}

	static public long getDistinceDay(java.sql.Date beforedate,
			java.sql.Date afterdate) throws ParseException {
		long dayCount = 0;

			dayCount = (afterdate.getTime() - beforedate.getTime())
					/ (24 * 60 * 60 * 1000);
		return dayCount;
	}

	// 获取相隔天数
	static public long getDistinceDay(String beforedate) throws ParseException {
		return getDistinceDay(beforedate, getTodayStr());
	}

	// 获取相隔时间数
	static public long getDistinceTime(String beforeDateTime,
			String afterDateTime) throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long timeCount = 0;
			java.util.Date d1 = d.parse(beforeDateTime);
			java.util.Date d2 = d.parse(afterDateTime);

			timeCount = (d2.getTime() - d1.getTime()) / (60 * 60 * 1000);
		return timeCount;
	}

	public static String getDistinceDescribe(String beforedate) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		try {
			Date now = new Date();
			Date before = d.parse(beforedate);
			long timeDistance = now.getTime() - before.getTime();
			long dayInstance = timeDistance / DATE_DISTANCE;
			if (dayInstance > 0) {
				return getDescribe(0,dayInstance);
			} else {
				long hours = timeDistance / HOUR_DISTANCE;
				if (hours > 0) {
					return getDescribe(1,hours);
				}else{
					long minites = timeDistance / MINITE_DISTANCE;
					return getDescribe(2,minites);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}
		return "";
	}
	public static String getDistinceDesc(Date before) {
		
		try {
			Date now = new Date();
			long timeDistance = now.getTime() - before.getTime();
			long dayInstance = timeDistance / DATE_DISTANCE;
			if (dayInstance > 0) {
				return getDescribe(0,dayInstance);
			} else {
				long hours = timeDistance / HOUR_DISTANCE;
				if (hours > 0) {
					return getDescribe(1,hours);
				}else{
					long minites = timeDistance / MINITE_DISTANCE;
					return getDescribe(2,minites);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}
		return "";
	}
	
	public static String getDistinceDescForStock(Date before) {
		try {
			Date now = new Date();
			long timeDistance = now.getTime() - before.getTime();
			long dayInstance = timeDistance / DATE_DISTANCE;
			if (dayInstance > 0) {
				return getDescribeForStock(0,dayInstance);
			} else {
				return "1";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(),e);
		}
		return "";
	}


	private static String getDescribe(int type, long distance) {
		switch (type) {
		case 0:
			return distance + "天前";
		case 1:
			return distance + "小时前";
		case 2:
			return distance + "分钟前";
		default:
			break;
		}
		return "";
	}
	
	private static String getDescribeForStock(int type, long distance) {
		return distance + "";
	}

	public static SimpleDateFormat SIMPLE_DAT_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	public static long MINITE_DISTANCE = (60 * 1000);

	public static long HOUR_DISTANCE = (60 * 1000) * 60;

	public static long DATE_DISTANCE = HOUR_DISTANCE * 24;
	
	
	/**
	 * 获取与当前时间的时间差 例如:0天23小时45分
	 * @param origin
	 * @return
	 */
	public static String getDiff2NowText(Date origin, String type) {
		Calendar now = Calendar.getInstance();
		long nowInMillis = now.getTimeInMillis();
		long originInMillis = origin.getTime();

		long diffInMinllis = 0l;

		if ("1".equals(type)) {
			diffInMinllis = (originInMillis - nowInMillis) / 1000;// 换算成秒
		} else {
			diffInMinllis = (nowInMillis - originInMillis) / 1000;// 换算成秒
		}
		long dayDiff = diffInMinllis / (24 * 3600);
		long hourDiff = diffInMinllis % (24 * 3600) / 3600;
		long minuteDiff = diffInMinllis % 3600 / 60;
		// long secondDiff = diffInMinllis % 60 / 1;
		return dayDiff + "天" + hourDiff + "小时" + minuteDiff + "分";
	}
	
	/**
	 * @param origin 目标日志
	 * @param filed Calendar.DAY_OF_YEAR
	 * @param amount  值
	 * @return 
	 */
	public static Date add(Date origin, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(origin);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 增加月
	 * @param date
	 * @param i 要增加的月数
	 * @return
	 */
	public static Date addMonths(Date date, int i) {
		 Calendar calendar = Calendar.getInstance();//日历对象
	   calendar.setTime(date);//设置当前日期
	   calendar.add(Calendar.MONTH, i);//月份加1
	   return calendar.getTime();
	}
	/**
	 * 增加天
	 * @param date
	 * @param i 要增加的天数
	 * @return
	 */
	public static Date addDays(Date date, int i) {
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.setTime(date);//设置当前日期
		calendar.add(Calendar.DATE, i);
		return calendar.getTime();
	}
	
	/**
	 * @author penghao
	 * 计算时间差
	 * 
	 */
	public static String formatTime(Date date) {
		long delta = new Date().getTime() - date.getTime();
		if (delta < 1L * ONE_MINUTE) {
			long seconds = toSeconds(delta);
			return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
		}
		if (delta < 45L * ONE_MINUTE) {
			long minutes = toMinutes(delta);
			return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
		}
		if (delta < 24L * ONE_HOUR) {
			long hours = toHours(delta);
			return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
		}
		if (delta < 48L * ONE_HOUR) {
			return "昨天";
		}
		if (delta < 30L * ONE_DAY) {
			long days = toDays(delta);
			return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
		}
		if (delta < 12L * 4L * ONE_WEEK) {
			long months = toMonths(delta);
			return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
		} else {
			long years = toYears(delta);
			return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
		}
	}

	private static long toSeconds(long date) {
		return date / 1000L;
	}

	private static long toMinutes(long date) {
		return toSeconds(date) / 60L;
	}

	private static long toHours(long date) {
		return toMinutes(date) / 60L;
	}

	private static long toDays(long date) {
		return toHours(date) / 24L;
	}

	private static long toMonths(long date) {
		return toDays(date) / 30L;
	}

	private static long toYears(long date) {
		return toMonths(date) / 365L;
	}

	public static long getDateline(){
		return System.currentTimeMillis()/1000;
	}
	
}