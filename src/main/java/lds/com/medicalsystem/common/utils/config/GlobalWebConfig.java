package lds.com.medicalsystem.common.utils.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 将拦截器注入配置类对象
    private final LoginInterceptor loginInterceptor;
    public WebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录、注册接口不拦截，因为注册不需要Token，而登录的目的恰恰是为了获得Token
        // 添加拦截器，将登录拦截器添加进去
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(
                        "/user/register",
                        "/user/login"
                        ,"/staff/registerBySelf",
                        "/staff/staffLogin");
    }
}
