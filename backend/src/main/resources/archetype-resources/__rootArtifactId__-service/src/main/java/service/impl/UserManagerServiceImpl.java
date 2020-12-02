#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * <pre>
 * FILE : LoginService.java
 * CLASS : LoginService
 *
 * AUTHOR : SuMMeR
 *
 * FUNCTION : TODO
 *
 *
 * ======================================================================
 * CHANGE HISTORY LOG
 * ----------------------------------------------------------------------
 * MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
 * ----------------------------------------------------------------------
 * 		  |2014年5月3日| SuMMeR| Created |
 * DESCRIPTION:
 * </pre>
 * <p>
 * ${symbol_dollar}Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp ${symbol_dollar}
 */
/**
 * ${symbol_dollar}Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp ${symbol_dollar}
 */

package ${package}.service.impl;


import ${package}.core.bean.AclUserBean;
import ${package}.core.common.Constants;
import ${package}.core.common.JsonResult;
import ${package}.core.common.MD5Encrypt;
import ${package}.core.exception.ServiceException;
import ${package}.core.exception.TooManyResultsException;
import ${package}.dao.CommonDAO;
import ${package}.dao.UserManagerDAO;
import ${package}.model.TmRolePO;
import ${package}.model.TmUserPO;
import ${package}.model.TrUserRolePO;
import ${package}.service.UserManagerService;
import ${groupId}.bury.bean.EqualPack;
import ${groupId}.bury.bean.Pack;
import ${groupId}.bury.bean.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2014年5月3日
 * @version    :
 */
@Service
@CacheConfig(cacheNames = "user")
@Slf4j
public class UserManagerServiceImpl implements UserManagerService
{

    @Resource
    private UserManagerDAO userManagerDAO;

    @Resource
    private CommonDAO commonDAO;


    @Override
    public PageResult<AclUserBean> userManagerPageQuery(String userCode, String userName, Integer userStatus, int limit, int curPage) throws ServiceException
    {
        try
        {
            return userManagerDAO.userManagerPageQuery(userCode, userName, userStatus, limit, curPage);
        }
        catch (Exception e)
        {
            throw new ServiceException("用户列表查询失败", e);
        }
    }

    @Override
    public JsonResult createUserInfo(TmUserPO user, MultipartFile avatar, AclUserBean aclUser) throws ServiceException
    {
        JsonResult result = new JsonResult();
        try
        {
            boolean isExits = false;
            TmUserPO cond = new TmUserPO();
            cond.setUserCode(user.getUserCode());
            cond.setUserStatus(Constants.IF_TYPE_NO);
            List<TmUserPO> list = commonDAO.select(cond);

            isExits = (null != list && list.size() > 0) ? true : false;
            if (!isExits)
            {
                user.setIsDelete(Constants.IF_TYPE_NO);
                user.setCreateBy(aclUser.getUserId());
                user.setCreateDate(new Date());
                user.setPassword(MD5Encrypt.MD5Encode(user.getPassword()));
                result.requestSuccess(commonDAO.insert(user));
            }
            else
            {
                result.requestFailure("用户已经存在");
            }
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new ServiceException("创建用户失败", e);
        }
    }

    @Override
    public JsonResult deleteUserInfo(Integer userId, AclUserBean aclUser) throws ServiceException
    {
        try
        {
            JsonResult result = new JsonResult();
            TmUserPO cond = new TmUserPO(userId);

            TmUserPO value = new TmUserPO();
            value.setIsDelete(Constants.IF_TYPE_YES);
            value.setUpdateBy(aclUser.getUserId());
            value.setUpdateDate(new Date());

            int count = commonDAO.update(cond, value);
            if (count > 0)
            {
                result.requestSuccess(true);
            }
            else
            {
                result.requestFailure("删用户失败");
            }
            return result;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("删除用户失败", e);
        }
    }


    @Override
    public TmUserPO findByUserId(Integer userId) throws ServiceException
    {

        try
        {
            log.debug("缓存中不存在用户信息,读取数据库...");
            TmUserPO tmUserPO = commonDAO.selectById(new TmUserPO(userId));
            return tmUserPO;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("查询失败", e);
        }
    }

    @Override
    public TmUserPO getUserByCode(String userCode) throws ServiceException
    {
        try
        {
            if (null == userCode || "".equals(userCode))
            {
                return null;
            }
            TmUserPO cond = new TmUserPO();
            cond.setUserCode(userCode);
            cond.setIsDelete(Constants.IF_TYPE_NO);
            cond.setUserStatus(Constants.STATUS_ENABLE);
            List<TmUserPO> users = commonDAO.select(cond);
            if (null != users && users.size() == 1)
            {
                return users.get(0);
            }
            else
            {
                throw new TooManyResultsException("userCode为:" + userCode + "的用户重复");
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @liutt
     * 修改编辑用户信息
     * @param user
     * @param avatar
     * @param aclUser
     * @return
     * @throws ServiceException
     */
    @Override
    public JsonResult updateUserInfo(TmUserPO user, MultipartFile avatar, AclUserBean aclUser) throws ServiceException
    {
        JsonResult result = new JsonResult();
        try
        {
            TmUserPO cond = new TmUserPO(user.getUserId());

            user.setUpdateDate(new Date());
            user.setUpdateBy(aclUser.getUserId());
            //如果用户没有填写密码选项，则密码不改变
            if (StringUtils.isNotEmpty(user.getPassword()))
            {
                user.setPassword(MD5Encrypt.MD5Encode(user.getPassword()));
            }
            else
            {
                user.setPassword((Pack<String>) null);
            }
            return result.requestSuccess(commonDAO.update(cond, user));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new ServiceException("编辑用户失败", e);
        }
    }

    @Override
    public List<TmRolePO> getRelationRolesByUserId(Integer userId) throws ServiceException
    {
        try
        {
            return userManagerDAO.queryRelationRole(userId);
        }
        catch (Exception e)
        {
            throw new ServiceException("获取已有角色失败", e);
        }

    }

    @Override
    public JsonResult deleteRoleRelation(Integer userId, Integer roleId) throws ServiceException
    {
        try
        {
            JsonResult result = new JsonResult();
            TrUserRolePO userRole = new TrUserRolePO();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            int count = commonDAO.delete(userRole);
            if (count > 0)
            {
                result.requestSuccess(true);
            }
            else
            {
                result.requestFailure("删除角色失败");
            }
            return result;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("删除角色失败", e);
        }
    }

    @Override
    public List<TmRolePO> getUnRelationRoles(AclUserBean aclUser) throws ServiceException
    {
        try
        {
            List<TmRolePO> roles = userManagerDAO.getRoleExistOwn(aclUser.getUserId(), aclUser.getRoleName());
            return roles;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取未关联角色失败", e);
        }

    }

    @Override
    public JsonResult createRelation(Integer userId, String rolesStr, AclUserBean aclUser) throws ServiceException
    {
        try
        {
            String[] roles = rolesStr.split(",");
            List<TrUserRolePO> list = new ArrayList<>();
            for (String roleId : roles)
            {
                TrUserRolePO userRole = new TrUserRolePO();
                userRole.setUserId(userId);
                userRole.setRoleId(new Integer(roleId));
                userRole.setCreateBy(aclUser.getUserId());
                userRole.setCreateDate(new Date());
                list.add(userRole);
            }
            commonDAO.insert(list);
            JsonResult ajaxResult = new JsonResult();
            return ajaxResult.requestSuccess();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("添加角色失败", e);
        }

    }

    @Override
    public JsonResult updateClearAvatar(Integer userId, AclUserBean loginUser) throws ServiceException
    {
        try
        {
            TmUserPO cond = new TmUserPO(userId);

            TmUserPO value = new TmUserPO();
            value.setAvatar(new EqualPack<String>(null));
            value.setUpdateBy(loginUser.getUserId());
            value.setUpdateDate(new Date());
            int count = commonDAO.update(cond, value);
            JsonResult ajaxResult = new JsonResult();
            if (count > 0)
            {
                return ajaxResult.requestSuccess();
            }
            else
            {
                return ajaxResult.requestFailure("用户保存失败");
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("用户保存失败", e);
        }
    }

    @Override
    public JsonResult getAvailableUserList() throws ServiceException
    {
        JsonResult result = new JsonResult();
        try
        {
            TmUserPO cond = new TmUserPO();
            cond.setIsDelete(Constants.IF_TYPE_NO);
            cond.setUserStatus(Constants.STATUS_ENABLE);
            List<TmUserPO> list = commonDAO.select(cond);
            if (null != list && list.size() > 0)
            {
                result.requestSuccess(list);
            }
            else
            {
                result.requestFailure("查询用户失败");
            }
            return result;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("查询用户失败", e);
        }
    }

    @Override
    public JsonResult updatePassword(Integer userId, String originPwd, String newPwd, AclUserBean loginUser) throws ServiceException
    {
        JsonResult result = new JsonResult();
        try
        {
            // 验证原始密码是否正确
            TmUserPO userPO = commonDAO.selectById(new TmUserPO(userId));
            if (null != userPO && !MD5Encrypt.MD5Encode(originPwd).equals(userPO.getPassword()))
            {
                result.requestFailure("输入的密码不正确");
            }
            else
            {
                // 更新密码
                TmUserPO cond = new TmUserPO(userId);

                userPO = new TmUserPO();
                userPO.setPassword(MD5Encrypt.MD5Encode(newPwd));
                userPO.setUpdateBy(loginUser.getUserId());
                userPO.setUpdateDate(new Date());
                int count = commonDAO.update(cond, userPO);
                if (count > 0)
                {
                    result.requestSuccess(count);
                }
                else
                {
                    result.requestSuccess("修改密码失败");
                }
            }
            return result;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("修改密码失败", e);
        }
    }
}
