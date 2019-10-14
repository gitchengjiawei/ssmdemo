package com.open.web.controller;

import com.open.web.bean.User;
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
    @GetMapping("toLogin")
    public String toLogin(){
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
            mv = getModelAndView("login");
            mv.addObject("message","用户名或密码不能为空");
            return mv;
        }

        User user = userService.getUserByUsername(username);
        if(null == user){
            mv = getModelAndView("login");
            mv.addObject("message","该用户不存在");
            return mv;
        }

        String queryPassword = user.getPassword();
        if(null != queryPassword && queryPassword.equals(password)){
            mv = getModelAndView("index");
            mv.addObject("message","登录成功");

            // 将user放入session中，保持登录状态
            request.getSession().setAttribute("session_user",user);

            return mv;
        }else{
            mv = getModelAndView("login");
            mv.addObject("message","密码错误");
            return mv;
        }
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
