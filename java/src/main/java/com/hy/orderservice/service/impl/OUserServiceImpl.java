package com.hy.orderservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hy.common.result.R;
import com.hy.common.utils.JwtUtils;
import com.hy.orderservice.entity.OUser;
import com.hy.orderservice.mapper.OUserMapper;
import com.hy.orderservice.service.OUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 黄叶
 * @since 2020-06-13
 */
@Service
public class OUserServiceImpl extends ServiceImpl<OUserMapper, OUser> implements OUserService {

    @Autowired
    OUserMapper oUserMapper;

    /**
     * 登录逻辑处理
     * @param oUser
     * @return
     */
    @Override
    public R login(OUser oUser) {
        // 根据用户名，查询用户信息
        QueryWrapper<OUser> oUserQueryWrapper = new QueryWrapper<>();
        oUserQueryWrapper.eq("username",oUser.getUsername());
        OUser user = oUserMapper.selectOne(oUserQueryWrapper);

        // 1、是否有用户
        if(user == null) return R.error().data("用户不存在");

        // 2、判断用户名输入是否正确
         if(user.getUsername().equals(oUser.getUsername())){
             // 3、判断密码是否正确，正确则存放token
            if(user.getPassword().equals(oUser.getPassword())){
                Map map = new HashMap(16);
                map.put("token",JwtUtils.getJwtToken(user.getId(),user.getUsername()));
                return R.ok().data(map);
            }
            // 不正确，则提示错误信息
            else return R.error().data("密码错误");
        }

        // 不正确，则提示错误信息
        else  return R.error().data("用户名输入错误");

    }

    /**
     * 获取用户信息逻辑处理
     * @param request 用来获取token
     * @return
     */
    @Override
    public R getInfo(HttpServletRequest request) {
        // 获取jwt解析的信息（用户的id）
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        // 根据id，查询用户的信息，并将他放入data数据中
        OUser user = oUserMapper.selectById(memberIdByJwtToken);
        // 存储用户信息到响应体
        Map map = new HashMap<>();
        map.put("name",user.getUsername());
        map.put("avatar",user.getImage());
        return R.ok().data(map);
    }

    /**
     * 用户登出，清除cookie
     * @param request
     * @param response
     * @return
     */
    @Override
    public R logout(HttpServletRequest request, HttpServletResponse response) {
//        Cookie[] cookies = request.getCookies();
//        if(cookies!=null){
//            for (Cookie cookie :cookies ) {
//                cookie.setMaxAge(0);
//                cookie.setPath("/");  //路径一定要写上，不然销毁不了
//                response.addCookie(cookie);
//            }
         return R.ok();
//        }
//        else return R.error();


    }
}
