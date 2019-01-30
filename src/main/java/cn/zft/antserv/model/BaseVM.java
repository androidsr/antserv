package cn.zft.antserv.model;

import lombok.Getter;
import lombok.Setter;


public class BaseVM {

    @Getter
    @Setter
    public static class Request {
        private String token;
    }

    @Getter
    @Setter
    public static class Response {
        public String code;
        public String msg;

        public Response() {
        }

        public Response(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }


}
