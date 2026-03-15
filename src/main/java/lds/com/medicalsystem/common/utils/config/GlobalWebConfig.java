package lds.com.medicalsystem.common.utils.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// 全局统一的配置类，配置拦截器 和 跨域跨域配置
@Configuration
public class GlobalWebConfig implements WebMvcConfigurer {
    // 将拦截器注入配置类对象
    private final LoginInterceptor loginInterceptor;
    public GlobalWebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    // 静态资源映射配置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")   // 设置的路径符号
                .addResourceLocations("file:./uploads/");       // 设置的放置文件的目录
    }

    // 需要添加的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录、注册接口不拦截，因为注册不需要Token，而登录的目的恰恰是为了获得Token
        // 添加拦截器，将登录拦截器添加进去
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(
                        "/user/register",
                        "/user/login"
                        ,"/staff/registerBySelf",
                        "/staff/staffLogin",
                        "/images/**");  // 放行静态资源
    }
    // 全局跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // 允许所有前端域名
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // 允许的请求方法
                .allowedHeaders("*") // 允许所有请求头（包括你加的 Authorization）
                .allowCredentials(true) // 允许携带 Cookie
                .maxAge(3600); // 预检请求的缓存时间（1小时，避免频繁发 OPTIONS）
    }
}
