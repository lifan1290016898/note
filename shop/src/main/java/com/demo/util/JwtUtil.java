package com.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static Logger log = LoggerFactory.getLogger(JwtUtil.class);
    /**
     * 秘钥
     */
    public static final String SECRET = "token-private-key";
    /**
     * token 过期时间: 15分钟
     */
    public static final int calendarField = Calendar.MINUTE;
    public static final int calendarInterval = 15;

    /**
     * JWT生成Token.
     * @param id 登录成功后用户id, 参数id不可传空
     */
    public static String createToken(Integer id, String name, String password) throws Exception {
        Date iatDate = new Date();
        // 过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        /**
         * iss: jwt签发者
         * sub: jwt所面向的用户
         * aud: 接收jwt的一方
         * exp: jwt的过期时间，这个过期时间必须要大于签发时间
         * nbf: 定义在什么时间之前，该jwt都是不可用的.
         * iat: jwt的签发时间
         * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
         */
        String token = JWT.create().withHeader(map)
//                .withClaim("iss", "service")
//                .withClaim("aud", "APP")
                .withClaim("name", name)
//                .withClaim("password", password)
                .withClaim("id", null == id ? null : id.toString())
                .withIssuedAt(iatDate)
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return jwt.getClaims();
    }

    /**
     * 根据Token获取user
     * @param token
     */
    public static Map<String, Object> getUser(String token) {
        Map<String, Claim> claims = verifyToken(token);
        Map<String, Object> loginUser = new HashMap<>();
        loginUser.put("id", claims.get("id").asInt());
        loginUser.put("name", claims.get("name").asString());
//        loginUser.put("password", claims.get("password").asString());
        return loginUser;
    }
}

