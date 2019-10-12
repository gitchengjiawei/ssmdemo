package com.open.web.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

/** 
* @author: wkn
* @date：2019年9月23日 上午11:14:15 
* 读取properties工具类
*/
public class PropertiesUtil {
	private PropertiesUtil() {
		
	}
	 /**
     * 根据key读取value
     * @param filePath
     * @param key
     * @return
     */
    public static String readValue(String filePath,String key) {
		Properties prop = null;
		String value = null;
		try {
			// 通过Spring中的PropertiesLoaderUtils工具类进行获取
			prop = PropertiesLoaderUtils.loadAllProperties(filePath);
			// 根据关键字查询相应的值
			value = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
    }
}
