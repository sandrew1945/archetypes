#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**********************************************************************
* <pre>
* FILE : Principal.java
* CLASS : Principal
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
* 		  |2016年10月25日| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* ${symbol_dollar}Id: Principal.java,v 0.1 2016年10月25日 下午3:20:45 SuMMeR Exp ${symbol_dollar}
*/

package ${package}.core.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2016年10月25日
 * @version    :
 */
@Data
public class Principal implements Serializable
{
	private String name;

	private Integer Type;
}
