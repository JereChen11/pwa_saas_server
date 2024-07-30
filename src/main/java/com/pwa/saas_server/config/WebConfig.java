package com.pwa.saas_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jere
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 路径指定，这里表示配置所有路径
                .allowedOrigins("*") // 域名指定，这里表示所有
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的方法
                .allowedHeaders("*") // 允许的请求头
                .allowCredentials(true) // 是否允许发送凭证(如 Cookie）
                .maxAge(3600); // 预检请求的缓存时间（以秒为单位）
    }
}
