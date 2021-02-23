package com.dlz.comm.util.web;

import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.ValUtil;
import com.dlz.comm.util.web.handler.ResponseHandler;
import lombok.Generated;
import lombok.Getter;
import org.apache.http.client.protocol.HttpClientContext;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP相关的操作参数
 *
 * @author dk
 */
public class HttpRequestParam<T> {
    private String url;

    public ResponseHandler getResponseHandler() {
        return responseHandler;
    }

    public void setResponseHandler(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    private ResponseHandler responseHandler;

    public Class<T> gettClass() {
        return tClass;
    }

    private Class<T> tClass;
    /**
     * 参数
     */
    private JSONMap para = new JSONMap();
    /**
     * 使用payload
     */
    private String payload;
    private Map<String, String> headers = new HashMap<>();
    /**
     * 请求参数
     */
    private String charsetNameRequest = HttpConstans.CHARSET_UTF8;

    private String mimeType = HttpConstans.MIMETYPE_FORM;
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
        HttpRequestParam httpRequestParam = new HttpRequestParam(url,tClass);
        httpRequestParam.mimeType = HttpConstans.MIMETYPE_JSON;
        if (para != null) {
            httpRequestParam.payload = ValUtil.getStr(para);
        }
        return httpRequestParam;
    }

    public static <T> HttpRequestParam<T> createTextReq(String url, Object para, Class<T> tClass) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(url,tClass);
        httpRequestParam.mimeType = HttpConstans.MIMETYPE_TEXT;
        if (para != null) {
            httpRequestParam.payload = ValUtil.getStr(para);
        }
        return httpRequestParam;
    }

    public static <T> HttpRequestParam<T> createFormReq(String url, Object para, Class<T> tClass) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(url,tClass);
        httpRequestParam.mimeType = HttpConstans.MIMETYPE_FORM;
        if (para != null) {
            httpRequestParam.para = new JSONMap(para);
        }
        return httpRequestParam;
    }

    private HttpRequestParam(String url, Class<T> tClass) {
        this.url = url;
        this.tClass = tClass;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void setCharsetNameRequest(String charsetNameRequest) {
        this.charsetNameRequest = charsetNameRequest;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
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

    protected JSONMap getPara() {
        return para;
    }

    public String getUrl() {
        return url;
    }

    public String getPayload() {
        return payload;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getCharsetNameRequest() {
        return charsetNameRequest;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getCharsetNameResponse() {
        return charsetNameResponse;
    }

    public boolean isShowLog() {
        return showLog;
    }

    public int getReturnType() {
        return returnType;
    }

    public HttpClientContext getLocalContext() {
        return localContext;
    }
}
