package com.tonggu.provider.us.common.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * 腾讯登陆的转换器
 * @author renshaw
 *
 */
public class TencentHttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public TencentHttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
        
        /**
         * 微信登陆时返回值格式是text/plain,需要添加此MediaType
         */
        mediaTypes.add(MediaType.TEXT_PLAIN);
        
        /**
         * QQ登陆时返回值格式是text/html,需要添加此MediaType
         */
        mediaTypes.add(MediaType.TEXT_HTML);
        
        setSupportedMediaTypes(mediaTypes);
    }
}
