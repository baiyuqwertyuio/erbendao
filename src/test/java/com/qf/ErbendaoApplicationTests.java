package com.qf;

import com.qf.dao.UserRepository;
import com.qf.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErbendaoApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Test
    public void contextLoads() {

        User byEmail = userRepository.findByEmail("815804284@qq.com");
        System.out.println("byEmail = " + byEmail);
    }

}
