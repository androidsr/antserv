package cn.zft.antserv.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;

public class TokenManager {

    public static String getCookie(HttpServletRequest request, HttpServletResponse res, String key) {
        if (StringUtils.isEmpty(key)) {
            key = Constants.Authorization;
        }
        if (res != null) {
            Collection<String> setCookies = res.getHeaders("Set-Cookie");
            if (setCookies.size() != 0) {
                for (String str : setCookies) {
                    if (str.startsWith(key + "=")) {
                        String token = str.split("=", key.length() + 2)[1];
                        if (token.indexOf(";") != -1) {
                            token = token.substring(0, token.indexOf(";"));
                        }
                        return token;
                    }
                }
            }
        }
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (key.equals(c.getName())) {
                        token = c.getValue();
                        break;
                    }
                }
            }
        }
        return token;
    }

    public static void setCookie(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    public static void delCookie(HttpServletRequest req, HttpServletResponse res, String key) {
        Cookie[] cookies = req.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    cookie.setValue(cookie.getValue());
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    cookie.setHttpOnly(true);
                    res.addCookie(cookie);
                    break;
                }
            }
        }
    }
}