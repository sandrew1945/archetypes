#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**********************************************************************
* <pre>
* FILE : MenuBean.java
* CLASS : MenuBean
*
* AUTHOR : SuMMeR
*
* FUNCTION : TODO
*
*
*======================================================================
* CHANGE HISTORY LOG
*----------------------------------------------------------------------
* MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
*----------------------------------------------------------------------
* 		  |2010-11-15| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* ${symbol_dollar}Id: MenuBean.java,v 1.1 2014/01/21 01:08:22 weibin Exp ${symbol_dollar}
*/

package ${package}.bean;

import java.io.Serializable;

/**
 * Function    : 菜单BEAN
 * @author     : SuMMeR
 * CreateDate  : 2010-11-15
 * @version    :
 */
public class MenuBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String funcId;

	private String funcCode;

	private String funcName;

	private String funcName_en;

	private String parentId;

	private String functionUrl;
	
	private String imgUrl;
	
		
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getFuncId()
	{
		return funcId;
	}

	public void setFuncId(String funcId)
	{
		this.funcId = funcId;
	}

	public String getFuncCode()
	{
		return funcCode;
	}

	public void setFuncCode(String funcCode)
	{
		this.funcCode = funcCode;
	}

	public String getFuncName()
	{
		return funcName;
	}

	public void setFuncName(String funcName)
	{
		this.funcName = funcName;
	}

	public String getFuncName_en()
	{
		return funcName_en;
	}

	public void setFuncName_en(String funcName_en)
	{
		this.funcName_en = funcName_en;
	}

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public String getFunctionUrl()
	{
		return functionUrl;
	}

	public void setFunctionUrl(String functionUrl)
	{
		this.functionUrl = functionUrl;
	}

}
