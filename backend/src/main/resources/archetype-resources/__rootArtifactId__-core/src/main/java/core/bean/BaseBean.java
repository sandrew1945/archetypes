#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**********************************************************************
 * <pre> FILE : EAIBean.java CLASS : EAIBean AUTHOR : SuMMeR FUNCTION : TODO
 * ====================================================================== CHANGE HISTORY LOG
 * ---------------------------------------------------------------------- MOD. NO.| DATE | NAME |
 * REASON | CHANGE REQ. ----------------------------------------------------------------------
 * |2010-3-19| SuMMeR| Created | DESCRIPTION: </pre>
 ***********************************************************************/
/**
 * ${symbol_dollar}Id: BaseBean.java,v 1.1 2013/07/31 08:32:41 xin.jin Exp ${symbol_dollar}
 */

package ${package}.core.bean;


import ${package}.core.common.Reflections;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Modify:Zoc.Lee 添加了静态属性判断,如果是静态属性,滤过
 * 
 * */

public abstract class BaseBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Override
	public String toString()
	{
		StringBuilder sB = new StringBuilder();
		Class<?> cls = this.getClass();
		Field[] fields = cls.getDeclaredFields();
		Field field = null;
		for (int i = 0; i < fields.length; i++)
		{
			field = fields[i];

			if (Modifier.isStatic(field.getModifiers()))
			{
				continue;
			}

			String fieldName = field.getName();
			sB.append(fieldName + " : ");
			String methodName = Reflections.getMethodOfGetByFieldName(fieldName);
			try
			{
				Method meth = cls.getMethod(methodName, null);
				meth.setAccessible(true);
				Object[] arglist = new Object[0];
				Object value = meth.invoke(this, arglist);
				if (null != value)
				{
					sB.append(value.toString());
				}
				else
				{
					sB.append("");
				}
				sB.append("${symbol_escape}r${symbol_escape}n");
			}
			catch (SecurityException e)
			{
				e.printStackTrace();
			}
			catch (NoSuchMethodException e)
			{
				e.printStackTrace();
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
			catch (InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
		return sB.toString();
	}
}
