package com.qf.dao;

import com.qf.pojo.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface MovieMapper {
    List<Movie> searchByName(@Param("like") String like);
}
