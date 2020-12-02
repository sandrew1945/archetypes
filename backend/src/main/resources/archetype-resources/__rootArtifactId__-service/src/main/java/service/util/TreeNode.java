#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**********************************************************************
* <pre>
* FILE : TreeNode.java
* CLASS : TreeNode
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
* 		  |2012-8-24| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* ${symbol_dollar}Id: TreeNode.java,v 1.1 2014/01/21 01:07:54 weibin Exp ${symbol_dollar}
*/

package ${package}.service.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2012-8-24
 * @version    :
 */
public class TreeNode
{
	private Integer functionId;

	private String path;

	private String name;

	private String icon;

	private Integer funcOrder;

	private List<TreeNode> children;

	public String getPath()
	{
		return path;
	}

	public Integer getFunctionId()
	{
		return functionId;
	}

	public void setFunctionId(Integer functionId)
	{
		this.functionId = functionId;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public Integer getFuncOrder()
	{
		return funcOrder;
	}

	public void setFuncOrder(Integer funcOrder)
	{
		this.funcOrder = funcOrder;
	}

	public List<TreeNode> getChildren()
	{
		return children;
	}

	public void setChildren(List<TreeNode> children)
	{
		this.children = children;
	}

	/**
	 * 
	 * Function    : 添加子节点
	 * LastUpdate  : 2012-8-24
	 * @param node
	 */
	public void addChildren(TreeNode node)
	{
		if (null == children)
		{
			children = new ArrayList<TreeNode>();
		}
		children.add(node);
	}

	/**
	 * 
	 * Function    : 判断是否存在子节点
	 * LastUpdate  : 2012-8-24
	 * @return
	 */
	public boolean hasChildren()
	{
		return null == children ? false : true;
	}

}
