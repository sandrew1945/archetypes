#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**********************************************************************
* <pre>
* FILE : RoleFunctionBean.java
* CLASS : RoleFunctionBean
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
* 		  |2016年9月22日| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* ${symbol_dollar}Id: RoleFunctionBean.java,v 0.1 2016年9月22日 下午10:16:10 SuMMeR Exp ${symbol_dollar}
*/

package ${package}.bean;

import lombok.Data;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2016年9月22日
 * @version    :
 */
@Data
public class RoleFunctionBean
{
	private Integer roleId;

	private String roleCode;

	private String roleName;

	private Integer functionId;

	private String functionCode;

	private String functionName;

}
