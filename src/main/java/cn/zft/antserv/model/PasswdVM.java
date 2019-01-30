package cn.zft.antserv.model;

import lombok.Getter;
import lombok.Setter;

public class PasswdVM {

    @Getter
    @Setter
    public static class Request extends BaseVM.Request {
        private String userId;
        private String passwd;
        private String newPasswd;
    }

}
