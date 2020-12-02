#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import ${package}.core.common.JsonResult;
import ${package}.core.common.LocalFileUtil;
import ${package}.core.exception.ServiceException;
import ${package}.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

/**
 * Created by summer on 2020/1/21.
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService
{
    @Value("${symbol_dollar}{file.local.path}")
    private String basePath;

    @Override
    public JsonResult fileUploadLocal(String relativePath, MultipartFile file) throws ServiceException
    {
        try
        {
            JsonResult result = new JsonResult();
            LocalFileUtil fileUtil = new LocalFileUtil();
            String randomFileName = fileUtil.createRandomFileName(file.getOriginalFilename());
            if (basePath.endsWith("/"))
            {
                basePath = basePath.substring(0, basePath.length() - 1);
            }
            if (!relativePath.startsWith(File.separator))
            {
                relativePath = File.separator + relativePath;
            }
            fileUtil.upload(basePath + relativePath, randomFileName, file.getInputStream());
            String filePath = (relativePath.endsWith(File.separator) ? relativePath + randomFileName : relativePath + File.separator + randomFileName);
            return result.requestSuccess(filePath);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("文件上传失败", e);
        }


    }

    @Override
    public byte[] downloadFile(String relativePath) throws ServiceException
    {
        try
        {
            LocalFileUtil fileUtil = new LocalFileUtil();
            if (basePath.endsWith("/"))
            {
                basePath = basePath.substring(0, basePath.length() - 1);
            }
            if (!relativePath.startsWith(File.separator))
            {
                relativePath = File.separator + relativePath;
            }
            return fileUtil.download(basePath + relativePath);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取文件失败", e);
        }
    }

    @Override
    public JsonResult fileUploadLocal(String relativePath, String filename, String base64File) throws ServiceException
    {
        try
        {
            JsonResult result = new JsonResult();
            LocalFileUtil fileUtil = new LocalFileUtil();
            String randomFileName = fileUtil.createRandomFileName(filename);
            String filePath = (relativePath.endsWith(File.separator) ? relativePath + randomFileName : relativePath + File.separator + randomFileName);
            Files.write(Paths.get(basePath + filePath), Base64.getDecoder().decode(base64File), StandardOpenOption.CREATE);
            return result.requestSuccess(filePath);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("文件上传失败", e);
        }


    }
}


