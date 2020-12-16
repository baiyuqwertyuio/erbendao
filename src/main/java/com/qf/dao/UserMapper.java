package com.qf.dao;

import com.qf.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User fingByEmail(@Param("email") String email);
}
