#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * <pre>
 * FILE : RoleManagerServiceImpl.java
 * CLASS : RoleManagerServiceImpl
 *
 * AUTHOR : Liutt
 *
 * FUNCTION : TODO
 *
 *
 * ======================================================================
 * CHANGE HISTORY LOG
 * ----------------------------------------------------------------------
 * MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
 * ----------------------------------------------------------------------
 * 		  |2016年5月30日| Liutt| Created |
 * DESCRIPTION:
 * </pre>
 * <p>
 * ${symbol_dollar}Id: RoleManagerServiceImpl.java,v 0.1 2016年5月30日 上午10:58:34 liutt Exp ${symbol_dollar}
 * <p>
 * ${symbol_dollar}Id: RoleManagerServiceImpl.java,v 0.1 2016年5月30日 上午10:58:34 liutt Exp ${symbol_dollar}
 * <p>
 * ${symbol_dollar}Id: RoleManagerServiceImpl.java,v 0.1 2016年5月30日 上午10:58:34 liutt Exp ${symbol_dollar}
 */
/**
 * ${symbol_dollar}Id: RoleManagerServiceImpl.java,v 0.1 2016年5月30日 上午10:58:34 liutt Exp ${symbol_dollar}
 */

package ${package}.service.impl;


import ${package}.bean.RoleBean;
import ${package}.core.bean.AclUserBean;
import ${package}.core.common.Constants;
import ${package}.core.common.JsonResult;
import ${package}.core.exception.DAOException;
import ${package}.core.exception.ServiceException;
import ${package}.dao.CommonDAO;
import ${package}.dao.RoleManagerDAO;
import ${package}.model.TmFuncFrontPO;
import ${package}.model.TmRolePO;
import ${package}.service.RoleManagerService;
import ${package}.service.util.MenuNode;
import com.sandrew.bury.bean.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


/**
 * Function    : 
 * @author     : liutt
 * CreateDate  : 2016年5月30日
 * @version    :
 */
@Service
@Slf4j
public class RoleManagerServiceImpl implements RoleManagerService
{

    @Resource
    private RoleManagerDAO roleManagerDAO;

    @Resource
    private CommonDAO commonDAO;

    @Override
    public List<TmRolePO> getRoleList() throws ServiceException
    {
        try
        {
            TmRolePO cond = new TmRolePO();
            List<TmRolePO> roleList = commonDAO.select(cond);
            log.debug("roleList : " + roleList);
            return roleList;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取角色列表失败", e);
        }

    }

    /**
     * Function    : 分页查询角色列表
     * LastUpdate  : 2016年5月26日
     * @author     ：liutt
     * @param condition 查询条件
     * @param curPage 分页信息
     */
    @Override
    public PageResult<RoleBean> roleManagerPageQuery(TmRolePO condition, int limit, int curPage) throws ServiceException
    {
        try
        {
            return roleManagerDAO.roleManagerPageQuery(condition.getRoleCode(), condition.getRoleName(), condition.getRoleStatus(), limit, curPage);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("角色列表查询失败", e);
        }
    }

    /**
     * Function    : 创建角色
     * LastUpdate  : 2016年5月30日
     * @author     ：liutt
     * @param role 角色
     * @param aclUser 登录用户
     */
    @Override
    public JsonResult createRole(TmRolePO role, AclUserBean aclUser) throws ServiceException
    {
        JsonResult result = new JsonResult();
        try
        {
            boolean isExits = false;
            // 验证用户代码是否存在
            TmRolePO cond = new TmRolePO();
            cond.setRoleCode(role.getRoleCode());
            cond.setIsDelete(Constants.IF_TYPE_NO);
            List<TmRolePO> list = commonDAO.select(cond);
            isExits = (null != list && list.size() > 0) ? true : false;
            if (!isExits)
            {
                role.setIsDelete(Constants.IF_TYPE_NO);
                role.setCreateBy(aclUser.getUserId());
                role.setCreateDate(new Date());
                result.requestSuccess(commonDAO.insert(role));
            }
            else
            {
                result.requestFailure("角色已经存在");
            }
            return result;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("创建角色失败", e);
        }
    }

    /**
     * Function    :根据角色id查询角色信息
     * LastUpdate  : 2016年5月31日
     * @author     ：liutt
     * @param roleId 角色id
     * @return
     * @throws ServiceException
     */
    @Override
    public TmRolePO findByroleId(Integer roleId) throws ServiceException
    {

        try
        {
            TmRolePO tmRolePO = commonDAO.selectById(new TmRolePO(roleId));
            return tmRolePO;
        }
        catch (DAOException e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("查询角色失败", e);
        }
    }

    /**
     * Function    :修改编辑角色信息
     * LastUpdate  : 2016年5月31日
     * @author     ：liutt
     * @param role
     * @param aclUser
     * @return
     * @throws ServiceException
     */
    @Override
    public JsonResult updateRole(TmRolePO role, AclUserBean aclUser) throws ServiceException
    {
        JsonResult result = new JsonResult();
        try
        {
            TmRolePO cond = new TmRolePO(role.getRoleId());

            role.setUpdateDate(new Date());
            role.setUpdateBy(aclUser.getUserId());
            commonDAO.update(cond, role);
            result.requestSuccess();
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new ServiceException("编辑角色失败", e);
        }

    }

    /**
     * Function    : 删除角色
     * LastUpdate  : 2016年5月31日
     * @author     ：
     * @param roleId 角色id
     * @return
     * @throws ServiceException
     */
    @Override
    public JsonResult deleteRole(Integer roleId, AclUserBean aclUser) throws ServiceException
    {
        try
        {
            JsonResult result = new JsonResult();
            TmRolePO cond = new TmRolePO(roleId);

            TmRolePO value = new TmRolePO();
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

    /**
     *  保存权限
     * @param roleId
     * @param nodes
     * @param loginUser
     * @return
     * @throws ServiceException
     */
    @Override
    public JsonResult savePermission(Integer roleId, List<MenuNode> nodes, AclUserBean loginUser) throws ServiceException
    {
        JsonResult result = new JsonResult();
        try
        {
            // 处理选择的菜单
            Set<MenuNode> premissionSet = new LinkedHashSet<>();
            handleMenuNode(premissionSet, nodes);
            // 删除该角色关联的菜单
            TmFuncFrontPO deleteCond = new TmFuncFrontPO();
            deleteCond.setRoleId(roleId);
            commonDAO.delete(deleteCond);
            // 重新添加该角色菜单
            for (MenuNode menuNode : premissionSet)
            {
                System.out.println("Path : " + menuNode.getPath() + "     Name : " + menuNode.getName() + "   children : " + menuNode.getChildren().size());
                insertMenu(roleId, null, menuNode, loginUser);
            }
            return result.requestSuccess(true);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("添加功能失败", e);
        }
    }

    @Override
    public JsonResult getCheckPermission(Integer roleId) throws ServiceException
    {
        JsonResult result = new JsonResult();
        try
        {
            TmFuncFrontPO cond = new TmFuncFrontPO();
            cond.setRoleId(roleId);
            List<TmFuncFrontPO> functionList = commonDAO.select(cond);
            List<String> checkedPermission = new ArrayList<>();
            for (TmFuncFrontPO funcFront : functionList)
            {
                checkedPermission.add(funcFront.getPath());
            }
            return result.requestSuccess(checkedPermission);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取已选菜单失败", e);
        }
    }

    /**
     *  菜单节点处理
     * @param set
     * @param nodes
     */
    private void handleMenuNode(Set<MenuNode> set, List<MenuNode> nodes)
    {
        for (MenuNode node : nodes)
        {
            if (!set.contains(node))
            {
                set.add(node);
            }
            else
            {
                set.remove(node);
            }
            if (null != node.getChildren() & node.getChildren().size() > 0)
            {
                handleMenuNode(set, node.getChildren());
            }
        }
    }

    private void insertMenu(Integer roleId, TmFuncFrontPO father, MenuNode node, AclUserBean loginUser) throws ServiceException
    {
        try
        {
            TmFuncFrontPO function = new TmFuncFrontPO();
            function.setRoleId(roleId);
            function.setPath(node.getPath());
            function.setName(node.getName());
            function.setTitle(node.getMeta().get("title"));
            function.setFile(node.getMeta().get("file"));
            function.setIcon(node.getMeta().get("icon"));
            function.setRedirect(node.getRedirect());
            if (null != father)
            {
                function.setFatherId(father.getFuncId());
            }
            function.setCreateBy(loginUser.getUserId());
            function.setCreateDate(new Date());
            commonDAO.insert(function);
            if (null != node.getChildren() && node.getChildren().size() > 0)
            {
                for (MenuNode child : node.getChildren())
                {
                    insertMenu(roleId, function, child, loginUser);
                }
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("维护功能失败", e);
        }
    }
}
