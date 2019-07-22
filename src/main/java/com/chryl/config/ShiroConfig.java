package com.chryl.config;

import com.chryl.realm.MyShiroRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chryl on 2019/7/22.
 */
@Configuration
public class ShiroConfig {
    //添加创建securityManager的工厂类注入bean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * 设置权限认证
         */
        //加载过滤器方法:
        // 两个泛型:路径,过滤器
        //anon-授权的
        //authc-未授权的
        Map<String, String> params = new HashMap<>();
        params.put("/user/login", "anon");
        params.put("/login.html", "anon");
        params.put("/index.html", "anon");
        params.put("/*", "authc");
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("404.html");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(params);
        return shiroFilterFactoryBean;
    }

    //创建SecurityManager类的注入bean
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shiroRealm") MyShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }

    //创建自定义realm域类的注入bean
    @Bean(name = "shiroRealm")
    public MyShiroRealm getMyShiroRealm() {
        MyShiroRealm shiroRealm = new MyShiroRealm();
        return shiroRealm;
    }

}
