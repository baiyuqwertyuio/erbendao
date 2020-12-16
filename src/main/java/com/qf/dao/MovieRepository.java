package com.qf.dao;

import com.qf.pojo.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    List<Movie> findBySid(Integer sid);

    List<Movie> findByVip(Integer vip);
}
