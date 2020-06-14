package com.hy.orderservice.controller;


import com.hy.common.result.R;
import com.hy.common.utils.JwtUtils;
import com.hy.orderservice.entity.OUser;
import com.hy.orderservice.service.OUserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户控制器
 * </p>
 *
 * @author 黄叶
 * @since 2020-06-13
 */
@Api("用户控制器")
@CrossOrigin
@RestController
@RequestMapping("/oderservice/user")
public class OUserController {

    @Autowired
    OUserService oUserService;

    /**
     * 使用JWT进行登录
     * @param oUser
     * @return
     */
    @PostMapping("login")
    public R login(@RequestBody OUser oUser) {
        return oUserService.login(oUser);
    }

    /**
     * 解析JWT获取，用户信息
     * @return
     */
    @GetMapping("info")
    public R info(  HttpServletRequest request) {
        return oUserService.getInfo(request);
    }

    /**
     * 用户登出，清除cookie
     */
    @PostMapping("logout")
    public R logout(HttpServletRequest request, HttpServletResponse response){
        return oUserService.logout(request,response);
    }
}

