package com.qf.dao;

import com.qf.pojo.Discuss;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论持久层
 */

@Mapper
public interface DiscussMapper {

    //查询电影所有评论
    List<Discuss> findByMid(@Param("id") Integer mid);

    //添加评论
    int insertDiscuss(Discuss discuss);

}
