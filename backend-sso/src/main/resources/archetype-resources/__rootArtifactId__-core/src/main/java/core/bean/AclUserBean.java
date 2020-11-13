#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**********************************************************************
* <pre>
* FILE : AclUserBean.java
* CLASS : AclUserBean
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
* 		  |2010-11-11| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* ${symbol_dollar}Id: AclUserBean.java,v 1.1 2013/07/31 08:32:41 xin.jin Exp ${symbol_dollar}
*/

package ${package}.core.bean;

import lombok.Data;

import java.util.List;
import java.util.Locale;

/**
 * Function    : 系统用户
 * @author     : SuMMeR
 * CreateDate  : 2010-11-11
 * @version    :
 */
@Data
public class AclUserBean extends BaseBean
{
	private Integer userId;

	private String userCode;

	private String userName;

	private Locale locale;

	private List roleList;

	private Integer roleId;

	private String roleName;

	private String roleCode;

	private Integer companyId;
	
	private Integer sex;
	
	private Integer userType;
	
	private String phone;
	
	private String mobile;
	
	private String email;
	
	private String avatarPath;
	
	private String userStatus;

	private String token;
}
