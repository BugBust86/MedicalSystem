package lds.com.medicalsystem.common.utils.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

// 获取token，解析token的工具
public class JWTUtil {
    private static final String key = "lds";

    // 接收业务数据生成Token并返回
    public static String genToke(Map<String, Object> claims){
        return JWT.create()
                .withClaim("claims", claims)    // token第二部分承载的负载，即唯一标识的键值对数据
                .withExpiresAt(new Date(System.currentTimeMillis()+ 1000*60*60*10))    // 有效时间，10个小时
                .sign(Algorithm.HMAC256(key));      // 加密算法和自己设置的密钥
    }

    // 接收Token，验证Token，并返回业务数据
    public static Map<String, Object> parseToken(String token){
        return JWT.require(Algorithm.HMAC256(key))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
