#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.common;

import ${package}.core.captcha.RandCodeImageUtils;
import ${package}.core.common.JsonResult;
import ${package}.core.exception.ActionException;
import ${package}.core.exception.JsonException;
import ${package}.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@Controller
@RequestMapping(value = "/generate")
public class GenerateImageController {


	@Resource
	CommonService commonService;

	private final static String AVATAR_PATH = "/avatar";

	/**
	 * 生成验证码图片io流
	 */
	@RequestMapping(value = "image", method = RequestMethod.GET)
	public void generateImage(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException
	{
		RandCodeImageUtils.generateImage(response, request);
	}

	@RequestMapping("uploadImg")
	public
	@ResponseBody
	JsonResult uploadImage(String filePath, MultipartFile file) throws JsonException
	{
		try
		{
			return commonService.fileUploadLocal(AVATAR_PATH, file);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException("图片上传失败", e);
		}

	}

	/**
	 * 查看图片
	 *
	 * @param filePath 相对路径
	 * @param response
	 * @throws ActionException
	 */
	@RequestMapping("loadImage")
	public void loadImage(String filePath, HttpServletResponse response) throws ActionException
	{
		OutputStream outputStream = null;
		try
		{
			// 文件服务器下载附件
			byte[] bytes = commonService.downloadFile(filePath);
			outputStream = response.getOutputStream();
			response.setContentType("application/octet-stream;charset=UTF-8");
			FileCopyUtils.copy(bytes, outputStream);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new ActionException("查看图片失败", e);
		}
	}

	@RequestMapping(value = "streamingDownload", method = RequestMethod.GET)
	public StreamingResponseBody streamingDownload(HttpServletResponse response) throws IOException
	{

		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=sample2.zip");
		InputStream inputStream = new FileInputStream(new File("/Users/summer/Desktop/platform922_ccjk_win32.exe"));
		return outputStream -> {
			int nRead;
			byte[] data = new byte[1024];
			while ((nRead = inputStream.read(data, 0, data.length)) != -1)
			{
				outputStream.write(data, 0, nRead);
			}
		};

	}
}
