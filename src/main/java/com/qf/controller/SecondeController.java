package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.service.SecondeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seconde")
public class SecondeController {
    @Autowired
    SecondeService secondeService;

    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public BaseResp selectAll(){
        return secondeService.selectAll();
    }

    @RequestMapping(value = "/selectByCid",method = RequestMethod.GET)
    public BaseResp selectByCid(@RequestParam("cid") Integer cid){
        return secondeService.selectByCid(cid);
    }
}
