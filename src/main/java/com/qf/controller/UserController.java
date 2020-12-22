package com.qf.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.qf.common.BaseResp;
import com.qf.pojo.User;
import com.qf.service.UserService;
import com.qf.utils.UserUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserUtils userUtils;

    @RequestMapping(value = "/sendMail",method = RequestMethod.POST)
    public BaseResp sendMail(@RequestBody Map map){
        String email = (String)map.get("email");

        return userService.send(email);
    }

    @RequestMapping(value = "/registry",method = RequestMethod.POST)
    public BaseResp registry(@RequestBody User user){
        return userService.registry(user);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResp login(@RequestBody User user){
        return userService.login(user);
    }


    @RequestMapping(value = "/getuser",method = RequestMethod.GET)
    public BaseResp getUser(HttpServletRequest request,@RequestParam String token){
        BaseResp baseResp = new BaseResp();
        User user = userUtils.getUser(request,token);
        baseResp.setCode(200);
        baseResp.setData(user);
        baseResp.setMsg("查询成功");
        return baseResp;
    }


    @RequestMapping(value = "/backlogin",method = RequestMethod.POST)
    public BaseResp Backlogin(@RequestBody User user){
        //登录的验证。从subjectUtils中获取主体
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getEmail(),user.getPassword());
        try {
            subject.login(token);


        }catch (Exception e){
            System.out.println("登录失败");
        }
        BaseResp baseResp = new BaseResp();

        if (subject.isAuthenticated()){
            baseResp.setCode(200);
            baseResp.setMsg("登录成功");
            return baseResp;
        }else {
            baseResp.setCode(201);
            baseResp.setMsg("登录失败");
            return baseResp;
        }
    }

    //后台管理方法
    @RequestMapping(value = "/findAll/{page}/{limit}",method = RequestMethod.GET)
    @RequiresPermissions(value = {"userFindAll"})
    public BaseResp findAll(@PathVariable Integer page,@PathVariable Integer limit){
        return userService.findAll(page,limit);
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    @RequiresPermissions(value = {"userFindById"})
    public BaseResp findById(@RequestBody Map map){
        return userService.findById(Integer.valueOf(map.get("id").toString()));
    }

    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    @RequiresPermissions(value = {"userSaveOrUpdate"})
    public BaseResp saveOrUpdate(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    @RequiresPermissions(value = {"userDeleteById"})
    public BaseResp deleteById(@RequestBody Map map){
        return  userService.deleteById(Integer.valueOf(map.get("id").toString()));
    }

    @RequestMapping(value = "/findUserByToken",method = RequestMethod.POST)
    public BaseResp findUserByToken(HttpServletRequest request){
        return userService.findUserByToken(request);
    }

    @RequestMapping(value = "/outAll",method = RequestMethod.POST)
    public BaseResp outAll() throws IOException {
        BaseResp baseResp = new BaseResp();
        List<User> list = userService.outAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息","用户列表"),User.class,list);
        FileOutputStream outputStream = new FileOutputStream("D:\\qf\\qf\\qf\\用户信息.xls");
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
        baseResp.setCode(200);
        return baseResp;
    }

}
