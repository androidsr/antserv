package cn.zft.antserv.config;

import cn.zft.antserv.utils.Constants;
import cn.zft.antserv.utils.TokenManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtFilter extends GenericFilterBean {
    private static List<String> urls = new ArrayList<>();

    static {
        urls.add("/login/");
    }

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String token = TokenManager.getCookie(request, response, Constants.Authorization);
        String url = request.getServletPath();
        if (urls.contains(url)) {
            chain.doFilter(req, res);
            return;
        }
        if (token == null) {
            throw new ServletException("未获取到token信息");
        }

        try {
            Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
            long timeout = (System.currentTimeMillis() - claims.getIssuedAt().getTime()) / 1000 / 60;
            if (timeout > 60) {
                throw new ServletException("token已过期！");
            }
            if (timeout > 10) {
                String jwtToken = Jwts.builder().setSubject(claims.getSubject()).setIssuedAt(new Date()).claim("role", claims.get("role"))
                        .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
                TokenManager.setCookie(response, Constants.Authorization, jwtToken);
            }
            request.setAttribute("claims", claims);
        } catch (SignatureException e) {
            throw new ServletException("Invalid token");
        }
        chain.doFilter(request, response);
    }
}