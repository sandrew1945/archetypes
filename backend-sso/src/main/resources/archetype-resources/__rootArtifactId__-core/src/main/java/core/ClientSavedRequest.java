#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.core;

import org.apache.shiro.web.util.SavedRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class ClientSavedRequest extends SavedRequest
{
	private String scheme;

	private String domain;

	private int port;

	private String contextPath;

	private String backUrl;

	public ClientSavedRequest(HttpServletRequest request, String backUrl)
	{
		super(request);
		this.scheme = request.getScheme();
		this.domain = request.getServerName();
		this.port = request.getServerPort();
		this.contextPath = request.getContextPath();
		this.backUrl = backUrl;
	}

	/**
	 *  生成请求地址
	 */
	public String getRequestUrl()
	{
		String requestURI = getRequestURI();
		if (null != backUrl)
		{
			if (backUrl.toLowerCase().startsWith("http://") || backUrl.toLowerCase().startsWith("https://"))
			{
				return backUrl;
			}
			else if (backUrl.startsWith(contextPath))
			{
				requestURI = backUrl;
			}
			else
			{
				requestURI = contextPath + backUrl;
			}
		}

		StringBuilder requestUrl = new StringBuilder(scheme);
		requestUrl.append("://").append(domain);
		if ("http".equalsIgnoreCase(scheme) && port != 80)
		{
			requestUrl.append(":").append(port);
		}
		else if ("https".equalsIgnoreCase(scheme) && port != 443)
		{
			requestUrl.append(":").append(port);
		}
		requestUrl.append(requestURI);
		if(null == backUrl && null != getQueryString())
		{
			requestUrl.append("?").append(getQueryString());
		}
		return requestUrl.toString();
	}

	public String getScheme()
	{
		return scheme;
	}

	public void setScheme(String scheme)
	{
		this.scheme = scheme;
	}

	public String getDomain()
	{
		return domain;
	}

	public void setDomain(String domain)
	{
		this.domain = domain;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public String getContextPath()
	{
		return contextPath;
	}

	public void setContextPath(String contextPath)
	{
		this.contextPath = contextPath;
	}

	public String getBackUrl()
	{
		return backUrl;
	}

	public void setBackUrl(String backUrl)
	{
		this.backUrl = backUrl;
	}

}
