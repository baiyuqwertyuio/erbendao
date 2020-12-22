package com.qf.dao;

import com.qf.pojo.UpLoad;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UpLoadMapper {

    int add(UpLoad upLoad);

    List<UpLoad> videoFindByUid(@Param("uid") Integer uid);

    int del(@Param("id") Integer id);
}
