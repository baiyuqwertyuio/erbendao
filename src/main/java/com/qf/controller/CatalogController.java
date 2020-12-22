package com.qf.controller;

import com.qf.common.BaseResp;
import com.qf.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    CatalogService catalogService;

    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public BaseResp selectAll(){
        return catalogService.selectAll();
    }
}
