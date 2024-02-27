package com.example.usermanager.configs;

import com.example.usermanager.service.IUserDAO;
import com.example.usermanager.service.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example.usermanager")
public class AppConfiguration implements WebMvcConfigurer {
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/user/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Bean
    public IUserDAO userDAO(){
        return new UserDAO();
    }
}
