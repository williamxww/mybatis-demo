package com.bow.dao;

import com.bow.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author vv
 * @since 2017/3/1.
 */
public interface UserMapper {

    @Select("select * from shiro.sys_user WHERE id = #{id}")
    User getUser(long id);

}
