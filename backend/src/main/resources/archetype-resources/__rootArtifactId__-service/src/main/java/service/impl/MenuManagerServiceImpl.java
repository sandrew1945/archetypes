#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;
import ${package}.core.bean.AclUserBean;
import ${package}.core.common.Constants;
import ${package}.core.exception.ServiceException;
import ${package}.dao.CommonDAO;
import ${package}.dao.MenuManagerDAO;
import ${package}.model.TmFunctionPO;
import ${package}.service.MenuManagerService;
import ${package}.service.util.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuManagerServiceImpl implements MenuManagerService
{
    @Resource
    private MenuManagerDAO menuManagerDAO;

    @Resource
    private CommonDAO commonDAO;

    @Override
    public List<TreeNode> getMenuTree() throws ServiceException
    {
        try
        {
            //            List<MenuNode>
            List<TmFunctionPO> list = menuManagerDAO.getMenuList();
            // 遍历全部菜单，将节点转换为MenuNode,并将子节点放入父节点的children中
            Map<Integer, TreeNode> cache = new HashMap<>();
            List<TreeNode> menuNodeList = list.stream().map(function -> {
                TreeNode node = new TreeNode();
                Map<String, String> meta = new HashMap<>();
                node.setFunctionId(function.getFunctionId());
                node.setPath(function.getPath());
                node.setName(function.getTitle());
                node.setIcon(function.getIcon());
                node.setFuncOrder(function.getFuncOrder());
                cache.put(function.getFunctionId(), node);
                if (cache.containsKey(function.getFatherId()))
                {
                    cache.get(function.getFatherId()).addChildren(node);
                    return null;
                }
                return node;
            }).filter(menuNode -> null != menuNode).collect(Collectors.toList());
            return menuNodeList;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取系统菜单失败", e);
        }
    }

    @Override
    public int updateMenu(TreeNode treeNode, AclUserBean loginUser) throws ServiceException
    {
        try
        {
            TmFunctionPO cond = new TmFunctionPO();
            cond.setFunctionId(treeNode.getFunctionId());

            TmFunctionPO value = new TmFunctionPO();
            value.setPath(treeNode.getPath());
            value.setTitle(treeNode.getName());
            value.setIcon(treeNode.getIcon());
            value.setFuncOrder(treeNode.getFuncOrder());
            value.setUpdateBy(loginUser.getUserId());
            value.setUpdateDate(new Date());
            return commonDAO.update(cond, value);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("更新系统菜单失败", e);
        }
    }

    @Override
    public int deleteMenuById(Integer functionId, AclUserBean loginUser) throws ServiceException
    {
        try
        {
            TmFunctionPO cond = new TmFunctionPO();
            cond.setFunctionId(functionId);

            TmFunctionPO value = new TmFunctionPO();
            value.setIsDelete(Constants.IF_TYPE_YES);
            value.setUpdateBy(loginUser.getUserId());
            value.setUpdateDate(new Date());
            int count = commonDAO.update(cond, value);
            if (count > 0)
            {
                // 删除该菜单下的子菜单
                TmFunctionPO childCond = new TmFunctionPO();
                childCond.setFatherId(functionId);
                childCond.setIsDelete(Constants.IF_TYPE_NO);
                List<TmFunctionPO> children = commonDAO.select(childCond);
                children.stream().forEach(child -> {
                    try
                    {
                        deleteMenuById(child.getFunctionId(), loginUser);
                    }
                    catch (ServiceException e)
                    {
                        log.error(e.getMessage(), e);
                    }
                });
            }
            return count;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("删除系统菜单失败", e);
        }
    }

    @Override
    public int createMenu(TreeNode treeNode, Integer fatherId, AclUserBean loginUser) throws ServiceException
    {
        try
        {
            TmFunctionPO function = new TmFunctionPO();
            function.setPath(treeNode.getPath());
            function.setTitle(treeNode.getName());
            function.setIcon(treeNode.getIcon());
            function.setFuncOrder(treeNode.getFuncOrder());
            function.setFatherId(fatherId);
            function.setIsDelete(Constants.IF_TYPE_NO);
            function.setCreateBy(loginUser.getUserId());
            function.setCreateDate(new Date());
            return commonDAO.insert(function);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("创建系统菜单失败", e);
        }
    }
}
