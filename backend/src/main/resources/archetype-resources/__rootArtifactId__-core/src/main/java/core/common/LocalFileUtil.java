#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**********************************************************************
 * <pre> FILE : LocalFileUtil.java CLASS : LocalFileUtil AUTHOR : SuMMeR FUNCTION : TODO
 * ====================================================================== CHANGE HISTORY LOG
 * ---------------------------------------------------------------------- MOD. NO.| DATE | NAME |
 * REASON | CHANGE REQ. ----------------------------------------------------------------------
 * |2010-11-8| SuMMeR| Created | DESCRIPTION: </pre>
 ***********************************************************************/
/**
 * ${symbol_dollar}Id: LocalFileUtil.java,v 1.8 2010/12/22 05:48:01 bin.wei Exp ${symbol_dollar}
 */

package ${package}.core.common;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Function    : 文件上传、下载工具类-本地实现
 * @author     : SuMMeR
 * CreateDate  : 2010-11-8
 * @version    :
 */
public class LocalFileUtil
{

	private static final String PROPERTIES_FILE_NAME = "upload";

	/* (non-Javadoc)
	 * @see com.autosys.pcc.util.FileUtil${symbol_pound}download()
	 */
	public boolean download()
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.autosys.pcc.util.FileUtil${symbol_pound}upload(java.lang.String, java.io.InputStream)
	 */
	public boolean upload(String filePath, InputStream in) throws Exception
	{
		BufferedOutputStream bos = null;
		try
		{
			// 检测文件目录是否存在,如不存在,创建目录
			File dir = new File(this.getDirectory(filePath));
			if (!dir.exists())
			{
				dir.mkdirs();
			}
			// 创建目标文件
			File file = new File(filePath);
			// 创建输出流
			bos = new BufferedOutputStream(new FileOutputStream(file));
			// 缓冲读取要上传文件,并写到本地磁盘
			byte[] tmp = new byte[4 * 1024];
			int len = 0;
			while ((len = in.read(tmp)) >= 0)
			{
				bos.write(tmp, 0, len);
			}
			return true;
		}
		catch (Exception e)
		{
			throw new Exception("write file error", e);
		}
		finally
		{
			// 关闭输出流
			if (null != bos)
			{
				try
				{
					bos.close();
				}
				catch (IOException e)
				{
					throw new Exception("close file error", e);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.autosys.pcc.util.FileUtil${symbol_pound}upload(java.lang.String, java.lang.String, java.io.InputStream)
	 */
	public boolean upload(String filePath, String fileName, InputStream in) throws Exception
	{
		return this.upload(filePath + File.separator + fileName, in);
	}

	/*
	 * (non-Javadoc)
	 * @see com.autosys.pcc.util.FileUtil${symbol_pound}download(java.lang.String)
	 */
	public byte[] download(String filePath) throws Exception
	{
		ByteArrayOutputStream bos = null;
		BufferedInputStream bis = null;
		try
		{
			// 获取要下载的文件
			File file = new File(filePath);
			bis = new BufferedInputStream(new FileInputStream(file));

			// 创建输出流
			bos = new ByteArrayOutputStream();
			byte[] tmp = new byte[4 * 1024];
			int len = 0;
			// 将文件写到输出流
			while ((len = bis.read(tmp)) >= 0)
			{
				bos.write(tmp, 0, len);
			}
			return bos.toByteArray();
		}
		catch (Exception e)
		{
			throw new Exception("get file error", e);
		}
		finally
		{
			if (null != bis)
			{
				bis.close();
			}
			if (null != bos)
			{
				try
				{
					bos.close();
				}
				catch (IOException e)
				{
					throw new Exception("close file error", e);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.autosys.pcc.util.FileUtil${symbol_pound}download(java.lang.String, java.lang.String)
	 */
	public byte[] download(String filePath, String fileName) throws Exception
	{
		return this.download(filePath + File.separator + fileName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.autosys.pcc.util.FileUtil${symbol_pound}getUploadPath()
	 */
	public String getUploadPath() throws Exception
	{
		try
		{
			// 读取系统设定的文件上传路径
			ResourceBundle bundle = ResourceBundle.getBundle(PROPERTIES_FILE_NAME);
			String path = bundle.getString("uploadPath");
			// 加上年月文件夹
			path = path + DateTimeUtil.getYearMonthStr() + File.separator;
			return path;
		}
		catch (Exception e)
		{
			throw new Exception("资源文件读取失败", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.autosys.pcc.util.FileUtil${symbol_pound}createRandomFileName()
	 */
	public String createRandomFileName(String fileuploadFileName)
	{
		String nowTimeStr = ""; //保存当前时间 
		String extName = ""; //扩展名
		String newFileName = ""; //保存的新文件名
		SimpleDateFormat sDateFormat;
		Random r = new Random();
		int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; //获取随机数 
		sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //时间格式化的格式 
		nowTimeStr = sDateFormat.format(new Date()); //当前时间 
		if (fileuploadFileName.lastIndexOf(".") >= 0)
		{
			extName = fileuploadFileName.substring(fileuploadFileName.lastIndexOf("."));
		}
		newFileName = nowTimeStr + rannum + extName;
		return newFileName;
	}

	/**
	 * 
	 * Function    : 根据文件全部路径获取所在文件夹
	 * LastUpdate  : 2010-11-8
	 * @param realPath
	 * @return
	 */
	private String getDirectory(String realPath)
	{
		String directory = realPath.substring(0, realPath.lastIndexOf(File.separator));
		return directory;
	}

}
