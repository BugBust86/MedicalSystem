package lds.com.medicalsystem.user;

import lds.com.medicalsystem.common.utils.config.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Token 信息提取工具类
 * 从 ThreadLocal 中提取 JWT Token 解析后的用户信息
 */
public class UserTokenUtils {
    private static final Logger logger = LoggerFactory.getLogger(UserTokenUtils.class);
    /**
     * 从 ThreadLocal 获取用户 ID
     * @return 用户 ID，如果不存在返回 null
     */
    public static int getUserId() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        if (claims == null) {
            logger.warn("ThreadLocal 中无数据，可能是拦截器未执行或 Token 解析失败");
            throw new RuntimeException("ThreadLocal为空");
        }
        Object userIdObj = claims.get("userId");
        if (userIdObj == null) {
            logger.warn("Token 中缺少 userId 字段，可能是医生或管理员 Token");
            throw new RuntimeException("token存储的信息中没有userId");
        }
        
        try {
            return (int) userIdObj;
        } catch (ClassCastException e) {
            logger.error("userId 类型转换失败，实际类型：{}", userIdObj.getClass().getName(), e);
            throw new RuntimeException("解析出来的userId不是int类型");
        }
    }

    /**
     * 从 ThreadLocal 获取手机号
     * @return 手机号，如果不存在返回 null
     */
    public static String getPhone() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        if (claims == null) {
            logger.warn("ThreadLocal 中无数据，可能是拦截器未执行或 Token 解析失败");
            throw new RuntimeException("ThreadLocal为空");
        }
        
        Object phoneObj = claims.get("phone");
        if (phoneObj == null) {
            logger.warn("Token 中缺少 phone 字段");
            throw new RuntimeException("token存储的信息中没有phone");
        }
        
        try {
            return (String) phoneObj;
        } catch (ClassCastException e) {
            logger.error("phone 类型转换失败，实际类型：{}", phoneObj.getClass().getName(), e);
            throw new RuntimeException("解析出来的phone不是String类型");
        }
    }

    /**
     * 从 ThreadLocal 获取所有 Token 信息
     * @return Token 信息 Map，如果不存在返回 null
     */
    public static Map<String, Object> getAllClaims() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        if (claims == null) {
            logger.warn("ThreadLocal 中无数据，可能是拦截器未执行或 Token 解析失败");
            return null;
        }
        return claims;
    }

    /**
     * 获取指定的 Token 信息
     * @param key 键名
     * @return 对应的值，如果不存在返回 null
     */
    public static Object getClaim(String key) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        if (claims == null) {
            return null;
        }
        return claims.get(key);
    }
}
