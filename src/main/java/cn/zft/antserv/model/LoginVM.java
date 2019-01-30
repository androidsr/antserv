package cn.zft.antserv.model;

import lombok.Getter;
import lombok.Setter;

public class LoginVM {

    @Getter
    @Setter
    public static class Request extends BaseVM.Request {
        private String userId;
        private String passwd;
    }

    @Getter
    @Setter
    public static class Response extends BaseVM.Response {
        private String userId;
        private String userName;

        public Response() {
        }

        public Response(String code, String msg, String token) {
            this.token = token;
            this.code = code;
            this.msg = msg;
        }

        public Response(String token) {
            this.token = token;
        }

        private String token;
    }

}
