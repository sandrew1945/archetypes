#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**********************************************************************
* <pre>
* FILE : EBException.java
* CLASS : EBException
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
* 		  |2009-11-2| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* ${symbol_dollar}Id: ServiceException.java,v 1.1 2013/07/31 08:32:47 xin.jin Exp ${symbol_dollar}
*/
package ${package}.core.exception;


/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2009-11-2
 * @version    :
 */
public class DAOException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4042594687623349469L;

	public DAOException()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOException(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DAOException(String message)
	{
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DAOException(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
