package com.qf.dao;

import com.qf.pojo.Discuss;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * 评论持久层
 *
 */
public interface DiscussRepository extends JpaRepository<Discuss, Integer> {

    List<Discuss> findByMid(@Param("mid") Integer mid);

}
