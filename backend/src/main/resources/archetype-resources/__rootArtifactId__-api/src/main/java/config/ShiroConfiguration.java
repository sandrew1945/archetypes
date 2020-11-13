#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;


import ${package}.config.shiro.AccountAuthorizationRealm;
import ${package}.config.shiro.MyFormAuthenticationFilter;
import ${package}.config.shiro.separate.DefaultHeaderSessionManager;
import ${package}.config.shiro.separate.MyShiroFilterFactoryBean;
import ${package}.config.shiro.separate.MyUserFilter;
import ${package}.config.shiro.session.RedisSessionDAO;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  shiro 配置类
 */
@Configuration
@Log4j2
public class ShiroConfiguration
{

    /**
     * 配置shiroFilter
     *
     * @return
     */
    @Bean(name="shiroFilter")
    public MyShiroFilterFactoryBean shiroFlter()
    {

        MyShiroFilterFactoryBean shiroFilter = new MyShiroFilterFactoryBean();

        shiroFilter.setLoginUrl("/login");

        Map<String, Filter> filters = shiroFilter.getFilters();
        filters.put("anon", new AnonymousFilter());
        filters.put("authc", new MyFormAuthenticationFilter());
        filters.put("logout", new LogoutFilter());
        filters.put("roles", new RolesAuthorizationFilter());
        filters.put("user", new MyUserFilter());
        //filters.put("captcha", new CaptchaValidateFilter());  如果需要验证码,打开此过滤器
//        shiroFilter.setFilters(filters);
        shiroFilter.setSecurityManager(securityManager());

        Map<String, String> filterChainDefinitionMapping = new LinkedHashMap<String, String>();
        filterChainDefinitionMapping.put("/server", "anon");
        filterChainDefinitionMapping.put("/shutdown", "anon");
        filterChainDefinitionMapping.put("/generate/**", "anon"); //生成各类
//        filterChainDefinitionMapping.put("/logout", "logout");
        filterChainDefinitionMapping.put("/login", "authc");
        filterChainDefinitionMapping.put("/**", "user");

        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
        log.debug("===============配置shiroFilter完毕!=============");
        return shiroFilter;
    }

    /**
     * @return
     */
    @Bean(name="securityManager")
    public SecurityManager securityManager()
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean(name="realm")
    //@DependsOn("lifecycleBeanPostProcessor")
    public AccountAuthorizationRealm realm()
    {
        AccountAuthorizationRealm accountAuthorizationRealm = new AccountAuthorizationRealm();
        //        accountAuthorizationRealm.init();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setStoredCredentialsHexEncoded(true);
        matcher.setHashIterations(1);
        accountAuthorizationRealm.setCredentialsMatcher(matcher);
        return accountAuthorizationRealm;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
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
        DefaultHeaderSessionManager sessionManager = new DefaultHeaderSessionManager();
        sessionManager.setDeleteInvalidSessions(true);
//        sessionManager.setSessionIdCookieEnabled(true);

        sessionManager.setSessionValidationSchedulerEnabled(false);
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        MySqlSessionValidationScheduler sessionValidationScheduler = sessionValidationScheduler();
//        sessionValidationScheduler.setSessionManager(sessionManager);
//        sessionManager.setSessionValidationScheduler(sessionValidationScheduler());
//        sessionManager.setSessionDAO(sessionDAO());      // 使用默认的sessionDao
//        sessionManager.setSessionIdCookie(sessionIdCookie());
        return sessionManager;
    }


    /**
     *  自定义sessionDao,将session保存到redis或数据库
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
