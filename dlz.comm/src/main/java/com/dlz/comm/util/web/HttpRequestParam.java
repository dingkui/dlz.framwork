package com.dlz.comm.util.web;

import lombok.Getter;
import org.apache.http.client.protocol.HttpClientContext;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP相关的操作参数
 *
 * @author dk
 */
@Getter
public class HttpRequestParam {
    private String url;
    /**
     * 参数
     */
    private Map<String, Object> para = new HashMap<>();
    /**
     * 使用payload
     */
    private String payload;
    private Map<String, String> headers = new HashMap<>();
    /**
     * 请求参数
     */
    private String charsetNameRequest = HttpConstans.CHARSET_UTF8;
    private String contentType = HttpConstans.CONTENTTYPE_UTF8;
    /**
     * 返回参数
     */
    private String charsetNameResponse = HttpConstans.CHARSET_UTF8;
    private boolean showLog = false;
    /**
     * 返回类型 1 字符串 2 xml
     */
    private int returnType = 1;
    private HttpClientContext localContext = new HttpClientContext();

    public HttpRequestParam(String url) {
        this.url = url;
    }

    public HttpRequestParam(String url, String payload) {
        this.url = url;
        this.payload = payload;
    }

    public HttpRequestParam(String url, String payload, Map<String, String> headers) {
        this.url = url;
        this.payload = payload;
        if (headers != null) {
            this.headers.putAll(headers);
        }
    }


    public HttpRequestParam(String url, Map<String, Object> para) {
        this.url = url;
        if (para != null) {
            this.para.putAll(para);
        }
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void addPara(String key, Object value) {
        para.put(key, value);
    }

    public void setCharsetNameRequest(String charsetNameRequest) {
        this.charsetNameRequest = charsetNameRequest;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setCharsetNameResponse(String charsetNameResponse) {
        this.charsetNameResponse = charsetNameResponse;
    }

    public void setShowLog(boolean showLog) {
        this.showLog = showLog;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }

    public void setLocalContext(HttpClientContext localContext) {
        this.localContext = localContext;
    }
}
