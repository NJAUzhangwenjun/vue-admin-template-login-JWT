package com.hy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hy.common.utils.JwtUtils;
import com.hy.orderservice.entity.OUser;
import com.hy.orderservice.service.OUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MywebApplicationTests {
@Autowired
    OUserService oUserService;
    @Test
    void contextLoads() {
        QueryWrapper<OUser> oUserQueryWrapper = new QueryWrapper<>();
        oUserQueryWrapper.eq("username","admin");
        OUser one = oUserService.getOne(oUserQueryWrapper);
        System.out.println(oUserService.login(one));
    }
    @Test
    void getToken(){
    }

}
