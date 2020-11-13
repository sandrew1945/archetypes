#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;


import ${package}.core.bean.AclUserBean;
import ${package}.core.common.JsonResult;
import ${package}.core.exception.ServiceException;
import ${package}.model.TmRolePO;
import ${package}.model.TmUserPO;
import com.sandrew.bury.bean.PageResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Function    : 
 * @author     : zhao.feng
 * CreateDate  : 2010-11-5
 * @version    :
 */
public interface UserManagerService
{
	/**
	 *  用户管理分页查询
	 * @param userCode
	 * @param userName
	 * @param userStatus
	 * @param limit
	 * @param curPage
	 * @return
	 * @throws ServiceException
	 */
	PageResult<AclUserBean> userManagerPageQuery(String userCode, String userName, Integer userStatus, int limit, int curPage) throws ServiceException;

	/**
	 * 
	 * Function    : 创建用户
	 * LastUpdate  : 2016年9月22日
	 * @param user
	 * @param avatar
	 * @param aclUser
	 * @return
	 * @throws ServiceException
	 */
	JsonResult createUserInfo(TmUserPO user, MultipartFile avatar, AclUserBean aclUser) throws ServiceException;
	
	/**
	 * 
	 * Function    : 删除用户信息
	 * LastUpdate  : 2016年9月22日
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	JsonResult deleteUserInfo(Integer userId, AclUserBean aclUser) throws ServiceException;
	
	/**
	 * 根据用户id查询用户信息
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	TmUserPO findByUserId(Integer userId) throws ServiceException;
	
	/**
	 * 
	 * Function    : 根据用户名获取用户信息
	 * LastUpdate  : 2016年10月25日
	 * @param userCode
	 * @return
	 * @throws ServiceException
	 */
	TmUserPO getUserByCode(String userCode) throws ServiceException;
	
	/**
	 * 修改编辑用户信息
	 * @param user
	 * @param avatar
	 * @param aclUser
	 * @return
	 * @throws ServiceException
	 */
	JsonResult updateUserInfo(TmUserPO user, MultipartFile avatar, AclUserBean aclUser) throws ServiceException;
	
	
	/**
	 * 
	 * Function    : 获取当前用户已经关联的角色
	 * LastUpdate  : 2016年9月22日
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	List<TmRolePO> getRelationRolesByUserId(Integer userId) throws ServiceException;
	
	/**
	 * 
	 * Function    : 删除用户与角色的关联
	 * LastUpdate  : 2016年9月22日
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	JsonResult deleteRoleRelation(Integer userId, Integer roleId) throws ServiceException;
	
	/**
	 * 
	 * Function    : 获取该用户下全部未关联角色
	 * LastUpdate  : 2016年9月22日
	 * @param aclUser
	 * @return
	 * @throws ServiceException
	 */
	List<TmRolePO> getUnRelationRoles(AclUserBean aclUser) throws ServiceException;
	
	/**
	 * 
	 * Function    : 批量添加用户与角色关系
	 * LastUpdate  : 2016年9月22日
	 * @param userId
	 * @param rolesStr
	 * @return
	 * @throws ServiceException
	 */
	JsonResult createRelation(Integer userId, String rolesStr, AclUserBean aclUser) throws ServiceException;

	/**
	 * 清空用户照片
	 * @param userId
	 * @param loginUser
	 * @return
	 * @throws ServiceException
	 */
	JsonResult updateClearAvatar(Integer userId, AclUserBean loginUser) throws ServiceException;

	/**
	 *  获取全部有效用户
	 * @return
	 * @throws ServiceException
     */
	JsonResult getAvailableUserList() throws ServiceException;

	/**
	 *  更新密码
	 * @param userId
	 * @param originPwd
	 * @param newPwd
	 * @return
	 * @throws ServiceException
     */
	JsonResult updatePassword(Integer userId, String originPwd, String newPwd, AclUserBean loginUser) throws ServiceException;
}
