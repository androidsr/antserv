package cn.zft.antserv.controller;

import cn.zft.antserv.model.BaseVM;
import cn.zft.antserv.model.LoginVM;
import cn.zft.antserv.model.PasswdVM;
import cn.zft.antserv.service.LoginService;
import cn.zft.antserv.utils.Constants;
import cn.zft.antserv.utils.ResponseCode;
import cn.zft.antserv.utils.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class SysLoginController {
    @Autowired
    LoginService loginService;

    @PostMapping
    public BaseVM.Response login(@RequestBody LoginVM.Request entity, HttpServletResponse response) {
        if (StringUtils.isEmpty(entity.getUserId()) || StringUtils.isEmpty(entity.getPasswd()))
            return ResponseCode.getBaseVM("00001");

        return loginService.login(entity, response);
    }

    @PutMapping
    public BaseVM.Response update(@RequestBody PasswdVM.Request entity, HttpServletRequest req, HttpServletResponse res) {
        if (StringUtils.isEmpty(entity.getPasswd()) || StringUtils.isEmpty(entity.getNewPasswd()))
            return ResponseCode.getBaseVM("00400");

        BaseVM.Response result = loginService.updatePwd(entity);
        if ("00200".equals(result.getCode())) {
            TokenManager.delCookie(req, res, Constants.Authorization);
        }
        return result;
    }

    @DeleteMapping
    public BaseVM.Response logout(HttpServletRequest req, HttpServletResponse res) {
        TokenManager.delCookie(req, res, Constants.Authorization);
        return ResponseCode.getBaseVM("00200");
    }

}