package com.cseu.server.user;

import com.cseu.server.user.bean.CseuGuest;
import com.cseu.server.user.service.CseuGuestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserApplicationTests {

    @Resource
    private CseuGuestService cseuGuestService;

    @Test
    public void contextLoads() {

    }

}
