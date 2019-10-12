package com.open.web.mapper;

import com.open.web.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    public List<User> getAllUser();

    public String getPasswordByUsername(@Param("username") String username);

    public User getUserByUsername(@Param("username")String username);
}
