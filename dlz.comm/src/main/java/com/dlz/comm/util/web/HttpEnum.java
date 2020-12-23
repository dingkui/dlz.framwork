package com.dlz.comm.util.web;

import com.dlz.comm.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.*;
import org.dom4j.Document;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static com.dlz.comm.util.web.HttpUtil.doHttp;

/**
 * http操作
 */
@Slf4j
public enum HttpEnum {
    GET(HttpGet.class),
    POST(HttpPost.class),
    PUT(HttpPut.class),
    DELETE(HttpDelete.class),
    OPTIONS(HttpOptions.class),
    HEAD(HttpHead.class),
    PATCH(HttpPatch.class),
    TRACE(HttpTrace.class);

    Constructor c1;
    HttpEnum(Class<? extends HttpRequestBase> clazz){
        try {
            c1 = clazz.getDeclaredConstructor(new Class[]{String.class});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private HttpRequestBase getRequest(String url) {
        try {
            return (HttpRequestBase)c1.newInstance(url);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new SystemException("不支持的http类型："+this.toString());
    }
    public String send(HttpRequestParam param) {
        param.setReturnType(1);
        return (String) doHttp(getRequest(param.getUrl()), param);
    }
    public String send(String url, Map<String, Object> para) {
        return send(HttpRequestParam.createFormReq(url,para));
    }
    public String send(String url) {
        return send(HttpRequestParam.createFormReq(url,null));
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
        return send4Dom(HttpRequestParam.createFormReq(url,para));
    }

    public Document send4Dom(String url) {
        return send4Dom(HttpRequestParam.createFormReq(url,null));
    }
}
