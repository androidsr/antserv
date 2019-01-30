package cn.zft.antserv.utils;

import cn.zft.antserv.model.BaseVM;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ResponseCode {
    private static Properties props;

    static {
        loadProps();
    }

    synchronized static private void loadProps() {
        props = new Properties();
        InputStream in = null;
        try {
            in = ResponseCode.class.getClassLoader().getResourceAsStream("config/code.properties");
            props.load(new InputStreamReader(in, "utf-8"));
            in.close();
        } catch (IOException e) {
        }
    }

    public static String getCode(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static BaseVM.Response getBaseVM(String key, String... inserts) {
        if (null == props) {
            loadProps();
        }
        String value = props.getProperty(key);
        for (int i = 0; i < inserts.length; i++) {
            value = value.replace("{" + i + "}", inserts[i]);
        }
        return new BaseVM.Response(key, value);
    }
}