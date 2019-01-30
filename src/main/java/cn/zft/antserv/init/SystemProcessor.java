package cn.zft.antserv.init;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SystemProcessor {

    @PostConstruct
    public void init() throws Exception {
        System.out.println("初始化参数");

    }
}
