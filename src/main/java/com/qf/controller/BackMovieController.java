package com.qf.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.qf.common.BaseResp;
import com.qf.common.UploadUtils;
import com.qf.pojo.Movie;
import com.qf.pojo.User;
import com.qf.service.MovieService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

//电影后台管理
@RestController
@RequestMapping("/backMovie")
public class BackMovieController {
    @Autowired
    MovieService movieService;
    @Autowired
    UploadUtils uploadUtils;

    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    @RequiresPermissions(value = {"movieSelectAll"})
    public BaseResp selectAll(){
        return movieService.selectAll();
    }

    @RequestMapping(value = "/selectById",method = RequestMethod.GET)
    @RequiresPermissions(value = {"movieSelectById"})
    public BaseResp selectById(@RequestParam("id") Integer id){
        return movieService.findById(id);
    }

    @RequestMapping(value = "/insertOrUpdate",method = RequestMethod.POST)
    @RequiresPermissions(value = {"movieInsertOrUpdate"})
    public BaseResp insertOrUpdate(@RequestBody Movie movie){
        return movieService.insertOrUpdate(movie);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @RequiresPermissions(value = {"movieDelete"})
    public BaseResp delete(@RequestParam("id") Integer id){
        return movieService.delete(id);
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public BaseResp upload(@RequestParam("file") MultipartFile multipartFile){
        return uploadUtils.upload(multipartFile);
    }

    @RequestMapping(value = "/upload1",method = RequestMethod.POST)
    public BaseResp upload1(@RequestParam("file") MultipartFile multipartFile){
        return uploadUtils.upload(multipartFile);
    }

    @RequestMapping(value = "/upload2",method = RequestMethod.POST)
    public BaseResp upload2(@RequestParam("file") MultipartFile multipartFile){
        return uploadUtils.upload(multipartFile);
    }

    @RequestMapping(value = "/outAll",method = RequestMethod.POST)
    public BaseResp outAll() throws IOException {
        BaseResp baseResp = new BaseResp();
        List<Movie> list = movieService.outAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息","用户列表"),Movie.class,list);
        FileOutputStream outputStream = new FileOutputStream("D:\\qf\\qf\\qf\\电影信息.xls");
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
        baseResp.setCode(200);
        return baseResp;
    }
}
