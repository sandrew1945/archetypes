#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**********************************************************************
* <pre>
* FILE : LoginResult.java
* CLASS : LoginResult
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
* 		  |2014年6月6日| Administrator| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* ${symbol_dollar}Id: LoginResult.java,v 0.1 2014年6月6日 上午9:36:25 Administrator Exp ${symbol_dollar}
*/

package ${package}.core.common;

/**
 * Function    : 	登录结果
 * @author     : Administrator
 * CreateDate  : 2014年6月6日
 * @version    :
 */
public class LoginResult
{
	private String returnView;

	private boolean hasError;

	public LoginResult(String returnView, boolean hasError)
	{
		super();
		this.returnView = returnView;
		this.hasError = hasError;
	}

	public String getReturnView()
	{
		return returnView;
	}

	public void setReturnView(String returnView)
	{
		this.returnView = returnView;
	}

	public boolean getHasError()
	{
		return hasError;
	}

	public void setHasError(boolean hasError)
	{
		this.hasError = hasError;
	}

}
