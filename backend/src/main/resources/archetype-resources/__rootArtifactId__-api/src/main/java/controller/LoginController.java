#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;


import ${package}.core.common.JsonResult;
import ${package}.core.controller.BaseController;
import ${package}.core.exception.JsonException;
import ${package}.core.exception.ServiceException;
import ${package}.model.TmUserPO;
import ${package}.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Slf4j
public class LoginController extends BaseController
{

    @Resource
    LoginService loginService;



    @PostMapping(value = "/login")
    public JsonResult login(String userCode, String password) throws JsonException
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

    @GetMapping(value = "/userInfo")
    public JsonResult userInfo() throws JsonException
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

    @GetMapping(value = "/getMenuByRole")
    public JsonResult getMenuByRole(Integer roleId) throws JsonException
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

    @PostMapping(value = "/logout")
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
