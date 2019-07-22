package com.chryl.service;

import com.chryl.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Chryl on 2019/7/22.
 */
@Service
public class UserSercice {
    @Resource
    private UserMapper userMapper;
    public List<Function> findFunByUserId(String id) {

    }
}
