package cn.zft.antserv.service;

import cn.zft.antserv.model.BaseVM;
import cn.zft.antserv.model.LoginVM;
import cn.zft.antserv.model.PasswdVM;

import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    BaseVM.Response login(LoginVM.Request entity, HttpServletResponse response);

    BaseVM.Response updatePwd(PasswdVM.Request entity);
}
