package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.common.UploadUtils;
import com.qf.pojo.UpLoad;
import com.qf.service.UpLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("/upload")
public class UpLoadController {

    @Autowired
    UploadUtils uploadUtils;

    @Autowired
    UpLoadService upLoadService;

    @RequestMapping(value = "/videoUpLoad",method = RequestMethod.POST)
    public BaseResp videoUpLoad(@RequestParam("file")MultipartFile multipartFile){

        return uploadUtils.upload(multipartFile);

    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResp add(@RequestBody UpLoad upLoad){
        return upLoadService.add(upLoad);
    }

    @RequestMapping(value = "/videoFindByUid",method = RequestMethod.POST)
    public BaseResp videoFindByUid(@RequestParam("uid") Integer uid){
        return upLoadService.videoFindByUid(uid);
    }
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public BaseResp del(@RequestBody Map map){
        return upLoadService.del(Integer.valueOf(map.get("id").toString()));

    }

}
