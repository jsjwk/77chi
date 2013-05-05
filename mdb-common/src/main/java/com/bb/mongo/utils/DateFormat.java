package com.bb.mongo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateFormat {
	
	private static String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	
	public static String format(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern); 
		return dateFormat.format(date);
	}
	
	public static String format(Date date,String pattern){
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern); 
		return dateFormat.format(date);
	}
	
	public static Date parse(String dateString){
		
		if(dateString == null || "".equals(dateString)){
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern); 
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date parse(String dateString,String pattern){
		
		if(dateString == null || "".equals(dateString)){
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern); 
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 字符串格式日期转成long
	 * @param dateStr
	 * @param param
	 * @return
	 * @throws ParseException
	 */
	public static Long stringToLong(String dateStr, String param){
		if(StringUtils.isEmpty(StringUtils.trim(dateStr))){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(param);
		Date date;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return Long.valueOf(date.getTime());
	}
	
	/**
	 * 日期转long
	 * @param date
	 * @param param
	 * @return
	 */
	public static Long dateToLong(Date date, String param){
		if(null == date){
			return null;
		}
		return stringToLong(dateToStr(date, param), param);
	}
	
	/**
	 * 时间转字符串
	 * @param time
	 * @param param
	 * @return
	 */
	public static String longToStr(Long time, String param){
		if(null == time){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(param);
		return sdf.format(new Date(time));
	}
	
	/**
	 * date转字符串
	 * @param date
	 * @param param
	 * @return
	 */
	public static String dateToStr(Date date, String param){
		if(null == date){
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(param); 
		return dateFormat.format(date);
	}
	
	/**
	 * 根据long型出生日期计算年龄
	 * @param longDay
	 * @return
	 */
	public static Integer birthdayToAge(Long longDay) {
		Date birthDay = new Date(longDay);
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) {
			throw new IllegalArgumentException("出生时间大于当前时间!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;// 注意此处，如果不加1的话计算结果是错误的
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age;
	}
}
