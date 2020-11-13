#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.exception.handler;


import ${package}.core.common.JsonResult;
import ${package}.core.exception.ActionException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Function    : 
 * @author     : Administrator
 * CreateDate  : 2016年5月31日
 * @version    :
 */
@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver
{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerExceptionResolver${symbol_pound}resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
	{
		ModelAndView mv = new ModelAndView();
		String referer = request.getHeader("Referer");
		try
		{
			if(handler instanceof HandlerMethod)
			{
				Method method = ((HandlerMethod)handler).getMethod();
				ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method, ResponseBody.class);
				if (responseBodyAnn != null)
				{
					// 处理ajax请求异常
					JsonResult result = new JsonResult();
					result.requestFailure(exception.getMessage());
					response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType  
		            response.setCharacterEncoding("UTF-8"); //避免乱码  
					response.getWriter().write(result.toString());
				}
				if (exception instanceof ActionException)
				{
					// 处理跳转请求异常
					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("referer", referer);
					parameter.put("errorMsg", exception.getMessage());
					return new ModelAndView("error/errorpage_500", parameter);
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return mv;
	}

}
