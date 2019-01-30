package cn.zft.antserv.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SelectVM {
    @Getter
    @Setter
    public static class Request extends BaseVM.Request {
        private String value;
        private Integer cur;
        private Integer size;
        private Integer total;

        public void configPage() {
            cur = (cur - 1) * size;
        }
    }

    @Getter
    @Setter
    public static class Response extends BaseVM.Response {
        List<Select> data;
    }

    @Getter
    @Setter
    public static class Select {
        private String keyName;
        private String titleName;
    }
}
