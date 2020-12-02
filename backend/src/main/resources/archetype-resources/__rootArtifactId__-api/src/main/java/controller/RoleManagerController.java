#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**********************************************************************
* <pre>
* FILE : RoleManagerController.java
* CLASS : RoleManagerController
*
* AUTHOR : Liutt
*
* FUNCTION : TODO
*
*
*======================================================================
* CHANGE HISTORY LOG
*----------------------------------------------------------------------
* MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
*----------------------------------------------------------------------
* 		  |2016年5月30日| Liutt| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* ${symbol_dollar}Id: RoleManagerController.java,v 0.1 2016年5月30日 上午10:58:34 liutt Exp ${symbol_dollar}
*/
package ${package}.controller;


import ${package}.bean.FunctionsParam;
import ${package}.bean.PermissionParam;
import ${package}.bean.RoleBean;
import ${package}.core.common.JsonResult;
import ${package}.core.controller.BaseController;
import ${package}.core.exception.JsonException;
import ${package}.core.exception.ServiceException;
import ${package}.model.TmRolePO;
import ${package}.service.RoleManagerService;
import ${groupId}.bury.bean.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * Function    : 
 * @author     : liutt
 * CreateDate  : 2016年5月30日
 * @version    :
 */
@Slf4j
@Controller
@RequestMapping("/rolemanager")
public class RoleManagerController extends BaseController
{
	@Resource
	private RoleManagerService roleManagerService;//角色处理的service


	@PostMapping(value = "/roleManagerPageQuery")
	public @ResponseBody
	PageResult<RoleBean> userManagerPageQuery(@RequestParam(required = false) String roleCode, @RequestParam(required = false) String roleName, @RequestParam(required = false) Integer roleStatus, int limit, int curPage) throws JsonException
	{
		try
		{
			TmRolePO condition = new TmRolePO();
			condition.setRoleCode(roleCode);
			condition.setRoleName(roleName);
			condition.setRoleStatus(roleStatus);
			return roleManagerService.roleManagerPageQuery(condition, limit, curPage);
		}
		catch (Exception e)
		{
			throw new JsonException(e.getMessage(), e);
		}

	}

	@GetMapping("getRoleInfoById")
	public @ResponseBody
	JsonResult getRoleInfoById(Integer roleId) throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			TmRolePO role = roleManagerService.findByroleId(roleId);
			return result.requestSuccess(role);
		}
		catch (ServiceException e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException("查询角色失败", e);
		}
	}

	@PostMapping(value = "/createRole")
	public @ResponseBody
	JsonResult createRole(TmRolePO user) throws JsonException
	{
		try
		{
			return roleManagerService.createRole(user, getLoginUser());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new JsonException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/updateRole")
	public @ResponseBody
	JsonResult updateRole(TmRolePO role) throws JsonException
	{
		try
		{
			return roleManagerService.updateRole(role, getLoginUser());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new JsonException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/deleteRole")
	public @ResponseBody
	JsonResult deleteRole(Integer roleId) throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			//删除角色的时候 需要判断 该角色是否分配给其他人 如果未分配咋可以删除 如果已分配 则不可以删除
			return roleManagerService.deleteRole(roleId, getLoginUser());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@PostMapping("savePermission")
	public @ResponseBody JsonResult savePermission(@RequestBody PermissionParam parameter) throws JsonException
	{
		try
		{
			return roleManagerService.savePermission(parameter.getRoleId(), parameter.getNode(), getLoginUser());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException("保存权限失败", e);
		}
	}

	/**
	 *  保存角色权限， 用于quasar
	 * @param parameter
	 * @return
	 * @throws JsonException
	 */
	@PostMapping("saveSelectedFunc")
	public @ResponseBody JsonResult saveSelectedFunc(@RequestBody FunctionsParam parameter) throws JsonException
	{
		try
		{
			return roleManagerService.saveSelectedFunc(parameter.getRoleId(), parameter.getFunctionIds(), getLoginUser());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException("保存权限失败", e);
		}
	}

	@GetMapping("getCheckedPremission")
	public @ResponseBody JsonResult getCheckedPremission(Integer roleId) throws JsonException
	{
		try
		{
			return roleManagerService.getCheckPermission(roleId);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException("保存权限失败", e);
		}
	}
}
