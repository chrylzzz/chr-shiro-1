package com.chryl.controller;

import com.alibaba.fastjson.JSONObject;
import com.chryl.bean.User;
import com.chryl.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Chryl on 2019/7/22.
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public Object toLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password) {
        if (StringUtils.isAnyBlank(username, password)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            log.info("登陆成功");
            User user = userMapper.selectByUserName(username);
            if (user != null) {
                jsonObject.put("msg", "login suc");
                jsonObject.put("user", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    @GetMapping("/get/{id}")
    public Object getUsr(@PathVariable("id") String id) {
        return userMapper.selectByUserId(id);

    }
}
