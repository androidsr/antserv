package cn.zft.antserv.controller;

import cn.zft.antserv.model.BaseVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExpHandler {
    private final static Logger LOG = LoggerFactory.getLogger(GlobalExpHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseVM.Response handleBizExp(HttpServletRequest request, Exception ex) {
        LOG.error("系统未知错误：" + ex.getMessage());
        ex.printStackTrace();
        return new BaseVM.Response("9999", "系统未知错误！");
    }
}