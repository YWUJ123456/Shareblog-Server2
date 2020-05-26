package com.shareblog.utils;

/**
 * @ClassName JwtUtil
 * @Author 杨武军
 * @Date 2020/5/18 16:43
 */

import com.shareblog.bean.Role;
import com.shareblog.bean.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * jwt工具类
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt.config")
public class JwtUtil {
    private String key = "yangwujun";
    private Long ttl;

    /**
     * 生成JWT
     * @param user
     * @return
     */
    public String createJWT(User user, Role role){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(user.getId()+"")
                .signWith(SignatureAlgorithm.HS256,key)
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .claim("role",role.getName())
                .claim("userName",user.getUsername());
        if (ttl>0){
            builder.setExpiration(new Date(nowMillis+ttl));//设置过期时间
        }
        return builder.compact();
    }

    public Claims parseJWT(String jwtStr){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }
}
