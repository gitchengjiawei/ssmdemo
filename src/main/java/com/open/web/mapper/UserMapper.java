package com.open.web.mapper;

import com.open.web.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public List<User> getAllUser();
}
