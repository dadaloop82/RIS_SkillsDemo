package com.dadaloop.RISSkillDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * WebMvcConfig is a configuration class that implements WebMvcConfigurer interface
 * to provide custom configurations for Spring MVC. By annotating this class with
 * @Configuration, we declare it as a source of bean definitions. 
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Override the addResourceHandlers method to customize the serving of static resources.
     * Here, we specify where Spring MVC should look for static resources such as CSS, JS,
     * or image files. This setup is crucial when serving a Single Page Application (SPA)
     * built with a framework like React alongside a Spring Boot application.
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
