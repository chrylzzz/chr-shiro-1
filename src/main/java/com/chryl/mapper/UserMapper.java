package com.chryl.mapper;

import com.chryl.bean.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;

/**
 * Created By Chr on 2019/7/19.
 */
public interface UserMapper {

    User selectByUserId(@Param("id") String id);

    User selectByUserName(@Param("userName") String userName);

    String queryPasswordByUserName(@Param("userName") String userName);

}
