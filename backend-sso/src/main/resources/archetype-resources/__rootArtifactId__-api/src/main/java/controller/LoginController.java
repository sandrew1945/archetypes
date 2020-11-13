#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;


import ${package}.core.bean.AclUserBean;
import ${package}.core.common.Constants;
import ${package}.core.common.JsonResult;
import ${package}.core.controller.BaseController;
import ${package}.core.exception.JsonException;
import ${package}.core.exception.ServiceException;
import ${package}.model.TmUserPO;
import ${package}.service.LoginService;
import io.buji.pac4j.subject.Pac4jPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
@Slf4j
public class LoginController extends BaseController
{
    @Value("${symbol_dollar}{sso.local.address}")
    private String localAddress;

    @Resource
    LoginService loginService;

    @RequestMapping(value = "/login")
    public @ResponseBody
    JsonResult login(String userCode, String password) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            TmUserPO user = new TmUserPO();
            user.setUserCode(userCode);
            user.setPassword(password);
            result = result.requestSuccess(loginService.login(user));
        }
        catch (Exception e)
        {
            String errorMsg = null;
            if (e instanceof ServiceException)
            {
                errorMsg = "用户名或密码错误";
            }
            else
            {
                errorMsg = "用户登录失败";
            }
            result.requestFailure(errorMsg);
            log.error(e.getMessage(), e);
        }

        return result;
    }

    @RequestMapping("/sso")
    public String sso(HttpServletRequest request, HttpServletResponse response)
    {
        Pac4jPrincipal principal = (Pac4jPrincipal) request.getUserPrincipal();
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> attributes = principal.getProfile().getAttributes();
//        Set<String> keys = attributes.keySet();
//        keys.stream().forEach( key -> {
//            System.out.println(attributes.get(key));
//        });
        AclUserBean loginUser = new AclUserBean();
        loginUser.setUserId(new Integer(attributes.get("id").toString()));
        loginUser.setUserCode(principal.getProfile().getId());
        loginUser.setUserName(attributes.get("name").toString());
        loginUser.setPhone(attributes.get("phone").toString());
        loginUser.setEmail(attributes.get("email").toString());
        subject.getSession().setAttribute(Constants.LOGIN_USER, loginUser);
        Cookie cookie = new Cookie("vue_admin_template_token", subject.getSession().getId().toString());
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:" + localAddress;
    }

    @RequestMapping(value = "/userInfo")
    public @ResponseBody JsonResult userInfo() throws JsonException
    {
        try
        {
            JsonResult result = new JsonResult();
            return result.requestSuccess(loginService.userInfo(getLoginUser()));
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("获取用户信息失败", e);
        }
    }

    @RequestMapping(value = "/getMenuByRole")
    public @ResponseBody JsonResult getMenuByRole(Integer roleId) throws JsonException
    {
        try
        {
            JsonResult result = new JsonResult();
            return result.requestSuccess(loginService.getMenuByRole(roleId));
        }
        catch (ServiceException e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("获取系统菜单失败", e);
        }
    }

    @RequestMapping(value = "/logout")
    public
    @ResponseBody
    JsonResult logout() throws JsonException
    {
        try
        {
            JsonResult result = new JsonResult();
            loginService.logout();
            return result.requestSuccess(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException("登出系统失败", e);
        }

    }

}
