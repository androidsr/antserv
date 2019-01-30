package cn.zft.antserv.service.impl;

import cn.zft.antserv.entity.SysUsers;
import cn.zft.antserv.model.BaseVM;
import cn.zft.antserv.model.LoginVM;
import cn.zft.antserv.model.PasswdVM;
import cn.zft.antserv.service.LoginService;
import cn.zft.antserv.service.SysUsersService;
import cn.zft.antserv.utils.Constants;
import cn.zft.antserv.utils.ResponseCode;
import cn.zft.antserv.utils.TokenManager;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    SysUsersService sysUsersService;

    @Override
    public BaseVM.Response login(LoginVM.Request entity, HttpServletResponse response) {
        SysUsers user = sysUsersService.get(entity.getUserId());
        if (user == null) {
            return ResponseCode.getBaseVM("00002");
        }
        if (!user.getPasswd().equals(entity.getPasswd())) {
            return ResponseCode.getBaseVM("00003");
        }

        String jwtToken = Jwts.builder().setSubject(entity.getUserId())
                .setIssuedAt(new Date()).claim("role", user.getRoleId())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        TokenManager.setCookie(response, Constants.Authorization, jwtToken);
        LoginVM.Response result = new LoginVM.Response(Constants.SUCC, "认证成功", jwtToken);

        try {
            result.setUserId(Base64.getEncoder().encodeToString(entity.getUserId().getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        result.setUserName(user.getName());
        return result;
    }

    @Override
    public BaseVM.Response updatePwd(PasswdVM.Request entity) {
        SysUsers user = sysUsersService.get(entity.getUserId());
        if (user == null) {
            try {
                user = sysUsersService.get(new String(Base64.getDecoder().decode(entity.getUserId()), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
            }
            if (user == null) {
                return ResponseCode.getBaseVM("00002");
            }
        }
        if (!user.getPasswd().equals(entity.getPasswd())) {
            return ResponseCode.getBaseVM("00003");
        }
        user.setPasswd(entity.getNewPasswd());
        sysUsersService.update(user);
        return ResponseCode.getBaseVM("00200");
    }
}
