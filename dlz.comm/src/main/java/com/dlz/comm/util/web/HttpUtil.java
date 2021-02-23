package com.dlz.comm.util.web;

import com.dlz.comm.exception.BussinessException;
import com.dlz.comm.exception.HttpException;
import com.dlz.comm.exception.SystemException;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.StringUtils;
import com.dlz.comm.util.ValUtil;
import com.dlz.comm.util.web.handler.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTTP相关的操作
 *
 * @author dk
 */
@Slf4j
public class HttpUtil {
    private static final ResponseHandler DEFAULT_RESPONSE_HANDLER = new ResponseHandler();

    /**
     * @param request
     * @param param
     * @return
     */
    public static HttpResponse executeHttp(HttpRequestBase request,
                                           HttpRequestParam param) {
        HttpClient httpClient = HttpConnUtil.wrapClient(param.getUrl());
        Map<String, String> headers = param.getHeaders();
        headers.forEach(request::addHeader);
        try {
            if (request instanceof HttpEntityEnclosingRequestBase) {
                String payLoad = param.getPayload();
                if (payLoad == null && !param.getPara().isEmpty()) {
                    if (param.getMimeType().equals(HttpConstans.MIMETYPE_FORM)) {
                        payLoad = buildUrl(param.getPara(), param.getCharsetNameRequest());
                    } else {
                        payLoad = JacksonUtil.getJson(param.getPara());
                    }
                }
                if (payLoad == null) {
                    payLoad = "";
                }
                ContentType contentType = ContentType.create(param.getMimeType(), param.getCharsetNameRequest());
                StringEntity entity = new StringEntity(payLoad, contentType);
                ((HttpEntityEnclosingRequestBase) request).setEntity(entity);
            } else if (!param.getPara().isEmpty()) {
                request.setURI(new URI(buildUrl(param.getUrl(), null, param.getPara(), param.getCharsetNameRequest())));
            }

            return httpClient.execute(request, param.getLocalContext());
        } catch (Exception e) {
            request.releaseConnection();
            String message = e.getMessage();
            String info = e.getClass().getName();
            if (StringUtils.isEmpty(message) && e.getCause() != null) {
                message = e.getCause().getMessage();
            }
            if (!StringUtils.isEmpty(message)) {
                info += ":" + message;
            }
            info += " -> executeUrl=" + request.getURI() + ",method=" + request.getMethod();
            throw new SystemException(info);
        }
    }

    /**
     * @param request
     * @param param
     * @return
     */
    public static Object doHttp(HttpRequestBase request,
                                HttpRequestParam param) {
        ResponseHandler responseHandler = param.getResponseHandler();
        if (responseHandler == null) {
            responseHandler = DEFAULT_RESPONSE_HANDLER;
        }
        Object result = null;
        try {
            HttpResponse execute = executeHttp(request, param);
            int statusCode = execute.getStatusLine().getStatusCode();
            result = responseHandler.handle(param, statusCode, execute);
        } catch (BussinessException | HttpException e) {
            throw e;
        } catch (SystemException e) {
            throw e;
        } catch (Exception e) {
            String info = "doHttp " + request.getMethod() + " Exception:" + e.getMessage() + " url:" + request.getURI();
            throw new SystemException(info, e);
        } finally {
            if (param.isShowLog() && log.isDebugEnabled()) {
                if (result instanceof Document) {
                    log.debug("doHttp " + request.getMethod() + " url:" + request.getURI() + " para:" + param.getPara() + " re:" + ((Document) result).asXML());
                } else {
                    log.debug("doHttp " + request.getMethod() + " url:" + request.getURI() + " para:" + param.getPara() + " re:" + result);
                }
            }
            request.releaseConnection();
        }
        return result;
    }

    public static class HttpConnUtil {
        private static final String HTTP = "http";
        private static final String HTTPS = "https";
        private static SSLConnectionSocketFactory sslsf = null;
        private static PoolingHttpClientConnectionManager cm = null;
        private static SSLContextBuilder builder = null;

        static {
            try {
                builder = new SSLContextBuilder();
                // 全部信任 不做身份鉴定
                builder.loadTrustMaterial(null, new TrustStrategy() {
                    @Override
                    public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        return true;
                    }
                });
                sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null,
                        NoopHostnameVerifier.INSTANCE);
                Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register(HTTP, new PlainConnectionSocketFactory())
                        .register(HTTPS, sslsf).build();
                cm = new PoolingHttpClientConnectionManager(registry);
                cm.setMaxTotal(200);// max connection
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static HttpClient wrapClient(String host) {
            HttpClientBuilder setConnectionManagerShared = HttpClientBuilder.create().setConnectionManager(cm).setConnectionManagerShared(true);
            if (host.startsWith(HTTPS)) {
                return setConnectionManagerShared.setSSLSocketFactory(sslsf).build();
            }
            return setConnectionManagerShared.build();
        }
    }

    public static String buildUrl(String host, String path, Map<String, Object> querys, String enc) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!StringUtils.isEmpty(path)) {
            sbUrl.append(path);
        }
        if (null != querys) {
            String s = buildUrl(querys, enc);
            if (0 < s.length()) {
                sbUrl.append(sbUrl.indexOf("?") > -1 ? "&" : "?").append(s);
            }
        }
        return sbUrl.toString();
    }

    public static String buildUrl(Map<String, Object> querys, String enc) throws UnsupportedEncodingException {
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, Object> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isEmpty(query.getKey()) && !StringUtils.isEmpty(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isEmpty(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isEmpty(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(ValUtil.getStr(query.getValue()), enc));
                    }
                }
            }
            return sbQuery.toString();
        }
        return "";
    }
}
