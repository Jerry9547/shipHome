package org.springside.examples.quickstart.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;


public class TmFunctions {
	
	public static String substr(String input, int beginIndex, int endIndex) {
		String poinit = "";
		if (input == null)
			input = "";
		if (beginIndex >= input.length())
			return "";
		if (beginIndex < 0)
			beginIndex = 0;
		if ((endIndex < 0) || (endIndex > input.length())) {
			endIndex = input.length();
		}
		if (endIndex < beginIndex)
			return "";
		return input.substring(beginIndex, endIndex) + poinit;
	}
	
	/**
	 * 字符串截取
	 * @param sourceString
	 * @param beginIndex
	 * @param maxLength
	 * @return
	 */
	public static String subString(String sourceString, int beginIndex, int maxLength){
		String resultString = "";
		if (sourceString == null || sourceString.equals("") || maxLength < 1) {
			return resultString;
		} else if (sourceString.length() <= maxLength) {
			return sourceString;
		} else if (sourceString.length() > 2 * maxLength) {
			sourceString = sourceString.substring(0, 2 * maxLength);
		} 
		sourceString = sourceString.trim();
		if (sourceString.length() > maxLength) {
			char[] chr = sourceString.toCharArray();
			int strNum = 0;
			int strGBKNum = 0;
			boolean isHaveDot = false;
			for (int i = 0; i < sourceString.length(); i++) {
				if (chr[i] >= 0xa1) {
					strNum = strNum + 2;
					strGBKNum++;
				} else {
					strNum++;
				}

				if (strNum == 2 * maxLength || strNum == 2 * maxLength + 1) {
					if (i + 1 < sourceString.length()) {
						isHaveDot = true;
					}
					break;
				}
			}
			resultString = sourceString.substring(beginIndex, strNum - strGBKNum);
			resultString = resultString + "...";
		}else{
			resultString = sourceString;
		}
		return resultString;
	}
	
	/**
	 * 日期格式转换
	 * @param dateString
	 * @return
	 */
	public static String timeFormat(String dateString){
		if(StringUtils.isNotEmpty(dateString)){
			try{
				String date = getCurrentDate(parse(dateString,"yyyy-MM-dd HH:mm:ss"));
				return date;
			}catch(Exception ex){
				return dateString;
			}
		}
		return dateString;
	}
	
	private static String getCurrentDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	private static Date parse(String str, String format) {
		if (StringUtils.isEmpty(str))
			return null;
		try {
			SimpleDateFormat t = new SimpleDateFormat(format);
			return t.parse(str);
		} catch (ParseException e) {
		}
		return null;
	}
	
	public static boolean isEmpty(String str){
		return  StringUtils.isBlank(str);
	}
	
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	/**
	 * @param imgUrl
	 * @param imgSuffix
	 * @return
	 */
	public static String imgSuffix(String imgUrl,String imgSuffix){
		if(org.apache.commons.lang.StringUtils.isNotEmpty(imgUrl)){
			if(org.apache.commons.lang.StringUtils.isNotEmpty(imgSuffix)){
				int index = imgUrl.lastIndexOf(".");
				String prefix = imgUrl.substring(0, index);
				if(prefix.lastIndexOf("_s1") == (prefix.length()-3))
				{
					prefix = prefix.substring(0,prefix.length()-3);
				}
				String suffix = imgUrl.substring(index);
//				String[] imgArr=imgUrl.split("\\.");
//				imgArr[0]=imgArr[0]+imgSuffix;
//				return imgArr[0]+"."+imgArr[1];
				return prefix + imgSuffix + suffix;
			}else{
				return imgUrl;
			}
		}
		return "";
	}
	
	public static String getDateDiff(Object obj,String pattern) {
		Date date = null;
		if (obj instanceof Date)
		{
			date = (Date)obj;
		}else if(obj instanceof String){
			date = parse(obj.toString(), pattern);
		}
        if (date == null) {
            return "";
        }

        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy/MM/dd HH:mm:ss";
        }

        Date date2 = new Date(); // 结束时间
        Long date3 = date2.getTime() - date.getTime(); // 时间差的毫秒数
        // 计算出相差天数
        Long days = (long) Math.floor(date3 / (24 * 3600 * 1000));
        // 计算出小时数
        Long leave1 = date3 % (24 * 3600 * 1000); // 计算天数后剩余的毫秒数
        Long hours = (long) Math.floor(leave1 / (3600 * 1000));
        // 计算相差分钟数
        Long leave2 = leave1 % (3600 * 1000); // 计算小时数后剩余的毫秒数
        Long minutes = (long) Math.floor(leave2 / (60 * 1000));
        // 计算相差秒数
        Long leave3 = leave2 % (60 * 1000); // 计算分钟数后剩余的毫秒数
        Long seconds =  (long) Math.round(leave3 / 1000);
        String datetime = "";
        if(days<1 && hours<1 && minutes<1 && seconds>=0){
            if(seconds==0){
                seconds = 1L;
            }
            datetime = seconds+ " 秒前";
        }else if(days<1 && hours<1 && minutes>0){
            datetime = minutes + " 分钟前";
        }else if(days<1 && hours>0){
            datetime = hours + " 小时前";
        } else if(days <= 7) {
            if (days == 0) {
                datetime = " 刚刚";
            } else {
                datetime = days +" 天前";
            }

        } else {
        	SimpleDateFormat t = new SimpleDateFormat(pattern);
            String dataStr = t.format(date);
            if (dataStr.lastIndexOf(".") != -1) {
                datetime = dataStr.substring(0,dataStr.lastIndexOf("."));
            } else {
                datetime = dataStr;
            }
        }
        return datetime;
	}
	
	/**
	 * 
	 * @param content
	 * @param split
	 * @return String[]
	 */
	public static String[] split(String content,String split){
		if(StringUtils.isEmpty(content))return null;
		return content.split(split);
	}
	
	public static void main(String[] args)
	{
		System.out.println(TmFunctions.timeFormat("2015-11-1 10:06:08"));
	}
	
}
