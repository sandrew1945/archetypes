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
 * <p>
 * ${symbol_dollar}Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp ${symbol_dollar}
 * <p>
 * ${symbol_dollar}Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp ${symbol_dollar}
 * <p>
 * ${symbol_dollar}Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp ${symbol_dollar}
 * <p>
 * ${symbol_dollar}Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp ${symbol_dollar}
 */
/**
 * ${symbol_dollar}Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp ${symbol_dollar}
 */

package ${package}.service.impl;


import ${package}.bean.RoleTreeNode;
import ${package}.bean.UserInfo;
import ${package}.core.bean.AclUserBean;
import ${package}.core.common.Constants;
import ${package}.core.common.LoginResult;
import ${package}.core.exception.ServiceException;
import ${package}.core.shiro.MyUsernamePasswordToken;
import ${package}.dao.CommonDAO;
import ${package}.dao.LoginDAO;
import ${package}.model.TmFuncFrontPO;
import ${package}.model.TmRolePO;
import ${package}.model.TmUserPO;
import ${package}.service.LoginService;
import ${package}.service.UserManagerService;
import ${package}.service.util.MenuNode;
import ${package}.service.util.TreeMaker;
import ${package}.service.util.TreeNode;
import ${package}.service.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2014年5月3日
 * @version    :
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService
{
    @Resource
    private LoginDAO loginDAO;

    @Resource
    private UserManagerService userManagerService;

    @Resource
    private CommonDAO commonDAO;


    @Override
    public AclUserBean login(TmUserPO user) throws ServiceException
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            MyUsernamePasswordToken token = new MyUsernamePasswordToken(user.getUserCode(), user.getPassword());

            if (!subject.isAuthenticated())
            {
                subject.login(token);
                TmUserPO databaseUser = userManagerService.getUserByCode(user.getUserCode());
                AclUserBean loginUser = new AclUserBean();
                loginUser.setUserId(databaseUser.getUserId());
                loginUser.setToken(subject.getSession().getId().toString());
                subject.getSession().setAttribute(Constants.LOGIN_USER, loginUser);
                return loginUser;
            }
            else
            {
                AclUserBean loginUser = (AclUserBean) subject.getSession().getAttribute(Constants.LOGIN_USER);
                return loginUser;
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("登录失败", e);
        }
    }

    @Override
    public AclUserBean userInfo(AclUserBean loginUser) throws ServiceException
    {
        try
        {
            TmUserPO user = userManagerService.findByUserId(loginUser.getUserId());
            loginUser.setUserCode(user.getUserCode());
            loginUser.setUserName(user.getUserName());
            loginUser.setSex(user.getSex());
            loginUser.setUserType(user.getUserType());
            loginUser.setPhone(user.getPhone());
            loginUser.setMobile(user.getMobile());
            loginUser.setEmail(user.getEmail());
            loginUser.setAvatarPath(user.getAvatar());
            List<TmRolePO> roleList = userManagerService.getRelationRolesByUserId(loginUser.getUserId());
            loginUser.setRoleList(roleList);
            return loginUser;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取用户信息失败", e);
        }
    }

    @Override
    public List<MenuNode> getMenuByRole(Integer roleId) throws ServiceException
    {
        try
        {
            TmFuncFrontPO cond = new TmFuncFrontPO();
            cond.setRoleId(roleId);
            List<TmFuncFrontPO> functionList = commonDAO.select(cond);
            List<MenuNode> menuList = new ArrayList<>();
            Map<Integer, MenuNode> cache = new HashMap<>();
            for (TmFuncFrontPO funcFront : functionList)
            {
                MenuNode menu = new MenuNode();
                menu.setPath(funcFront.getPath());
                menu.setName(funcFront.getName());
                menu.setComponent(funcFront.getFile());
                menu.setRedirect(funcFront.getRedirect());
                Map<String, String> meta = new HashMap<>();
                meta.put("title", funcFront.getTitle());
                meta.put("icon", funcFront.getIcon());
                menu.setMeta(meta);
                cache.put(funcFront.getFuncId(), menu);
                if (null != funcFront.getFatherId())
                {
                    // 如果存在父节点，那么将子节点添加到父节点，并且不添加到menuList中
                    MenuNode father = cache.get(funcFront.getFatherId());
                    father.addChildren(menu);
                    continue;
                }
                menuList.add(menu);
            }
            return menuList;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取系统菜单失败", e);
        }
    }

    /**
     * Function    : 用户角色选择处理器
     * LastUpdate  : 2014年6月4日
     * @param userCode
     * @return
     */
    public LoginResult postRoleHandler(String userCode) throws ServiceException
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            TmUserPO sessionUser = userManagerService.getUserByCode(userCode);
            AclUserBean aclUser = new AclUserBean();
            aclUser.setUserId(sessionUser.getUserId());
            aclUser.setUserCode(sessionUser.getUserCode());
            aclUser.setUserName(sessionUser.getUserName());
            aclUser.setUserType(sessionUser.getUserType());
            aclUser.setAvatarPath(sessionUser.getAvatar());
            session.setAttribute(Constants.LOGIN_USER, aclUser);

            // 如果已经选择完角色，那么直接进入系统首页
            if (UserUtil.hasRole(aclUser))
            {
                return new LoginResult("index", false);
            }
            // 判断用户可以选择的角色，如果只有一个，那么不进行选择，直接跳入系统首页
            List<UserInfo> list = loginDAO.selectRoleByUserCode(userCode);
            if (null != list && list.size() > 1)
            {
                // 多角色
                return new LoginResult("login/postRoleChoice", false);
            }
            if (null != list && list.size() < 1)
            {
                // 无角色
                return new LoginResult("redirect:error403", true);
            }

            // 角色信息放入SESSION中
            UserInfo tempAcl = list.get(0);
            aclUser.setRoleId(tempAcl.getRoleId());
            aclUser.setRoleName(tempAcl.getRoleName());
            aclUser.setRoleCode(tempAcl.getRoleCode());
            session.setAttribute(Constants.LOGIN_USER, aclUser);
            return new LoginResult("index", false);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("角色选择失败", e);
        }

    }

    /**
     * 	根据用户ID获取角色选择树
     */
    @Override
    public List<RoleTreeNode> choiceRoleTree(Integer userId) throws ServiceException
    {
        try
        {
            return loginDAO.selectRoleForChoice(userId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new ServiceException("角色选择树获取失败", e);
        }
    }

    @Override
    public String generateMenu(Integer roleId) throws ServiceException
    {
        String treeStr = null;
        try
        {
            treeStr = TreeMaker.buildTree(loginDAO.getMenuInfo(roleId));
        }
        catch (Exception e)
        {
            e.printStackTrace();

            throw new ServiceException("获取菜单失败", e);
        }
        return treeStr;
    }


    @Override
    public List<TreeNode> getMenuTreeNode(Integer roleId) throws ServiceException
    {
        List<TreeNode> treeNodes;
        try
        {
            treeNodes = TreeMaker.handleNode(loginDAO.getMenuInfo(roleId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new ServiceException("获取菜单失败", e);
        }
        return treeNodes;
    }

    @Override
    public String showIndex(Integer roleId) throws ServiceException
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            AclUserBean aclUser = (AclUserBean) session.getAttribute(Constants.LOGIN_USER);
            // 通过roleId获取角色信息
            TmRolePO rolePO = commonDAO.selectById(new TmRolePO(roleId));
            aclUser.setRoleId(roleId);
            aclUser.setRoleCode(rolePO.getRoleCode());
            aclUser.setRoleName(rolePO.getRoleName());
            session.setAttribute(Constants.LOGIN_USER, aclUser);
            return "index";
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("选择岗位、角色失败", e);
        }
    }

    @Override
    public void logout() throws ServiceException
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("登出系统失败", e);
        }
    }
}
