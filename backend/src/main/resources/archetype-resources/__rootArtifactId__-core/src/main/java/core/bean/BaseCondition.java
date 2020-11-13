#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**********************************************************************
* <pre>
* FILE : BaseCondition.java
* CLASS : BaseCondition
*
* AUTHOR : Administrator
*
* FUNCTION : TODO
*
*
*======================================================================
* CHANGE HISTORY LOG
*----------------------------------------------------------------------
* MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
*----------------------------------------------------------------------
* 		  |2014年6月18日| Administrator| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* ${symbol_dollar}Id: BaseCondition.java,v 0.1 2014年6月18日 上午11:17:54 Administrator Exp ${symbol_dollar}
*/

package ${package}.core.bean;

import java.lang.reflect.Field;

/**
 * Function    : 系统查询条件基础Bean
 * @author     : Administrator
 * CreateDate  : 2014年6月18日
 * @version    :
 */
public class BaseCondition
{
	public Integer pageSize;

	public Integer curPage;


	public Integer getPageSize()
	{
		return pageSize;
	}


	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}


	public Integer getCurPage()
	{
		return curPage;
	}


	public void setCurPage(Integer curPage)
	{
		this.curPage = curPage;
	}


	/**
	 * 
	 * Function    : 判断是否为空
	 * LastUpdate  : 2014年6月18日
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public boolean isNull() throws IllegalArgumentException, IllegalAccessException
	{
		Class<?> cls = this.getClass();
		Field[] fields = cls.getFields();
		//Field field = null;
		for (Field field : fields)
		{
			field.setAccessible(true);
			Object value = field.get(this);
			if (null != value)
			{
				return false;
			}
		}
		return true;
	}
}
