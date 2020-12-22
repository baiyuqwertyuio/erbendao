package com.qf.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.qf.common.BaseResp;
import com.qf.pojo.Movie;
import com.qf.pojo.OrdList;
import com.qf.pojo.Order;
import com.qf.pojo.User;
import com.qf.service.OrderService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderrController {


    @Autowired
    OrderService orderService;

    @RequestMapping("/findAll")
    @RequiresPermissions(value = {"orderfindAll"})
    public BaseResp findAll(){
        return orderService.findAll();
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public BaseResp findById(@RequestParam Integer id){
        return orderService.findById(id);
    }

    @RequestMapping(value = "/updateOrd",method = RequestMethod.POST)
    public BaseResp updateOrd(@RequestBody Order order){
        BaseResp baseResp = new BaseResp();
        int update = orderService.update(order);
        baseResp.setData(update);
        baseResp.setCode(200);
        return baseResp;
    }

    @RequestMapping(value = "/insertOrd",method = RequestMethod.POST)
    public BaseResp insert(@RequestBody Order order){
        BaseResp baseResp = new BaseResp();
        int insert = orderService.insert(order);
        baseResp.setData(insert);
        baseResp.setCode(200);
        return baseResp;
    }

    @RequestMapping(value = "/deleteOrd",method = RequestMethod.POST)
    @RequiresPermissions(value = {"orderdeleteOrd"})
    public BaseResp delete(@RequestBody Map map){
        BaseResp baseResp = new BaseResp();

        int delete = orderService.delete(Integer.valueOf(map.get("id").toString()));
        baseResp.setData(delete);
        baseResp.setCode(200);
        return baseResp;
    }

    @RequestMapping(value = "/outAll",method = RequestMethod.POST)
    public BaseResp outAll() throws IOException {
        BaseResp baseResp = new BaseResp();
        List<OrdList> list = orderService.outAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息","用户列表"), OrdList.class,list);
        FileOutputStream outputStream = new FileOutputStream("D:\\qf\\qf\\qf\\订单信息.xls");
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
        baseResp.setCode(200);
        return baseResp;
    }


}
