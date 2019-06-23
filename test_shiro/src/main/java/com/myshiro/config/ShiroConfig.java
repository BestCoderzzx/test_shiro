package com.myshiro.config;

import com.myshiro.filter.LoginFilter;
import com.myshiro.filter.PermsFilter;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2019/6/5.
 */
@Configuration
public class ShiroConfig {

    //1. ShiroFilterFactoryBean
    @Bean
    ShiroFilterFactoryBean SetShiroFilterFactoryBean(@Qualifier("webSecurityManager") WebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String,String> filterChains = new HashMap<String,String>();
        /**
         * anon  不需要登录
         * authc 需要登录认证
         * user  记住我时候使用
         * perms 是否有权限
         * role 是否有角色
         */
        filterChains.put("/submitLogin","anon");
        filterChains.put("/user/add","perms[userAdd]");
        filterChains.put("/user/query","perms[userQuery]");
        filterChains.put("/**","authc");
//        filterChains.put("/**","user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChains);
        //
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        //自定义过来器
        Map<String,Filter> filters = new HashMap<>();
        filters.put("authc",new LoginFilter());
        filters.put("perms",new PermsFilter());
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;
    }

    //2. DefaultWebSecurityManager
    @Bean("webSecurityManager")
    WebSecurityManager setWebSecurityManager(@Qualifier("myUserRealm") MyUserRealm userRealm){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(userRealm);
        return webSecurityManager;
    }

    //3. Realm
    @Bean("myUserRealm")
    MyUserRealm setMyUserRealm(){
        return new MyUserRealm();
    }
}
