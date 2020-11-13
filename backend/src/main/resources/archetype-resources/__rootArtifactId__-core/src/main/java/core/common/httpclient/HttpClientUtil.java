#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.common.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil
{

    // utf-8字符编码
    public static final String CHARSET_UTF_8 = "UTF-8";

    // HTTP内容类型。
    public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";

    // HTTP内容类型。相当于form表单的形式，提交数据
    public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";

    // HTTP内容类型。相当于form表单的形式，提交数据
    public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";


    // 连接管理器
    private static PoolingHttpClientConnectionManager pool;

    // 请求配置
    private static RequestConfig requestConfig;

    static
    {

        try
        {
            //connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //System.out.println("初始化HttpClientTest~~~开始");
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
            // 配置同时支持 HTTP 和 HTPPS
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
            // 初始化连接管理器
            pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
            pool.setMaxTotal(200);
            // 设置最大路由
            pool.setDefaultMaxPerRoute(2);
            // 根据默认超时限制初始化requestConfig
            int socketTimeout = 5000;
            int connectTimeout = 5000;
            int connectionRequestTimeout = 5000;
            requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();

            //System.out.println("初始化HttpClientTest~~~结束");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (KeyStoreException e)
        {
            e.printStackTrace();
        }
        catch (KeyManagementException e)
        {
            e.printStackTrace();
        }


        // 设置请求超时时间
        requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).setConnectionRequestTimeout(50000).build();
    }

    public static CloseableHttpClient getHttpClient()
    {

        CloseableHttpClient httpClient = HttpClients.custom()
                // 设置连接池管理
                .setConnectionManager(pool)
                // 设置请求配置
                .setDefaultRequestConfig(requestConfig)
                // 设置重试次数
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).build();
        return httpClient;
    }

    /**
     * 发送Post请求
     *
     * @param httpPost
     * @return
     */
    private static HttpResponse sendHttpPost(HttpPost httpPost, Map<String, String> header, String charset)
    {
        HttpResponse httpResponse = new HttpResponse();
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        // 响应内容
        String responseContent = null;
        try
        {
            // 创建默认的httpClient实例.
            httpClient = getHttpClient();
            // 配置请求信息
            httpPost.setConfig(requestConfig);
            if (null != header)
            {
                for (String key : header.keySet())
                {
                    httpPost.addHeader(key, header.get(key));
                }
            }

            // 执行请求
            response = httpClient.execute(httpPost);
            // 得到响应实例
            HttpEntity entity = response.getEntity();

            // 可以获得响应头
            // Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
            // for (Header header : headers) {
            // System.out.println(header.getName());
            // }
            // 判断响应状态
//            if (response.getStatusLine().getStatusCode() >= 300)
//            {
//                throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
//            }
            httpResponse.setReturnCode(response.getStatusLine().getStatusCode());

            if (null == charset || "".equals(charset))
            {
                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
            }
            else
            {
                responseContent = EntityUtils.toString(entity, charset);
            }
            EntityUtils.consume(entity);
            httpResponse.setReturnContent(responseContent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // 释放资源
                if (response != null)
                {
                    response.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return httpResponse;
    }

    /**
     * 发送Post请求(body)
     *
     * @param httpPost
     * @return
     */
    private static HttpResponse sendHttpPost(HttpPost httpPost, Object body, Map<String, String> header, String charset)
    {
        HttpResponse httpResponse = new HttpResponse();
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        // 响应内容
        String responseContent = null;
        try
        {
            // 创建默认的httpClient实例.
            httpClient = getHttpClient();
            // 配置请求信息
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            if (null != header)
            {
                for (String key : header.keySet())
                {
                    httpPost.addHeader(key, header.get(key));
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
            // 解决中文乱码问题
            StringEntity stringEntity = new StringEntity(jsonString, charset);
            stringEntity.setContentEncoding(charset);
            httpPost.setEntity(stringEntity);

            // 执行请求
            response = httpClient.execute(httpPost);
            // 得到响应实例
            HttpEntity entity = response.getEntity();

            httpResponse.setReturnCode(response.getStatusLine().getStatusCode());

            if (null == charset || "".equals(charset))
            {
                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
            }
            else
            {
                responseContent = EntityUtils.toString(entity, charset);
            }
            EntityUtils.consume(entity);
            httpResponse.setReturnContent(responseContent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // 释放资源
                if (response != null)
                {
                    response.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return httpResponse;
    }

    /**
     * 发送Get请求
     *
     * @param httpGet
     * @return
     */
    private static HttpResponse sendHttpGet(HttpGet httpGet, Map<String, String> header, String charset)
    {
        HttpResponse httpResponse = new HttpResponse();
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        // 响应内容
        String responseContent = null;
        try
        {
            // 创建默认的httpClient实例.
            httpClient = getHttpClient();

            // 配置请求信息
            httpGet.setConfig(requestConfig);
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
            if (null != header)
            {
                for (String key : header.keySet())
                {
                    httpGet.addHeader(key, header.get(key));
                }
            }

            // 执行请求
            response = httpClient.execute(httpGet);
            // 得到响应实例
            HttpEntity entity = response.getEntity();

            // 可以获得响应头
            // Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
            // for (Header header : headers) {
            // System.out.println(header.getName());
            // }

            // 得到响应类型
            // System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());

            // 判断响应状态
//            if (response.getStatusLine().getStatusCode() >= 300)
//            {
//                throw new Exception("HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
//            }
            httpResponse.setReturnCode(response.getStatusLine().getStatusCode());

            if (null == charset || "".equals(charset))
            {
                responseContent = EntityUtils.toString(entity, charset);
            }
            else
            {
                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
            }
            EntityUtils.consume(entity);
            httpResponse.setReturnContent(responseContent);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // 释放资源
                if (response != null)
                {
                    response.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return httpResponse;
    }


    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     */
    public static HttpResponse sendHttpPost(String httpUrl, Map<String, String> header)
    {
        // 创建httpPost
        HttpPost httpPost = new HttpPost(httpUrl);
        return sendHttpPost(httpPost, header, null);
    }

    /**
     * 发送 get请求
     *
     * @param httpUrl
     */
    public static HttpResponse sendHttpGet(String httpUrl, Map<String, String> header)
    {
        // 创建get请求
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpGet(httpGet, header, null);
    }

    public static HttpResponse sendHttpGet(String httpUrl, Map<String, String> header, String charset)
    {
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpGet(httpGet, header, charset);
    }

    public static HttpResponse sendHttpGet(String httpUrl, Map<String, String> param, Map<String, String> header)
    {
        HttpGet httpGet = new HttpGet(httpUrl);
        StringBuilder paramBuilder = new StringBuilder("?");
        for (String name : param.keySet())
        {
            paramBuilder.append(name).append("=").append(param.get(name)).append("&");
        }
        paramBuilder.deleteCharAt(paramBuilder.length() - 1);
        return sendHttpGet(httpUrl + paramBuilder.toString(), header, CHARSET_UTF_8);
    }

    public static String sendHttpGet(String httpUrl, boolean userCookie)
    {
        // 创建get请求
        /*
        HttpGet httpGet = new HttpGet(httpUrl);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0"); 
        return sendHttpGet(httpGet);
        */
        try
        {
            CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建httpClient实例
            HttpGet httpGet = new HttpGet(httpUrl); // 创建httpget实例

            //httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            //httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
            //httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            //httpGet.setHeader("Cache-Control", "max-age=0");
            //httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Cookie", "_lxsdk_cuid=15e7a6ef513c8-06775f05f-63161675-1fa400-15e7a6ef51384; _lxsdk=15e7a6ef513c8-06775f05f-63161675-1fa400-15e7a6ef51384; _hc.v=1a269ab4-917e-3de0-ba71-0d11de6d85f1.1505292646; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; s_ViewType=10; __utma=1.927541595.1508838228.1508838228.1509340292.2; __utmb=1.3.10.1509340292; __utmc=1; __utmz=1.1508838228.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); JSESSIONID=2F9D113D28C6A3B5EDAFCE2CF675E117; aburl=1; cy=2; cye=beijing; _lxsdk_s=15f6b938c94-e6c-0b1-702%7C%7C36");

            //httpGet.setHeader("Host", "www.dianping.com");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36"); // 设置请求头消息User-Agent

            CloseableHttpResponse response = httpClient.execute(httpGet); // 执行http get请求
            HttpEntity entity = response.getEntity(); // 获取返回实体
            //System.out.println("网页内容："+EntityUtils.toString(entity, "utf-8")); // 获取网页内容
            String res = EntityUtils.toString(entity, "utf-8");
            response.close(); // response关闭
            httpClient.close(); // httpClient关闭
            return res;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送 post请求（带文件）
     *
     * @param httpUrl   地址
     * @param maps      参数
     * @param fileLists 附件
     */
    public static HttpResponse sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists)
    {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        if (maps != null)
        {
            for (String key : maps.keySet())
            {
                meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
            }
        }
        if (fileLists != null)
        {
            for (File file : fileLists)
            {
                FileBody fileBody = new FileBody(file);
                meBuilder.addPart("files", fileBody);
            }
        }
        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return sendHttpPost(httpPost, maps, null);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param params  参数(格式:key1=value1&key2=value2)
     */
    public static HttpResponse sendHttpPost(String httpUrl, String params, Map<String, String> header)
    {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try
        {
            // 设置参数
            if (params != null && params.trim().length() > 0)
            {
                StringEntity stringEntity = new StringEntity(params, "UTF-8");
                stringEntity.setContentType(CONTENT_TYPE_FORM_URL);
                httpPost.setEntity(stringEntity);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost, header, null);
    }

    /**
     * 发送 post请求
     *
     * @param params 参数
     */
    public static HttpResponse sendHttpPost(String httpUrl, Map<String, String> params, Map<String, String> header)
    {
        String param = convertStringParamter(params);
        return sendHttpPost(httpUrl, param, header);
    }

    /**
     *  发送post请求（body）
     * @param httpUrl
     * @param body
     * @param header
     * @return
     */
    public static HttpResponse sendHttpPost(String httpUrl, Object body, Map<String, String> header)
    {
        HttpResponse response = null;
        try
        {
            HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
            response = sendHttpPost(httpPost, body, header, "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return response;
    }



    /**
     * 将map集合的键值对转化成：key1=value1&key2=value2 的形式
     *
     * @param parameterMap 需要转化的键值对集合
     * @return 字符串
     */
    public static String convertStringParamter(Map<String, String> parameterMap)
    {
        StringBuffer parameterBuffer = new StringBuffer();
        if (parameterMap != null)
        {
            Iterator<String> iterator = parameterMap.keySet().iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext())
            {
                key = (String) iterator.next();
                if (parameterMap.get(key) != null)
                {
                    value = (String) parameterMap.get(key);
                }
                else
                {
                    value = "";
                }
                parameterBuffer.append(key).append("=").append(value);
                if (iterator.hasNext())
                {
                    parameterBuffer.append("&");
                }
            }
        }
        return parameterBuffer.toString();
    }
}
