package com.example.springbootdemo;

import com.example.springbootdemo.config.fiter.CheckSessionOutFilter;
import com.example.springbootdemo.config.interceptors.CheckInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.FilterRegistration;

@SpringBootApplication
/*TODO 设置mybatis的mapper文件的路径*/
@MapperScan("com.example.springbootdemo.mapper")
/*todo 开启定时任务支持*/
@EnableScheduling
public class SpringbootdemoApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean getFilterRegistration(){
        FilterRegistrationBean filterRegistrationBean =new FilterRegistrationBean();
        filterRegistrationBean.setFilter(getCheckSessionOutFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("sessionTimeOutFilter");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

    @Bean
    public CheckSessionOutFilter getCheckSessionOutFilter(){
        return new CheckSessionOutFilter();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CheckInterceptor()).addPathPatterns("/*").excludePathPatterns("/login/login");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
