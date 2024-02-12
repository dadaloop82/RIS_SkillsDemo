package com.dadaloop.RISSkillDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     *
     * @param registry ResourceHandlerRegistry to configure resource handlers for serving
     *                 static resources.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // The "classpath:" prefix indicates that the resources are to be loaded from the classpath. 
        // The "file:" prefix is used to serve resources directly from the filesystem
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/public/",
                        "file:src/main/frontend/src/");
    }
}
