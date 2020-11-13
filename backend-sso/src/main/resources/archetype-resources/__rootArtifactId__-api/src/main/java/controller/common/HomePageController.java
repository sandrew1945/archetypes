#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.common;


import ${package}.core.controller.BaseController;
import ${package}.core.exception.ActionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * Function    : 系统首页处理器
 * @author     : Administrator
 * CreateDate  : 2016年5月27日
 * @version    :
 */
@Controller
@RequestMapping(value = "/homepage")
public class HomePageController extends BaseController
{
//	@Resource
//	StesHomePageService stesHomePageService;

	/**
	 * 
	 * Function    : 根据用户获取可选岗位树
	 * LastUpdate  : 2014年5月30日
	 * @return
	 * @throws ActionException
	 */
	@RequestMapping(value = "/showHomePage")
	public String homePage(Model model) throws ActionException
	{
		try
		{
			return "homepage/homepage";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ActionException(e.getMessage(), e);
		}
	}
}
