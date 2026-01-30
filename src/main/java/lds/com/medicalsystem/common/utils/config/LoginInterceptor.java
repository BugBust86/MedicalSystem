package lds.com.medicalsystem.common.utils.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Map;

// 组件实现这个接口完成全局拦截器的注册
@Component
public class LoginInterceptor implements HandlerInterceptor {
    // preHandle方法是全局拦截器最先执行的方法，注册的多个拦截器中只要有一个的preHandle方法返回false
    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler){
        // 令牌验证，将请求头中名为Authorization的字符串记录起来
        String token = request.getHeader("Authorization");
        try {
            // 验证Token,解析返回的是map集合，不需要校验集合到底对不对，
            // 因为如果与原来生成Token负载的对象不一样的话会抛异常，压根不会解析出来
            Map<String, Object> claims = JWTUtil.parseToken(token);

            // 把业务数据存储到ThreadLocal对象中
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }
    // 执行完拦截器的preHandle方法后，DispatcherServlet(派遣器微服务容器)会执行Controller，
    // 而后是postHandle(如果前面没抛异常的话），可对Controller返回的进行修改，最后是AfterCompletion关闭资源


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // 清空ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
