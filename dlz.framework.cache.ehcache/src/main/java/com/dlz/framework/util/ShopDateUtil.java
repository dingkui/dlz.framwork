package com.dlz.framework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 日期相关的操作
 * @author Dawei
 *  
 */

public class ShopDateUtil {
	protected static final Log logger = LogFactory.getLog(ShopDateUtil.class);
	
	public static final long ONE_DAY=86400;//一天的秒数
	 /**
	   * 当天的开始时间
	   * @return
	   */
	  public static long startOfTodDay() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    Date date=calendar.getTime();
	    return date.getTime()/1000;
	  }
	  /**
	   * 当天的结束时间
	   * @return
	   */
	  public static long endOfTodDay() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    calendar.set(Calendar.MILLISECOND, 999);
	    Date date=calendar.getTime();
	    return date.getTime()/1000;
	  }
	  /**
	   * 昨天的开始时间
	   * @return
	   */
	  public static long startOfyesterday() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.add(Calendar.DATE, -1);
	    calendar.set(Calendar.MILLISECOND, 0);
	    Date date=calendar.getTime();
	    return date.getTime()/1000;
	  }
	  /**
	   * 昨天的结束时间
	   * @return
	   */
	  public static long endOfyesterday() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    calendar.set(Calendar.MILLISECOND, 999);
	    calendar.add(Calendar.DATE, -1);
	    Date date=calendar.getTime();
	    return date.getTime()/1000;
	  }
	  /**
	   * 某天的开始时间
	   * @param dayUntilNow 距今多少天以前
	   * @return 时间戳
	   */
	  public static long startOfSomeDay(int dayUntilNow){
		  Calendar calendar = Calendar.getInstance();
		  calendar.set(Calendar.HOUR_OF_DAY, 0);
		  calendar.set(Calendar.MINUTE, 0);
		  calendar.set(Calendar.SECOND, 0);
		  calendar.set(Calendar.MILLISECOND, 0);
		  calendar.add(Calendar.DATE, -dayUntilNow);
		  Date date=calendar.getTime();
		  return date.getTime()/1000;
	  }
	  
	 /**
	  *  某天的年月日
	  * @param dayUntilNow 距今多少天以前
	  * @return 年月日map  key为  year month day
	  */
	 public static Map<String,Object> getYearMonthAndDay(int dayUntilNow){
		 Map<String,Object> map=new HashMap<String,Object>();
		 Calendar calendar=Calendar.getInstance();
		  calendar.set(Calendar.HOUR_OF_DAY, 0);
		  calendar.set(Calendar.MINUTE, 0);
		  calendar.set(Calendar.SECOND, 0);
		  calendar.set(Calendar.MILLISECOND, 0);
		  calendar.add(Calendar.DATE, -dayUntilNow);
		  map.put("year", calendar.get(Calendar.YEAR));
		  map.put("month", calendar.get(Calendar.MONTH)+1);
		  map.put("day", calendar.get(Calendar.DAY_OF_MONTH));
		  return map;
	 }
	 
	 /**
	  * 取得某两天的间隔
	  * 
	  * @param from 开始时间
	  * @param to 结束时间
	  * @return
	  */
	 public static int diffDate(Long from,Long to){ 
		Date fDate = new Date(from*1000);
		Date tDate = new Date(to*1000);
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(tDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
	    //logger.debug(day1 + "," + day2);
		return day2 - day1;
	 }
	/**
	 * 将一个字符串转换成日期格式
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date toDate(String date, String pattern) {
		if((""+date).equals("")){
			return null;
		}
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date newDate = new Date();
		try {
			newDate = sdf.parse(date);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return newDate;
	}
	
	/**
	 * 把日期转换成字符串型
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toString(Date date, String pattern){
		if(date == null){
			return "";
		}
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		String dateString = "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			dateString = sdf.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateString;
	}
	
	public static String toString(Long time,String pattern){
		if(time>0){
			if(time.toString().length()==10){
				time = time*1000;
			}
			Date date = new Date(time);
			String str  = toString(date, pattern);
			return str;
		}
		return "";
	}

	
	
	/**
	 * 获取上个月的开始结束时间
	 * @return
	 */
	public static Long[] getLastMonth() {
		   // 取得系统当前时间
		   Calendar cal = Calendar.getInstance();
		   int year = cal.get(Calendar.YEAR);
		   int month = cal.get(Calendar.MONTH) + 1;
		   
		   // 取得系统当前时间所在月第一天时间对象
		   cal.set(Calendar.DAY_OF_MONTH, 1);
		   
		   // 日期减一,取得上月最后一天时间对象
		   cal.add(Calendar.DAY_OF_MONTH, -1);
		   
		   // 输出上月最后一天日期
		   int day = cal.get(Calendar.DAY_OF_MONTH);

		   String months = "";
		   String days = "";

		   if (month > 1) {
		    month--;
		   } else {
		    year--;
		    month = 12;
		   }
		   if (!(String.valueOf(month).length() > 1)) {
		    months = "0" + month;
		   } else {
		    months = String.valueOf(month);
		   }
		   if (!(String.valueOf(day).length() > 1)) {
		    days = "0" + day;
		   } else {
		    days = String.valueOf(day);
		   }
		   String firstDay = "" + year + "-" + months + "-01";
		   String lastDay = "" + year + "-" + months + "-" + days;

		   Long[] lastMonth = new Long[2];
		   lastMonth[0] =getDateline(firstDay);
		   lastMonth[1] = getDateline(lastDay);

		 //  //logger.debug(lastMonth[0] + "||" + lastMonth[1]);
		   return lastMonth;
		}
	
	/**
	 * 获取指定月份的开始和结算时间
	 * @param date
	 * @return
	 */
	public static Long[] getTargetDateStartAndEndTime(Date date){
		Long[] arr = new Long[2];
		Calendar cal = Calendar.getInstance();
		cal.setTime(toDate(toString(date, "yyyy-MM-dd"), "yyyy-MM-dd"));
		cal.set(Calendar.DAY_OF_MONTH, 1);//当前月第一天
		arr[0] = cal.getTimeInMillis()/1000;
		cal.add(Calendar.MONTH, 1);//下个月
		cal.set(Calendar.DAY_OF_MONTH, 1);//下月第一天
		arr[1] = cal.getTimeInMillis()/1000;
		return arr;
	}
	
	/**
	 * 增加天数
	 * @param dataStr
	 * @param format
	 * @param num
	 * @return
	 */
	public static String addDayToTargetDate(String dataStr,String format,Integer num){
		Date date = toDate(dataStr, format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, num);
		date = calendar.getTime();
		return toString(date, format);
	}
	
	/**
	 * 获取当月的开始结束时间
	 * @return
	 */
	public static Long[] getCurrentMonth() {
		   // 取得系统当前时间
		   Calendar cal = Calendar.getInstance();
		   int year = cal.get(Calendar.YEAR);
		   int month = cal.get(Calendar.MONTH)+1 ;
		   // 输出下月第一天日期
		   int notMonth = cal.get(Calendar.MONTH)+2 ;
		   // 取得系统当前时间所在月第一天时间对象
		   cal.set(Calendar.DAY_OF_MONTH, 1);
		   
		   // 日期减一,取得上月最后一天时间对象
		   cal.add(Calendar.DAY_OF_MONTH, -1);
		   
		  

		   String months = "";
		   String nextMonths = "";


		   if (!(String.valueOf(month).length() > 1)) {
		    months = "0" + month;
		   } else {
		    months = String.valueOf(month);
		   }
		   if (!(String.valueOf(notMonth).length() > 1)) {
			   nextMonths = "0" + notMonth;
		   } else {
			   nextMonths = String.valueOf(notMonth);
		   }
		   String firstDay = "" + year + "-" + months + "-01";
		   String lastDay=	""+year+"-"+nextMonths+"-01";
		   Long[] currentMonth = new Long[2]; 
		   currentMonth[0] =getDateline(firstDay);
		   currentMonth[1] = getDateline(lastDay);

		 //  //logger.debug(lastMonth[0] + "||" + lastMonth[1]);
		   return currentMonth;
		}
		
 
	public static long getDateline(){
		return System.currentTimeMillis()/1000;
	}
	public static long getDateline(String date){
		return (long)(toDate(date, "yyyy-MM-dd").getTime()/1000);
	}
	public static long getDateHaveHour(String date){
		return (long)(toDate(date, "yyyy-MM-dd HH").getTime()/1000);
	}
	public static long getDateline(String date,String pattern){
		return (long)(toDate(date, pattern).getTime()/1000);
	}
	
	
	public static void main(String[] args){
		
		long starttime=startOfSomeDay(30);
		long endtime=endOfyesterday();
		logger.debug(starttime);
		logger.debug(endtime);
		Map<String,Object> map=getYearMonthAndDay(0);
		logger.debug(map.get("year")+"年"+map.get("month")+"月"+map.get("day")+"日");
		Map<String,Object> map2=getYearMonthAndDay(1);
		logger.debug(map2.get("year")+"年"+map2.get("month")+"月"+map2.get("day")+"日");
		/*	long d= 1319990400 ;
					d=d*1000;
			int line =getDateline("2011-10-31");
		
			//logger.debug( line +   "--"+toString(new Date(d), "yyyy-MM-dd"));
			//logger.debug(d);*/
		logger.debug(diffDate(starttime, endtime) + "天");
//		int d1 =getDateline("2011-10-30");
//		int d2 =getDateline("2011-10-15");
//		
//		//logger.debug(d1);
//		//logger.debug(d2);
//		
//		int f = 15 *24*60*60;
//		
//		//logger.debug(d1-f);
		
		//logger.debug( new Date(1320205608000l));
		//logger.debug( toString( new Date(1320205608000l),"yyyy-MM-dd HH:mm:ss"));
		getTargetDateStartAndEndTime(new Date());
	}
}
