package lds.com.medicalsystem;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {

    @Test
    public void getToken() {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("工号", "D2022001");
        claims.put("role", "医生");
        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .sign(Algorithm.HMAC256("lds"));
        System.out.println(token);
    }

    @Test
    public void verifyToken() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoi5byg5LiJIn0sImV4cCI6MTc2OTgwMDU3NH0." +
                "xprJE-MxlW8H55IR8uIgGbbFnkWYE5SwZx684Pppx6E";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("lds")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);  // 解析后的JWT对象

        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }
}
