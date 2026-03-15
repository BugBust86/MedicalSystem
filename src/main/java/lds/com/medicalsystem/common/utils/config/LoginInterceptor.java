package lds.com.medicalsystem.common.utils.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Map;

// 组件实现这个接口完成全局拦截器的注册
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    // preHandle方法是全局拦截器最先执行的方法，注册的多个拦截器中只要有一个的preHandle方法返回false
    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler){
        // 关键：先放行 OPTIONS 预检请求
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 令牌验证，将请求头中名为Authorization的字符串记录起来
        String token = request.getHeader("Authorization");
        logger.info("拦截器捕获到的 token: {}", token);

        if (token == null || !token.startsWith("Bearer ")) {
            logger.info("Token 为空或格式错误，返回 401，请求路径：{}", request.getRequestURI());
            response.setStatus(401);
            return false;
        }
        try {
            // 解析时去掉 Bearer 前缀
            String actualToken = token.substring(7);  // 去掉 "Bearer "
            logger.info("去掉头部的token：{}",actualToken);
            // 验证Token,解析返回的是map集合，不需要校验集合到底对不对，
            // 因为如果与原来生成Token负载的对象不一样的话会抛异常，压根不会解析出来
            Map<String, Object> claims = JWTUtil.parseToken(actualToken);

            // 把业务数据存储到ThreadLocal对象中
            ThreadLocalUtil.set(claims);
            logger.info("Token 解析成功，工号：{}", claims.get("工号"));
            return true;
        } catch (Exception e) {
            logger.warn("Token 解析失败，请求路径：{}, 错误：{}", request.getRequestURI(), e.getMessage());
            logger.debug("Token 解析详细异常：", e);
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
