package com.dlz.comm.util.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.*;
import org.dom4j.Document;

import java.util.Map;

import static com.dlz.comm.util.web.HttpUtil.doHttp;

/**
 * http操作
 */
@Slf4j
public enum HttpEnum {
    PUT,
    DELETE,
    UPDATEL,
    POST,
    TRACE,
    GET;

    private HttpRequestBase getRequest(String url) {
        switch (this.toString()) {
            case "GET":
                return new HttpGet(url);
            case "POST":
                return new HttpPost(url);
            case "DELETE":
                return new HttpDelete(url);
            case "TRACE":
                return new HttpTrace(url);
            case "OPTIONS":
                return new HttpOptions(url);
            case "PUT":
                return new HttpPut(url);
            default:
                return new HttpPost(url);
        }
    }
    public String send(HttpRequestParam param) {
        param.setReturnType(1);
        return (String) doHttp(getRequest(param.getUrl()), param);
    }
    public String send(String url, Map<String, Object> para) {
        return send(new HttpRequestParam(url, para));
    }
    public String send(String url) {
        return send(new HttpRequestParam(url));
    }

    public Document send4Dom(HttpRequestParam param) {
        param.setReturnType(2);
        try {
            return (Document) doHttp(getRequest(param.getUrl()), param);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public Document send4Dom(String url, Map<String, Object> para) {
        return send4Dom(new HttpRequestParam(url, para));
    }

    public Document send4Dom(String url) {
        return send4Dom(new HttpRequestParam(url));
    }
}
