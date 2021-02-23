package com.dlz.comm.util.web;

import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.ValUtil;
import com.dlz.comm.util.web.handler.ResponseHandler;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.protocol.HttpClientContext;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP相关的操作参数
 *
 * @author dk
 */
@Getter
@Setter
public class HttpRequestParam<T> {
    private static final ResponseHandler DEFAULT_RESPONSE_HANDLER = new ResponseHandler();
    private final String url;
    private final Class<T> tClass;
    private final Map<String, String> headers = new HashMap<>();

    private ResponseHandler responseHandler = DEFAULT_RESPONSE_HANDLER;
    /**
     * 参数
     */
    private JSONMap para = new JSONMap();
    /**
     * 使用payload
     */
    private String payload;

    /**
     * 请求参数
     */
    private String charsetNameRequest = HttpConstans.CHARSET_UTF8;

    private String mimeType = HttpConstans.MIMETYPE_FORM;
    /**
     * 返回参数
     */
    private String charsetNameResponse = HttpConstans.CHARSET_UTF8;
    /**
     * 是否显示日志
     */
    private boolean showLog = false;

    private HttpClientContext localContext = new HttpClientContext();



    public static HttpRequestParam<String> createJsonReq(String url, Object para) {
        return createJsonReq(url, para, String.class);
    }

    public static HttpRequestParam<String> createTextReq(String url, Object para) {
        return createTextReq(url, para, String.class);
    }

    public static HttpRequestParam<String> createFormReq(String url, Object para) {
        return createFormReq(url, para, String.class);
    }

    public static <T> HttpRequestParam<T> createJsonReq(String url, Object para, Class<T> tClass) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(url, tClass);
        httpRequestParam.mimeType = HttpConstans.MIMETYPE_JSON;
        if (para != null) {
            httpRequestParam.payload = ValUtil.getStr(para);
        }
        return httpRequestParam;
    }

    public static <T> HttpRequestParam<T> createTextReq(String url, Object para, Class<T> tClass) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(url, tClass);
        httpRequestParam.mimeType = HttpConstans.MIMETYPE_TEXT;
        if (para != null) {
            httpRequestParam.payload = ValUtil.getStr(para);
        }
        return httpRequestParam;
    }

    public static <T> HttpRequestParam<T> createFormReq(String url, Object para, Class<T> tClass) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(url, tClass);
        httpRequestParam.mimeType = HttpConstans.MIMETYPE_FORM;
        if (para != null) {
            httpRequestParam.para = new JSONMap(para);
        }
        return httpRequestParam;
    }

    public HttpRequestParam(String url, Class<T> tClass) {
        this.url = url;
        this.tClass = tClass;
    }

    public HttpRequestParam<T> addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }
}
