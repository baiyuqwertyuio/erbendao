package com.qf.service.impl;

import com.qf.common.BaseResp;
import com.qf.dao.MovieMapper;
import com.qf.dao.MovieRepository;
import com.qf.pojo.Movie;
import com.qf.service.MovieService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieMapper movieMapper;

    @Override
    public BaseResp findAll() {
        BaseResp baseResp = new BaseResp();
        List<Movie> all = movieRepository.findAll();
        baseResp.setCode(200);
        baseResp.setMsg("查询成功");
        baseResp.setData(all);
        return baseResp;
    }

    @Override
    public BaseResp findBySid(Integer sid) {
        BaseResp baseResp = new BaseResp();
        List<Movie> bySid = movieRepository.findBySid(sid);
        if (bySid!=null){
            baseResp.setCode(200);
            baseResp.setMsg("查询成功");
            baseResp.setData(bySid);
            return baseResp;
        }
        baseResp.setCode(300);
        baseResp.setMsg("查询失败");
        return baseResp;
    }

    @Override
    public BaseResp findById(Integer id) {
        BaseResp baseResp = new BaseResp();
        Optional<Movie> byId = movieRepository.findById(id);
        if (byId.isPresent()){
            baseResp.setCode(200);
            baseResp.setMsg("查询成功");
            baseResp.setData(byId.get());
            return baseResp;
        }
        baseResp.setCode(300);
        baseResp.setMsg("查询失败");
        return baseResp;
    }

    @Override
    public BaseResp findByVip(Integer vip) {
        BaseResp baseResp = new BaseResp();
        List<Movie> byVip = movieRepository.findByVip(vip);
        if (byVip!=null){
            baseResp.setCode(200);
            baseResp.setMsg("查询成功");
            baseResp.setData(byVip);
            return baseResp;
        }
        baseResp.setCode(300);
        baseResp.setMsg("查询失败");
        return baseResp;
    }

    @Override
    public BaseResp findBigPic() {
        BaseResp baseResp = new BaseResp();
        List<Movie> all = movieRepository.findAll();
        List<Movie> movies = all.subList(0, 5);
        baseResp.setCode(200);
        baseResp.setMsg("查询成功");
        baseResp.setData(movies);
        return baseResp;
    }

    @Override
    public BaseResp searchByName(String like) {
        BaseResp baseResp = new BaseResp();
        List<Movie> movies = movieMapper.searchByName(like);
        long count = (long)movies.size();
        baseResp.setCode(200);
        baseResp.setMsg("查询成功");
        baseResp.setData(movies);
        baseResp.setCount(count);
        return baseResp;
    }

    @Override
    public BaseResp uploading(Movie movie) {
        BaseResp baseResp = new BaseResp();
        Movie movie1 = movieRepository.saveAndFlush(movie);
        if (movie1!=null){
            baseResp.setMsg("上传成功");
            baseResp.setCode(200);
            return baseResp;
        }
        baseResp.setMsg("上传失败");
        baseResp.setCode(300);
        return baseResp;
    }
}
