package com.open.web.config;

import com.open.web.utils.PropertiesUtil;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @auther 程佳伟
 * @create 2019-10-15 19:56
 */
@Service
@Log4j2
public class ConfigMap {

    @Getter
    private static Map<String,String> map = new HashMap<>();


    @PostConstruct
    public static void loadConfig(){
        map = PropertiesUtil.getValues("config.properties");
        log.info("[|||||||] - Loading config map :{}",map);
    }
}
