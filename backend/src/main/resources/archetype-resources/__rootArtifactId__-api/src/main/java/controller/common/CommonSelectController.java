#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.common;

import ${package}.core.controller.BaseController;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * Function    : 公共的下拉框生成
 * @author     : Administrator
 * CreateDate  : 2016年5月27日
 * @version    :
 */
@Controller
@Log4j2
@RequestMapping(value = "/select")
public class CommonSelectController extends BaseController
{


}
