#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;


import ${package}.config.shiro.MyCasRealm;
import ${package}.config.shiro.MyFormAuthenticationFilter;
import ${package}.config.shiro.separate.HeaderSessionManager;
import ${package}.config.shiro.separate.MyShiroFilterFactoryBean;
import ${package}.config.shiro.separate.MyUserFilter;
import ${package}.config.shiro.session.RedisSessionDAO;
import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;
import io.buji.pac4j.subject.Pac4jSubjectFactory;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.config.Config;
import org.pac4j.core.logout.handler.DefaultLogoutHandler;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置类
 */
@Configuration
@Log4j2
public class ShiroConfiguration
{
    @Value("${symbol_dollar}{sso.server.url}")
    private String serverAddress;

    @Value("${symbol_dollar}{sso.local.address}")
    private String localAddress;

    @Value("${symbol_dollar}{server.servlet.context-path}")
    private String context;

    @Value("${symbol_dollar}{sso.client.name}")
    private String clientName;

    /**
     * 配置shiroFilter
     *
     * @return
     */
    @Bean(name = "shiroFilter")
    public MyShiroFilterFactoryBean shiroFilter(Config config) throws UnknownHostException
    {
        MyShiroFilterFactoryBean shiroFilter = new MyShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());

        Map<String, Filter> filters = shiroFilter.getFilters();
        //cas 资源认证拦截器
        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.setConfig(config);
        //securityFilter.setClients(clientName);
        filters.put("securityFilter", securityFilter);
        //cas 认证后回调拦截器
        CallbackFilter callbackFilter = new CallbackFilter();
        callbackFilter.setConfig(config);
        callbackFilter.setDefaultUrl(localAddress + context + "/sso");
        filters.put("callbackFilter", callbackFilter);
        // 注销 拦截器
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setConfig(config);
        logoutFilter.setCentralLogout(true);
        logoutFilter.setLocalLogout(true);
        logoutFilter.setDefaultUrl(localAddress);
        filters.put("logout",logoutFilter);


        filters.put("anon", new AnonymousFilter());
        filters.put("authc", new MyFormAuthenticationFilter());
        filters.put("logout", logoutFilter);
        filters.put("roles", new RolesAuthorizationFilter());
        filters.put("user", new MyUserFilter());
        //filters.put("captcha", new CaptchaValidateFilter());  如果需要验证码,打开此过滤器
        //        shiroFilter.setFilters(filters);
        shiroFilter.setSecurityManager(securityManager());

        Map<String, String> filterChainDefinitionMapping = new LinkedHashMap<String, String>();
        filterChainDefinitionMapping.put("/shutdown", "anon");
        filterChainDefinitionMapping.put("/generate/**", "anon"); //生成各类
        filterChainDefinitionMapping.put("/casFailure.jsp", "anon");
        filterChainDefinitionMapping.put("/", "securityFilter");
        filterChainDefinitionMapping.put("/sso", "securityFilter");
        filterChainDefinitionMapping.put("/logout", "logout");
        filterChainDefinitionMapping.put("/login", "securityFilter");
        filterChainDefinitionMapping.put("/callback", "callbackFilter");
        filterChainDefinitionMapping.put("/**", "user");

        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
        log.debug("===============配置shiroFilter完毕!=============");
        return shiroFilter;
    }

    @Bean
    public CasConfiguration casConfig() {
        final CasConfiguration configuration = new CasConfiguration();
        //CAS server登录地址
        configuration.setLoginUrl(serverAddress + "/login");
        configuration.setAcceptAnyProxy(true);
        configuration.setPrefixUrl(serverAddress + "/");
        //监控CAS服务端登出，登出后销毁本地session实现双向登出
        DefaultLogoutHandler logoutHandler = new DefaultLogoutHandler();
        logoutHandler.setDestroySession(true);
        configuration.setLogoutHandler(logoutHandler);
        return configuration;
    }

    @Bean
    public Pac4jSubjectFactory subjectFactory(){
        return new Pac4jSubjectFactory();
    }

    /**
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager()
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setSubjectFactory(subjectFactory());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean(name = "realm")
    //@DependsOn("lifecycleBeanPostProcessor")
    public MyCasRealm realm()
    {
        MyCasRealm myCasRealm = new MyCasRealm();
        // 使用自定义的realm
        myCasRealm.setClientName(clientName);
        myCasRealm.setCachingEnabled(false);
        //暂时不使用缓存
        myCasRealm.setAuthenticationCachingEnabled(false);
        myCasRealm.setAuthorizationCachingEnabled(false);
        return myCasRealm;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
    {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor()
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator()
    {
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean()
    {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(securityManager());
        return methodInvokingFactoryBean;
    }


    @Bean
    public DefaultSessionManager sessionManager()
    {
//        DefaultHeaderSessionManager sessionManager = new DefaultHeaderSessionManager();
//        sessionManager.setDeleteInvalidSessions(true);
//        sessionManager.setSessionValidationSchedulerEnabled(false);
        HeaderSessionManager sessionManager = new HeaderSessionManager();
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookieEnabled(true);
//        sessionManager.setSessionValidationSchedulerEnabled(false);
        sessionManager.setSessionIdCookie(sessionIdCookie());
        return sessionManager;
    }


    /**
     * 自定义sessionDao,将session保存到redis或数据库
     *
     * @return
     */
    @Bean
    public RedisSessionDAO sessionDAO()
    {
        //      MySqlSessionDAO sessionDAO = new MySqlSessionDAO();
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setSessionIdGenerator(sessionIdGenerator());
        return sessionDAO;
    }

    @Bean
    public SessionIdGenerator sessionIdGenerator()
    {
        SessionIdGenerator sessionIdGenerator = new JavaUuidSessionIdGenerator();
        return sessionIdGenerator;
    }

    @Bean
    public SimpleCookie sessionIdCookie()
    {
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);
        simpleCookie.setDomain("");
        simpleCookie.setPath("/");
        return simpleCookie;
    }
}
