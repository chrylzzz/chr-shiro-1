package com.chryl.realm;

import com.chryl.bean.User;
import com.chryl.mapper.UserMapper;
import com.chryl.service.UserSercice;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chryl on 2019/7/22.
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserSercice userSercice;
    //授权方法,权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取用户对象
        User user = (User) principalCollection.getPrimaryPrincipal();


        List<String> perms = new ArrayList<>();
        //根据用户id 获取权限
        UserSercice.findFunByUserId(user.getId());

        return null;
    }

    @Autowired
    private UserMapper userMapper;

    //认证方法,用户登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证方法。。。。");
        String username = authenticationToken.getPrincipal().toString();
        System.out.println("username:" + username);
        //需要通过用户名查询用户密码
        User user = userMapper.selectByUserName(username);
        String password = user.getUserPassword();
        //把用户名和密码封装到AuthenticationInfo对象中
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, password, "shiroRealm");
        return authenticationInfo;
    }
}
