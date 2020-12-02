#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;


import ${package}.bean.RoleBean;
import ${package}.core.bean.AclUserBean;
import ${package}.core.common.JsonResult;
import ${package}.core.exception.ServiceException;
import ${package}.model.TmRolePO;
import ${package}.service.util.MenuNode;
import ${groupId}.bury.bean.PageResult;

import java.util.List;


/**
 * Function    : 角色管理处理service
 * @author     : liutt
 * CreateDate  : 2016-5-30
 * @version    :
 */
public interface RoleManagerService
{
	/**
	 * 
	 * Function    : 获取全部角色信息
	 * LastUpdate  : 2016年5月27日
	 * @return
	 */
	List<TmRolePO> getRoleList() throws ServiceException;
	
	/**
	 * Function    : 分页查询角色列表
	 * LastUpdate  : 2016年5月26日
	 * @author     ：liutt
	 * @param condition 查询条件
	 * @param curPage 分页信息
	 */
	PageResult<RoleBean> roleManagerPageQuery(TmRolePO condition, int limit, int curPage) throws ServiceException;
	

	/**
	 * Function    : 创建角色
	 * LastUpdate  : 2016年5月30日
	 * @author     ：liutt
	 * @param role 角色
	 * @param aclUser 登录用户
	 */
	JsonResult createRole(TmRolePO role, AclUserBean aclUser) throws ServiceException;
	
	/**
	 * Function    :根据角色id查询角色信息
	 * LastUpdate  : 2016年5月31日
	 * @author     ：liutt
	 * @param roleId 角色id
	 * @return
	 * @throws ServiceException
	 */
	TmRolePO findByroleId(Integer roleId) throws ServiceException;
	
	/**
	 * Function    :修改编辑角色信息
	 * LastUpdate  : 2016年5月31日
	 * @author     ：liutt
	 * @param role
	 * @param aclUser
	 * @return
	 * @throws ServiceException
	 */
	JsonResult updateRole(TmRolePO role, AclUserBean aclUser) throws ServiceException;
	

	/**
	 * Function    : 删除角色
	 * LastUpdate  : 2016年5月31日
	 * @author     ：liutt
	 * @param roleId 角色id
	 * @return
	 * @throws ServiceException
	 */
	JsonResult deleteRole(Integer roleId, AclUserBean aclUser) throws ServiceException;


	/**
	 *  保存权限
	 * @param roleId
	 * @param nodes
	 * @param loginUser
	 * @return
	 * @throws ServiceException
     */
	JsonResult savePermission(Integer roleId, List<MenuNode> nodes, AclUserBean loginUser) throws ServiceException;

	/**
	 *  保存角色下的菜单(quasar)
	 * @param roleId
	 * @param functionIds
	 * @param loginUser
	 * @return
	 * @throws ServiceException
	 */
	JsonResult saveSelectedFunc(Integer roleId, List<Integer> functionIds, AclUserBean loginUser) throws ServiceException;

	/**
	 * 	获取已选择菜单
	 * @param roleId
	 * @return
	 * @throws ServiceException
     */
	JsonResult getCheckPermission(Integer roleId) throws ServiceException;
}
