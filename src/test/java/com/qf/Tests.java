package com.qf;

import com.qf.common.BaseResp;
import com.qf.dao.MovieMapper;
import com.qf.pojo.Movie;
import com.qf.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Tests {
    @Autowired
    MovieService movieService;
    @Autowired
    MovieMapper movieMapper;

    @Test
    public void testAll() {
        BaseResp movies = movieService.searchByName("肖");
        System.out.println(movies);
    }

}
