package com.cxy.website.common.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 *@Description shiro配置文件
 *@Author 侯森林
 *@Date 2019-9-18
 */
@Configuration
public class ShiroConfig {
    /*

     */
    /**
    *@Description  配置安全管理器，并注入realm域
    *@Param
    *@Return
    *@Author 侯森林
    *@Date 2019-910
    */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //自定义realm
        securityManager.setRealm(myShiroRealm());;
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        //全局会话超时时间（单位毫秒），默认30分钟  暂时设置为10秒钟 用来测试
        mySessionManager.setGlobalSessionTimeout(1800000);
        //是否开启删除无效的session对象  默认为true
        mySessionManager.setDeleteInvalidSessions(true);
        //是否开启定时调度器进行检测过期session 默认为true
        mySessionManager.setSessionValidationSchedulerEnabled(true);
        //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
        //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        //暂时设置为 5秒 用来测试
        mySessionManager.setSessionValidationInterval(1800000);
        mySessionManager.setSessionIdUrlRewritingEnabled(false);
        return mySessionManager;
    }

    @Bean
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    public CORSAuthenticationFilter corsAuthenticationFilter(){
        return new CORSAuthenticationFilter();
    }
    /**
     *@Description  配置过滤器
     *@Param SecurityManager 安全管理器
     *@Return shiroFilterFactoryBean
     *@Author 侯森林
     *@Date 2019-9-10
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

       Map<String, Filter> filterMap = new LinkedHashMap<>();
       filterMap.put("authc", new CORSAuthenticationFilter());
       shiroFilterFactoryBean.setFilters(filterMap);
        /*定义shiro过滤链  Map结构
         * Map中key(xml中是指value值)的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的
         * anon：它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种
         * authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
         /* 过滤链定义，从上向下顺序执行，一般将 / ** 放在最为下边:这是一个坑呢，一不小心代码就不好使了;
          authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问 */
        filterChainDefinitionMap.put("/loginMain", "anon");
        filterChainDefinitionMap.put("/resources/**", "anon");
        filterChainDefinitionMap.put("/api/**", "anon");
        filterChainDefinitionMap.put("/getVerifyCode", "anon");
        filterChainDefinitionMap.put("/videos", "anon");
        filterChainDefinitionMap.put("/get", "anon");

        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/vendor/**", "anon");
        filterChainDefinitionMap.put("/video/saveinfo", "anon");
        filterChainDefinitionMap.put("/doctorManage/**","anon");//放行医生登录的相关请求


        //放行swagger
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterChainDefinitionMap.put("/configuration/security", "anon");
        filterChainDefinitionMap.put("/configuration/ui", "anon");

        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public Realm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(myhashedCredentialsMatcher());
        myShiroRealm.setCachingEnabled(false);
        return myShiroRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
//		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    //免密
    @Bean
    public MyHashedCredentialsMatcher myhashedCredentialsMatcher() {
        MyHashedCredentialsMatcher myhashedCredentialsMatcher = new MyHashedCredentialsMatcher();
        myhashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        myhashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return myhashedCredentialsMatcher;
    }


    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "simpleMappingExceptionResolver")
    public Object createSimpleMappingExceptionResolver() {
//        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
//        Properties mappings = new Properties();
////		mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
//        mappings.setProperty("UnauthorizedException", "403");
//        r.setExceptionMappings(mappings);  // None by default
////		r.setDefaultErrorView("error");    // No default
//        r.setExceptionAttribute("exception");     // Default is "exception"
//        //r.setWarnLogCategory("example.MvcLogger");     // No default
//        return r;
        Map map=new HashMap();
        map.put("code","403");
        map.put("msg","没有权限");
        return map;
    }

}



