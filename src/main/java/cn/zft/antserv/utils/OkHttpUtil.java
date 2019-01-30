package cn.zft.antserv.utils;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class OkHttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    /**
     * 自定义请求格式（公共）
     *
     * @param request okhttp请求信息定义
     * @return body string字符串，失败返回""
     */
    public static String newNet(Request request) {
        Response response = null;
        try {
            OkHttpClient client = new OkHttpClient();
            response = client.newCall(request).execute();
            int status = response.code();
            if (status == 200) {
                String body = response.body().string();
                logger.error("http网络请求响应200：" + body);
                return body;
            } else if (response.isSuccessful()) {
                logger.error("http网络请求非正常响应：" + response.body().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("http网络请求异常信息：", e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }

    /**
     * GET方式提交表单数据
     *
     * @param url 访问地址
     * @return body string字符串，失败返回""
     */
    public static String newGetForm(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return newNet(request);
    }


    /**
     * POST方式提交表单数据
     *
     * @param url    访问地址
     * @param params 数据
     * @return body string字符串，失败返回""
     */
    public static String newPostForm(String url, Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        return newNet(request);
    }

    /**
     * POST方式提交json格式数据
     *
     * @param url     访问地址
     * @param jsonStr 数据
     * @return body string字符串，失败返回""
     */
    public static String newPostJson(String url, String jsonStr) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return newNet(request);
    }

    /**
     * POST方式提交xml格式数据
     *
     * @param url    访问地址
     * @param xmlStr 数据
     * @return body string字符串，失败返回""
     */
    public static String newPostXml(String url, String xmlStr) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xmlStr);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return newNet(request);
    }

}