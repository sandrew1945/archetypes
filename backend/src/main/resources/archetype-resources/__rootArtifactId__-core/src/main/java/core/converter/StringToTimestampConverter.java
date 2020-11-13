#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Function    : String转Timestamp转换器
 * @author     : Administrator
 * CreateDate  : 2014年8月15日
 * @version    :
 */
public class StringToTimestampConverter implements Converter<String, Timestamp>
{

	@Override
	public Timestamp convert(String source)
	{
		try
		{
			if (!StringUtils.hasLength(source))
			{
				return null;
			}
			Format f = new SimpleDateFormat("yyyy-MM-dd");
			Date d = (Date) f.parseObject(source);
			Timestamp timestamp = new Timestamp(d.getTime());
			return timestamp;
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException(String.format("类型转换失败，需要格式[yyyy-mm-dd]，但格式是[%s]", source));
		}
	}

}
