#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * <pre>
 * FILE : MyFormAuthenticationFilter.java
 * CLASS : MyFormAuthenticationFilter
 *
 * AUTHOR : Administrator
 *
 * FUNCTION : TODO
 *
 *
 * ======================================================================
 * CHANGE HISTORY LOG
 * ----------------------------------------------------------------------
 * MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
 * ----------------------------------------------------------------------
 * 		  |2017年5月8日| Administrator| Created |
 * DESCRIPTION:
 * </pre>
 * <p/>
 * ${symbol_dollar}Id: MyFormAuthenticationFilter.java,v 0.1 2017年5月8日 上午9:41:32 Administrator Exp ${symbol_dollar}
 */

package ${package}.config.shiro;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Function    : 
 * @author     : Administrator
 * CreateDate  : 2017年5月8日
 * @version    :
 */
@Log4j2
public class MyFormAuthenticationFilter extends FormAuthenticationFilter
{
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        // 如果验证码验证失败,跳过验证
        if (request.getAttribute(getFailureKeyAttribute()) != null)
        {
			return true;
        }
        return super.onAccessDenied(request, response);
    }

    /* 分布式时使用,暂时关闭
    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception
    {
        // 判断session中是否存在authc.fallbackUrl，如果存在，说明是从其他系统重定向过来的请求，否则是server系统自身登录
        Session session = getSubject(request, response).getSession();
        String fallbackUrl = (String) session.getAttribute("authc.fallbackUrl");
        log.debug("fallbackUrl =============" + fallbackUrl);
        if (StringUtils.isEmpty(fallbackUrl))
        {
            fallbackUrl = getSuccessUrl();
        }
        WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
    }
    */

}