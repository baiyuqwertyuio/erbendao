package com.qf;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.qf.common.BaseResp;
import com.qf.dao.MovieMapper;
import com.qf.dao.UserRepository;
import com.qf.pojo.Movie;
import com.qf.pojo.User;
import com.qf.service.MovieService;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Tests {
    @Autowired
    MovieService movieService;
    @Autowired
    MovieMapper movieMapper;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testAll() throws Exception {
        List<User> all = userRepository.findAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户列表","测试"),User.class,all);
        FileOutputStream outputStream = new FileOutputStream("D:\\qf\\qf\\qf\\aa.xls");
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    @Test
    public void test(){
        //定义导入参数
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        List<User> users = ExcelImportUtil.importExcel(new File("D:\\qf\\qf\\qf\\aa.xls"), User.class,importParams);
        for (User user : users) {
            userRepository.saveAndFlush(user);
        }
    }

}
