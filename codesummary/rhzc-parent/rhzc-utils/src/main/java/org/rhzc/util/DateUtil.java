package org.rhzc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public final static String STYLE_1 = "yyyy-MM-dd HH:mm:ss";

	public final static String STYLE_2 = "yyyy-MM-dd";

	public final static String STYLE_3 = "yyyyMMdd";

	public final static String STYLE_4 = "yyyyMMddhh";

	public final static String STYLE_5 = "yyyyMMddhhmm";

	public final static String STYLE_6 = "yyyy年MM月dd日HH时mm分ss秒";

	public final static String STYLE_7 = "yyyy年MM月dd日HH时mm分";

	public final static String STYLE_8 = "yyyy年MM月dd日";

	public final static String STYLE_9 = "hhmmss";

	public static String getCurrentTime(String style) {

		if(style == null || "".equals(style))
		{
			style = STYLE_1;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(style);
		
		String str = sdf.format(new Date());
		
		return str;
	}

	public static String formatDate(String time, String fmtStyle,
			String wantStyle) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(STYLE_1);
		Date date = sdf.parse(time);
		sdf = new SimpleDateFormat(STYLE_3);

		return sdf.format(date);
	}

	public static Date formatDate(String times, String style)
			throws ParseException {
		if (style == null || "".equals(style)) {
			style = STYLE_1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		return sdf.parse(times);
	}

	public static int getAgeByBirthDay(String birthDay) throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(STYLE_2);
		Date date = sdf.parse(birthDay);
		if (cal.before(date)) {
			return 0;
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(date);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		} else {
			return 0;
		}

		return age;
	}

	public static int compareDate(String DATE1, String DATE2) {
		int i = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				i = -1;
			} else if (dt1.getTime() < dt2.getTime()) {
				i = 0;
			} else if (dt1.getTime() == dt2.getTime()) {
				i = 1;
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return i;
	}

	public static Date lastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	public static String getMonthBegin() {
		Calendar localTime = Calendar.getInstance();
		String strY = null;
		int x = localTime.get(Calendar.YEAR);
		int y = localTime.get(Calendar.MONTH) + 1;
		strY = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strY + "-01";
	}

	public static String getMonthEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		int endday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-" + endday;
	}

	public static boolean isWeekOfSaturday(String date) throws ParseException {

		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date bdate = format1.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			return true;
		return false;
	}

	public static boolean isWeekOfSunday(String bDate) throws ParseException {

		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date bdate = format1.parse(bDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			return true;
		return false;
	}

	public static boolean leapYear(int year) {
		boolean leap;
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0)
					leap = true;
				else
					leap = false;
			} else
				leap = true;
		} else
			leap = false;
		return leap;
	}

	public static String getLastTime(String nowTime, int type, int increment,
			String style) throws ParseException {
		if (style == null || "".equals(style)) {
			style = STYLE_1;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(nowTime));
		if (type == 2)
			calendar.add(Calendar.MONTH, increment);
		else
			calendar.add(Calendar.DAY_OF_MONTH, increment);

		String sdate = sdf.format(calendar.getTime());
		calendar.setTime(sdf.parse(sdate));
		return sdate;
	}

	public static long getBetweenDays(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat(STYLE_2);
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Math.abs(quot);
	}

	public static int diffDays(String startDate, String endDate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(startDate));
		long startTime = cal.getTimeInMillis();
		cal.setTime(sdf.parse(endDate));
		long endTime = cal.getTimeInMillis();
		long between_days = (endTime - startTime) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static long getBetweenTimes(String date1, String date2, int type)
			throws ParseException {
		SimpleDateFormat s = new SimpleDateFormat(STYLE_1);
		long t1 = s.parse(date1).getTime();
		long t2 = s.parse(date2).getTime();
		long result = 0;
		switch (type) {
		// 秒
		case 1:
			result = (t2 - t1) / 1000;
			break;

		// 分
		case 2:
			result = (t2 - t1) / 1000 / 60;
			break;

		// 时
		case 3:
			result = (t2 - t1) / 1000 / 60 / 60;
			break;
		default:
			break;
		}
		return result;
	}

	public static String getDistanceTime(String str1, String str2)
			throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		one = df.parse(str1);
		two = df.parse(str2);
		long time1 = one.getTime();
		long time2 = two.getTime();
		if (time2 <= time1)
			return 0 + "天" + 0 + "小时" + 0 + "分";

		long diff = time2 - time1;
		day = diff / (24 * 60 * 60 * 1000);
		hour = (diff / (60 * 60 * 1000) - day * 24);
		min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
		return day + "天" + hour + "小时" + min + "分";
	}

	public static int PeriodOfTime() {
		Calendar calendar = Calendar.getInstance();
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int result = 0;
		// 早上
		if (hours >= 0 && hours <= 5) {
			// 凌晨
			result = 1;
		} else if (hours >= 6 && hours <= 10) {
			// 早上
			result = 2;
		} else if (hours >= 11 && hours <= 13) {
			// 中午
			result = 3;
		} else if (hours >= 14 && hours <= 18) {
			// 下午
			result = 4;
		} else if (hours >= 19 && hours <= 24) {
			// 晚上
			result = 5;
		}
		return result;
	}

	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	public static int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getCurrentDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DATE);
	}

	public static int getCurrentHour() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	// 获取当前日期的前一天
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

	// 获取前天的日期
	public static Date getPreDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -2);
		date = calendar.getTime();
		return date;
	}

	// 获取前几天日期
	public static Date getFourthteenDay(Date date, int i) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -i);
		date = calendar.getTime();

		return date;
	}

	// 获取最近一周时间
	public static Date getWeekDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -6);
		date = calendar.getTime();
		return date;
	}

	// 获取最近一月时间
	public static Date getMonthDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		date = calendar.getTime();
		return date;
	}

	public static String getDateString(Date date, String styles) {
		SimpleDateFormat format = new SimpleDateFormat(styles);

		return format.format(date);
	}

	// 获取本周的第一天
	public static String getWeekFirstDay() throws ParseException {
		Calendar cal = Calendar.getInstance();

		cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(DateUtil
				.getCurrentTime(DateUtil.STYLE_2)));

		int d = 0;
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			d = -6;
		} else {
			d = 2 - cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	// 获取本周的最后一天
	public static String getWeekLastDay() throws ParseException {
		Calendar cal = Calendar.getInstance();

		cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(DateUtil
				.getCurrentTime(DateUtil.STYLE_2)));

		int d = 0;
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			d = -6;
		} else {
			d = 2 - cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);

		cal.add(Calendar.DAY_OF_WEEK, 6);

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	// 获取本月的第一天
	public static String getMonthFirstDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();

		c.add(Calendar.MONTH, 0);

		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天

		String first = format.format(c.getTime());

		return first;
	}

	// 获取本月的最后一天
	public static String getMonthLastDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar ca = Calendar.getInstance();

		ca.set(Calendar.DAY_OF_MONTH,
				ca.getActualMaximum(Calendar.DAY_OF_MONTH));

		String last = format.format(ca.getTime());

		return last;
	}

	// 获取前月的第一天
	public static String getPreMonthFirstDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal_1 = Calendar.getInstance();// 获取当前日期

		cal_1.add(Calendar.MONTH, -1);

		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天

		String firstDay = format.format(cal_1.getTime());

		return firstDay;
	}

	// 获取前月的最后一天
	public static String getPreMonthLastDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 获取前月的最后一天
		Calendar cale = Calendar.getInstance();

		cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天

		String lastDay = format.format(cale.getTime());

		return lastDay;
	}

	// System.out.println("获取上周一日期:" + tt.getPreviousWeekday());
	// System.out.println("获取上周日日期:" + tt.getPreviousWeekSunday());

	private static int weeks = 0;

	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();

		int dayOfWeek = cd.get(7) - 1;

		if (dayOfWeek == 1) {
			return 0;
		}

		return (1 - dayOfWeek);
	}

	
	public static String getPreviousWeekSunday() {
		weeks = 0;

		weeks -= 1;

		int mondayPlus = getMondayPlus();

		GregorianCalendar currentDate = new GregorianCalendar();

		currentDate.add(5, mondayPlus + weeks);

		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);

		return preMonday;
	}

	public static String getPreviousWeekday() {
		weeks -= 1;

		int mondayPlus = getMondayPlus();

		GregorianCalendar currentDate = new GregorianCalendar();

		currentDate.add(5, mondayPlus + 7 * weeks);

		Date monday = currentDate.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String preMonday = sdf.format(monday);

		return preMonday;
	}
	
//	public static void main(String[] args) {
//		

//		 System.out.println("获取上周一日期:" + getPreviousWeekday());
//		 System.out.println("获取上周日日期:" + getPreviousWeekSunday());
//		
//	}
}