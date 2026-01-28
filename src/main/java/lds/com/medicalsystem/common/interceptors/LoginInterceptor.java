package lds.com.medicalsystem.common.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lds.com.medicalsystem.common.utils.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler){
        // 令牌验证
        String token = request.getHeader("Authorization");
        try {
            // 验证Token,解析返回的是map集合，不需要校验集合到底对不对，
            // 因为如果与原来生成Token负载的对象不一样的话会抛异常，压根不会解析出来
            Map<String, Object> claims = JWTUtil.parseToken(token);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }
}
