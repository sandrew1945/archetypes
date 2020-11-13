#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;


import ${package}.bean.RoleTreeNode;
import ${package}.core.bean.AclUserBean;
import ${package}.core.common.LoginResult;
import ${package}.core.exception.ServiceException;
import ${package}.model.TmUserPO;
import ${package}.service.util.MenuNode;
import ${package}.service.util.TreeNode;

import java.util.List;


/**
 * Function    : 
 * @author     : zhao.feng
 * CreateDate  : 2010-11-5
 * @version    :
 */
public interface LoginService
{

	/**
	 *  登录
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	AclUserBean login(TmUserPO user) throws ServiceException;

	/**
	 *  获取用户信息
	 * @return
	 * @throws ServiceException
	 */
	AclUserBean userInfo(AclUserBean loginUser) throws ServiceException;

	/**
	 *
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	List<MenuNode> getMenuByRole(Integer roleId) throws ServiceException;

	/**
	 *
	 * Function    : 处理用户的角色及Session信息
	 * LastUpdate  : 2017年5月24日
	 * @param userCode
	 * @return
	 * @throws ServiceException
	 */
	LoginResult postRoleHandler(String userCode) throws ServiceException;

	/**
	 *
	 * Function    : 根据用户ID获取角色选择树
	 * LastUpdate  : 2016年4月16日
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	List<RoleTreeNode> choiceRoleTree(Integer userId) throws ServiceException;

	/**
	 *
	 * Function    :
	 * LastUpdate  : 2016年4月16日
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	String generateMenu(Integer roleId) throws ServiceException;


	/**
	 * 获取菜单树
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	List<TreeNode> getMenuTreeNode(Integer roleId) throws ServiceException;
	/**
	 *
	 * Function    : 用户选择完角色进入主界面
	 * LastUpdate  : 2016年4月16日
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	String showIndex(Integer roleId) throws ServiceException;

	/**
	 *  登出
	 * @throws ServiceException
	 */
	void logout() throws ServiceException;
	
}
