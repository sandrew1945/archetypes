#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**********************************************************************
* <pre>
* FILE : PlainCredentialsMatcher.java
* CLASS : PlainCredentialsMatcher
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
* 		  |2016年9月21日| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* ${symbol_dollar}Id: PlainCredentialsMatcher.java,v 0.1 2016年9月21日 下午1:34:08 SuMMeR Exp ${symbol_dollar}
*/

package ${package}.core.shiro;

import ${package}.core.bean.Principal;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * Function    : 明文密码验证
 * @author     : SuMMeR
 * CreateDate  : 2016年9月21日
 * @version    :
 */
public class RemoteCredentialsMatcher extends HashedCredentialsMatcher
{
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
	{
		try
		{
			MyUsernamePasswordToken userNamePasswordToken = (MyUsernamePasswordToken) token;
			String loginPassword = String.valueOf(userNamePasswordToken.getPassword());	// 登录时输入的密码
			String userPassword = info.getCredentials().toString();						// 数据库存储的密码

			Integer loginUserType = userNamePasswordToken.getUserType();				// 登录时输入的用户类型
			Integer userType = ((Principal)info.getPrincipals().getPrimaryPrincipal()).getType();	// 数据库存储的用户类型

			//内容一致就返回true,不一致就返回false
			if((loginPassword.equals(userPassword)) && (loginUserType.equals(userType)))
			{
				return true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
