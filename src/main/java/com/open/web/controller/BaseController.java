package com.open.web.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Description
 * @auther 程佳伟
 * @create 2019-10-12 23:45
 */
public class BaseController {

    /**
     * 简单的跳转到指定的叶脉呢
     * @param pageName 页面的名称
     * @return
     */
    public String toPage(String pageName){
        return pageName;
    }

    /**
     * 创建指定的 ModelAndView
     * @param url 跳转的页面
     * @return
     */
    public ModelAndView getModelAndView(String url){

        ModelAndView mv = new ModelAndView();

        HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();

        mv.addObject("params",parameterMap);
        mv.addObject("uri",request.getRequestURI());

        mv.setViewName(url);
        return mv;
    }
}
