package com.shareblog.filter;

import com.shareblog.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName JwtFilter
 * @Author 杨武军
 * @Date 2020/5/18 18:04
 * 拦截器，访问前拦截
 */
@Component
@ConfigurationProperties(prefix = "login.config")
@Data
public class JwtFilter extends HandlerInterceptorAdapter {
    private String url;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //放行optoins请求
        if (RequestMethod.OPTIONS.name().equals(request.getMethod())){
            return true;
        }
        // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
        response.setHeader("Access-Control-Expose-Headers", "*");
        String token = request.getHeader("Authorization");
        System.out.println(token);
        //未登录
        if ("null".equals(token) || StringUtils.isEmpty(token)) {
            reDirect(request, response);
            return false;
        } else {
            Claims claims = jwtUtil.parseJWT(token);
            if ("admin".equals(claims.get("role"))) {
                request.setAttribute("admin_claims", claims);
            }
            if ("editor".equals(claims.get("role"))) {
                request.setAttribute("editor_claims", claims);
            }
            if ("user".equals(claims.get("role"))) {
                request.setAttribute("user_claims", claims);
            }
        }
        return true;
    }

    //对于请求是ajax请求重定向问题的处理方法
    public void reDirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取当前请求的路径
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            //告诉ajax我是重定向
            response.setHeader("REDIRECT", "REDIRECT");
            //告诉ajax我重定向的路径
            response.setHeader("CONTENTPATH", url);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.sendRedirect(url);
        }
    }

}
