package com.hy.orderservice.service;

import com.hy.common.result.R;
import com.hy.orderservice.entity.OUser;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 黄叶
 * @since 2020-06-13
 */
public interface OUserService extends IService<OUser> {

    /**
     * 登录逻辑处理
     * @param oUser 用户实体类
     * @return 统一响应体
     */
    R login(OUser oUser);

    /**
     * 获取登录用户信息
     * @param request 用于获取token信息
     * @return 统一响应体
     */
    R getInfo(HttpServletRequest request);

    /**
     * 用户登出，清除cookie
     */
    R logout(HttpServletRequest request, HttpServletResponse response);
}
