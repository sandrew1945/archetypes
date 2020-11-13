#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Function    : 日期工具类
 * @author     : SuMMeR
 * CreateDate  : 2010-11-12
 * @version    :
 */
public class DateTimeUtil
{
	private static final String DATE_PATTERN_DATE = "yyyy-MM-dd";

	private static final String DATE_PATTERN_TIME = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 * Function    : 将字符传转成日期类型
	 * LastUpdate  : 2010-12-6
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String time) throws ParseException
	{
		if (time == null || time.equals(""))
		{
			return null;
		}
		SimpleDateFormat formate = new SimpleDateFormat(DATE_PATTERN_DATE);
		return formate.parse(time);
	}

	/**
	 * 
	 * Function    : 将日期格式化成字符串 yyyy-MM-dd HH:mm:ss格式
	 * LastUpdate  : 2010-11-12
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String parseDateToTimeString(Date date) throws ParseException
	{
		if (date == null)
		{
			return null;
		}
		SimpleDateFormat formate = new SimpleDateFormat(DATE_PATTERN_TIME);
		return formate.format(date);
	}

	/**
	 * 
	 * Function    : 将日期格式化成字符串 yyyy-MM-dd 格式
	 * LastUpdate  : 2010-11-12
	 * @param date
	 * @return
	 */
	public static String parseDateToDateString(Date date)
	{
		if (date == null)
		{
			return null;
		}
		SimpleDateFormat formate = new SimpleDateFormat(DATE_PATTERN_DATE);
		return formate.format(date);
	}

	/**
	 * 
	 * Function    : 将日期格式化成字符串
	 * LastUpdate  : 2010-11-12
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String parseDateToDate(Date date) throws ParseException
	{
		if (date == null)
		{
			return null;
		}
		SimpleDateFormat formate = new SimpleDateFormat(DATE_PATTERN_DATE);
		return formate.format(date);
	}

	/**
	 * 
	 * Function    : 将毫秒转化成日期的字符串表示
	 * LastUpdate  : 2010-11-12
	 * @param time
	 * @return
	 */
	public static String getDateTimeFormat(long time)
	{
		SimpleDateFormat formate = new SimpleDateFormat(DATE_PATTERN_TIME);
		Date date = new Date(time);
		String dateStr = formate.format(date);
		return dateStr;
	}

	/**
	 * 
	 * Function    : 将毫秒转化成日期
	 * LastUpdate  : 2010-11-12
	 * @param time
	 * @return
	 */
	public static Date getDateByTime(long time)
	{
		Date date = new Date(time);
		return date;
	}

	/**
	 * 
	 * Function    : 获取当前年月
	 * LastUpdate  : 2010-12-20
	 * @return		"yyyyMM"
	 */
	public static String getYearMonthStr()
	{
		SimpleDateFormat formator = new SimpleDateFormat("yyyyMM");
		return formator.format(new Date());
	}
}
