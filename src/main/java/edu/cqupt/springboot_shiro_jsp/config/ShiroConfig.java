package edu.cqupt.springboot_shiro_jsp.config;

import edu.cqupt.springboot_shiro_jsp.shiro.cache.MyRedisCacheManager;
import edu.cqupt.springboot_shiro_jsp.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LWenH
 * @create 2021/6/19 - 20:10
 * <p>
 * 整合shiro
 */
@Configuration
public class ShiroConfig {


    /**
     * 创建自定义realm
     *
     * @return
     */
    @Bean("customerRealm")
    public Realm realm() {
        CustomerRealm realm = new CustomerRealm();
        // 凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 设置加密方式：Md5
        credentialsMatcher.setHashAlgorithmName("md5");
        // 设置散列次数
        credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);

        // 开启缓存管理
        // shiro中默认的EhCache
        realm.setCacheManager(new MyRedisCacheManager());
        // 开启全局缓存
        realm.setCachingEnabled(true);
        // 开启认证缓存，并为缓存设置别名
        realm.setAuthenticationCachingEnabled(true);
        realm.setAuthenticationCacheName("authenticationCache");
        // 开启授权缓存，并设置别名
        realm.setAuthorizationCachingEnabled(true);
        realm.setAuthorizationCacheName("authorizationCache");

        return realm;
    }

    /**
     * 安全管理器
     *
     * @param realm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier(value = "customerRealm") Realm realm) {
        return new DefaultWebSecurityManager(realm);
    }

    /**
     * shiroFilter 过滤器
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 给shiroFilter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 配置系统受限的资源和公共资源
        Map<String, String> filterMap = new HashMap<>();
        /*
            authc和anon等其实都是过滤器，shiro进行权限控制的原理就是基于过滤器链
            authc：请求这个资源需要认证和授权
            anon：指定的url可以匿名访问，也就是不需要授权

            这里需要注意的是，过滤器链是有顺序的，需要按照顺序添加到map中，达到想要的效果
            所以需要先配置公开资源，然后配置受限资源
         */
        filterMap.put("/actuator/**", "anon");
        filterMap.put("/register.jsp", "anon");
        filterMap.put("/user/register", "anon");
        filterMap.put("/user/login", "anon");
        filterMap.put("/user/image", "anon");
        filterMap.put("/**", "authc");
        /*
            请求被拦截以后，默认会回到login页面中，如果没有这个界面的话就会出错
            手动指定默认的认证界面为login.jsp
            设置了默认的认证界面以后，如果对所有资源设置都需要认证和授权，请求不存在的资源也会重定向到指定的认证界面中去
         */
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }
}
