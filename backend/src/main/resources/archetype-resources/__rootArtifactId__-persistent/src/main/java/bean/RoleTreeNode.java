#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**********************************************************************
* <pre>
* FILE : PostRoleTreeNode.java
* CLASS : PostRoleTreeNode
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
* 		  |2014年6月2日| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* ${symbol_dollar}Id: PostRoleTreeNode.java,v 0.1 2014年6月2日 上午10:51:56 SuMMeR Exp ${symbol_dollar}
*/

package ${package}.bean;

import java.io.Serializable;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2014年6月2日
 * @version    :
 */
public class RoleTreeNode implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String id;

	private String pId;

	private String name;

	private String isParent;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getpId()
	{
		return pId;
	}

	public void setpId(String pId)
	{
		this.pId = pId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getIsParent()
	{
		return isParent;
	}

	public void setIsParent(String isParent)
	{
		this.isParent = isParent;
	}

}
