#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config.shiro;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by summer on 2018/5/3.
 */
@Log4j2
public class CaptchaValidateFilter extends AccessControlFilter
{
    private boolean captchaEnable = true;                       // 是否开启验证码
    private String captchaParam = "captcha";                    // 前端提交验证码参数名
    private String failureKeyAttribute = "shiroLoginFailure";      // 验证失败存储失败信息的属性名

    public boolean isCaptchaEnable()
    {
        return captchaEnable;
    }

    public void setCaptchaEnable(boolean captchaEnable)
    {
        this.captchaEnable = captchaEnable;
    }

    public String getCaptchaParam()
    {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam)
    {
        this.captchaParam = captchaParam;
    }

    public String getFailureKeyAttribute()
    {
        return failureKeyAttribute;
    }

    public void setFailureKeyAttribute(String failureKeyAttribute)
    {
        this.failureKeyAttribute = failureKeyAttribute;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception
    {
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        // 如果验证码没有开启或者不是表单提交,验证通过
        if (captchaEnable == false || !"post".equalsIgnoreCase(request.getMethod()))
        {
            return true;
        }
        String inputCaptcha = request.getParameter("captcha");
        boolean isAccessAllowed = validateCaptcha(request, inputCaptcha);
        return isAccessAllowed;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception
    {
        servletRequest.setAttribute(failureKeyAttribute, "captcha.error");
        return true;
    }

    /**
     *  检验验证码是否正确
     * @param request
     * @param captcha
     * @return
     */
    private boolean validateCaptcha(HttpServletRequest request, String captcha)
    {
        String sessionCaptcha = (String) request.getSession().getAttribute("captcha");
        if(null != sessionCaptcha && !"".equals(sessionCaptcha))
        {
            return sessionCaptcha.equalsIgnoreCase(captcha);
        }
        return false;
    }
}
