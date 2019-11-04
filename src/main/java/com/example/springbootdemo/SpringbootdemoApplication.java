package com.example.springbootdemo;

import com.example.springbootdemo.config.fiter.CheckSessionOutFilter;
import com.example.springbootdemo.config.interceptors.CheckInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


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

    /**
     * @Method
     * @Author limingxing
     * @Version  1.0
     * @Description
     * @param null
     * @Return
     * @Exception todo 因为继承了webmvcconfigtionsupport所以springboot的默认配置失效，
     *  todo 导致静态资源无法访问，所以重写此方法，添加静态资源的访问权限
     * @Date 2019/9/5 12:00
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
