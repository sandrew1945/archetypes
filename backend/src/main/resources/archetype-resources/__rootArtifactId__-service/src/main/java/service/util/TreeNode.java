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
	private String id;

	private String code;

	private String name;

	private String imgUrl;

	private List<TreeNode> children;

	private TreeNode fatherNode;

	private String fatherId;

	private String url;

	public String getImgUrl()
	{
		return imgUrl;
	}

	public void setImgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public TreeNode getFatherNode()
	{
		return fatherNode;
	}

	public void setFatherNode(TreeNode fatherNode)
	{
		this.fatherNode = fatherNode;
	}

	public String getFatherId()
	{
		return fatherId;
	}

	public void setFatherId(String fatherId)
	{
		this.fatherId = fatherId;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public List<TreeNode> getChildren()
	{
		return children;
	}

	/**
	 * 
	 * Function    : 添加子节点
	 * LastUpdate  : 2012-8-24
	 * @param node
	 */
	public void addChild(TreeNode node)
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

	/**
	 * 
	 * Function    : 判断是否是叶子节点
	 * LastUpdate  : 2012-10-30
	 * @return
	 */
	public boolean isLeaf()
	{
		if (null == children || children.size() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * Function    : 判断是否是根节点
	 * LastUpdate  : 2016年10月25日
	 * @return
	 */
	public boolean isRoot()
	{
		if (null == fatherNode)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
