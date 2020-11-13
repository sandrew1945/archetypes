#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;


import ${package}.core.bean.AclUserBean;
import ${package}.core.common.JsonResult;
import ${package}.core.controller.BaseController;
import ${package}.core.exception.JsonException;
import ${package}.core.exception.ServiceException;
import ${package}.model.TmUserPO;
import ${package}.service.UserManagerService;
import com.sandrew.bury.bean.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/usermanager")
public class UserManagerController extends BaseController
{
    @Resource
    private UserManagerService userManagerService;

    @PostMapping(value = "/userManagerPageQuery")
    public
    @ResponseBody
    PageResult<AclUserBean> userManagerPageQuery(@RequestParam(required = false) String userCode, @RequestParam(required = false) String userName, @RequestParam(required = false) Integer userStatus, int limit, int curPage) throws JsonException
    {
        try
        {
            TmUserPO condition = new TmUserPO();
            condition.setUserCode(userCode);
            condition.setUserName(userName);
            condition.setUserStatus(userStatus);
            return userManagerService.userManagerPageQuery(userCode, userName, userStatus, limit, curPage);
        }
        catch (Exception e)
        {
            throw new JsonException(e.getMessage(), e);
        }

    }

    @GetMapping("getUserInfoById")
    public @ResponseBody
    JsonResult getUserInfoById(Integer userId) throws JsonException
    {
        try
        {
            JsonResult result = new JsonResult();
            TmUserPO user = userManagerService.findByUserId(userId);
            return result.requestSuccess(user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("获取用户信息失败", e);
        }
    }

    @PostMapping(value = "/createUserInfo")
    public
    @ResponseBody
    JsonResult createUserInfo(TmUserPO user) throws JsonException
    {
        try
        {
            return userManagerService.createUserInfo(user, null, getLoginUser());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }


    @PostMapping(value = "/updateUserInfo")
    public
    @ResponseBody
    JsonResult updateUserInfo(TmUserPO user) throws JsonException
    {
        try
        {
            return userManagerService.updateUserInfo(user, null, getLoginUser());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }

    @PostMapping(value = "/deleteUserInfo")
    public
    @ResponseBody
    JsonResult deleteUserInfo(Integer userId) throws JsonException
    {
        try
        {
            return userManagerService.deleteUserInfo(userId, getLoginUser());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/queryRelationRoles")
    public
    @ResponseBody
    JsonResult queryRelationRoles(Integer userId) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            return result.requestSuccess(userManagerService.getRelationRolesByUserId(userId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }


    @PostMapping(value = "/deleteRoleRelation")
    public
    @ResponseBody
    JsonResult deleteRoleRelation(Integer userId, Integer roleId) throws JsonException
    {
        try
        {
            return userManagerService.deleteRoleRelation(userId, roleId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }

    @PostMapping(value = "/queryUnRelationRoles")
    public
    @ResponseBody
    JsonResult queryUnRelationRoles(Integer userId, String roleName) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            AclUserBean aclUser = new AclUserBean();
            aclUser.setUserId(userId);
            aclUser.setRoleName(roleName);
            return result.requestSuccess(userManagerService.getUnRelationRoles(aclUser));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }


    @PostMapping(value = "/createRelation")
    public
    @ResponseBody
    JsonResult createRelation(Integer userId, String rolesStr) throws JsonException
    {
        try
        {
            return userManagerService.createRelation(userId, rolesStr, getLoginUser());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/getUserList")
    public @ResponseBody JsonResult getUserList() throws JsonException
    {
        try
        {
            return userManagerService.getAvailableUserList();
        }
        catch (ServiceException e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }

    @PostMapping(value = "/updatePassword")
    public @ResponseBody JsonResult updatePassword(Integer userId, String originPwd, String newPwd) throws JsonException
    {
        try
        {
            return userManagerService.updatePassword(userId, originPwd, newPwd, getLoginUser());
        }
        catch (ServiceException e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }
}
