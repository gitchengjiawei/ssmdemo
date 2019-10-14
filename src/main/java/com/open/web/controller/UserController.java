package com.open.web.controller;

import com.open.web.bean.User;
import com.open.web.constant.ResponseCode;
import com.open.web.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/v2/user/")
@Log4j2
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("showAllUser")
    public @ResponseBody List<User> showAllUser(){
        return userService.getAllUser();
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/")
    public String toLogin(){
        return "login";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/toLogin")
    public String toLogin2(){
        return "login";
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param request  request
     * @return
     */
    @PostMapping("login")
    public ModelAndView login(@RequestParam(value = "username")String username,
                        @RequestParam(value = "password")String password,
                        HttpServletRequest request){

        ModelAndView mv = null;

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            mv = getModelAndView("login", ResponseCode.BAD_REQUEST,"用户名或密码不能为空");
            return mv;
        }

        User user = userService.getUserByUsername(username);
        if(null == user){
            mv = getModelAndView("login",ResponseCode.USER_NOT_EXIST,"该用户不存在");
            return mv;
        }

        String queryPassword = user.getPassword();
        if(null != queryPassword && queryPassword.equals(password)){
            mv = getModelAndView("index",ResponseCode.OK,"登录成功");

            // 将user放入session中，保持登录状态
            request.getSession().setAttribute("session_user",user);

            return mv;
        }else{
            mv = getModelAndView("login",ResponseCode.BAD_REQUEST,"密码错误");
            return mv;
        }
    }

    /**
     * 注册用户
     * @param user 用户信息
     * @return
     */
    @PostMapping("register")
    public ModelAndView register(@RequestBody User user){

        ModelAndView mv = null;

        //参数校验
        if(null == user) {
            mv = getModelAndView("register", ResponseCode.BAD_REQUEST, "用户信息不能为空");
            return mv;
        }

        if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            mv = getModelAndView("register", ResponseCode.BAD_REQUEST, "必填项信息不能为空");
            return mv;
        }

        try {
            userService.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
            mv = getModelAndView("register",ResponseCode.SERVER_ERROR,"添加用户时出现异常");
            return mv;
        }

        if(null == user.getId() || StringUtils.isEmpty(user.getId().toString())){
            mv = getModelAndView("register",ResponseCode.ADD_FAILED,"添加用户信息失败");
            return mv;
        }

        mv = getModelAndView("login",ResponseCode.OK,"添加用户成功");
        return mv;
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("session_user");
        return "login";
    }
}
