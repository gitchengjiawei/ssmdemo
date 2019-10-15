package com.open.web.controller;

import com.open.web.config.ConfigMap;
import com.open.web.constant.ResponseCode;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description
 * @auther 程佳伟
 * @create 2019-10-15 21:36
 */
@Controller
@RequestMapping("/test/")
public class TestController extends BaseController {

    @GetMapping("testConfig")
    public ModelAndView testConfig(){
        System.out.println("request /test/testConfig map:" + ConfigMap.getMap());
        System.out.println("request /test/testConfig map:" + ConfigMap.getMap());
        System.out.println("request /test/testConfig map:" + ConfigMap.getMap());
        System.out.println("request /test/testConfig map:" + ConfigMap.getMap());
        return getModelAndView("login", ResponseCode.OK,"success");
    }
}
