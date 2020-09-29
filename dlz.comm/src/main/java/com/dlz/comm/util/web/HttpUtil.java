package com.dlz.comm.util.web;

import com.dlz.comm.exception.BussinessException;
import com.dlz.comm.exception.HttpException;
import com.dlz.comm.exception.SystemException;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.StringUtils;
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
    /**
     * @param request
     * @param param
     * @return
     * @throws Exception
     */
    public static Object doHttp(HttpRequestBase request,
                                HttpRequestParam param) {
        HttpClient httpClient = HttpConnUtil.wrapClient(param.getUrl());
        param.getHeaders().entrySet().forEach(e -> request.addHeader(e.getKey(), e.getValue()));

        Object result = null;
        try {
            HttpEntity entity = null;
            if (request instanceof HttpEntityEnclosingRequestBase) {
                if (!param.getPara().isEmpty()) {
                    List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
                    param.getPara().entrySet().forEach(e -> nameValuePairList.add(new BasicNameValuePair(e.getKey(), JacksonUtil.getJson(e.getValue()))));
                    entity = new UrlEncodedFormEntity(nameValuePairList, param.getCharsetNameRequest());
                } else if (param.getPayload() != null) {
                    entity = new StringEntity((param.getPayload()), param.getCharsetNameRequest());
                }
                ((StringEntity) entity).setContentType(param.getContentType());
                ((HttpEntityEnclosingRequestBase) request).setEntity(entity);
            } else if (!param.getPara().isEmpty()) {
                request.setURI(new URI(buildUrl(param.getUrl(), null, param.getPara(), param.getCharsetNameRequest())));
            }

            HttpResponse execute = httpClient.execute(request, param.getLocalContext());
            int statusCode = execute.getStatusLine().getStatusCode();
            switch (statusCode) {
                case HttpStatus.SC_OK:
                case HttpStatus.SC_CREATED:
                case HttpStatus.SC_ACCEPTED:
                    result = getResult(execute.getEntity().getContent(), param.getReturnType(), param.getCharsetNameResponse());
                    return result;
                case HttpStatus.SC_NOT_FOUND:
                    throw new HttpException("地址无效:" + request.getURI(), statusCode);
                case HttpStatus.SC_UNAUTHORIZED:
                case HttpStatus.SC_FORBIDDEN:
                    throw new HttpException("无访问权限:" + request.getURI(), statusCode);
                case HttpStatus.SC_MOVED_TEMPORARILY:
                    result= getResult(execute.getEntity().getContent(), 1, param.getCharsetNameResponse());
                    throw new BussinessException("访问错误:" + result);
                default:
                    result= getResult(execute.getEntity().getContent(), 1, param.getCharsetNameResponse());
                    if (statusCode > 3000 && statusCode < 3100) {
                        throw new BussinessException(statusCode, (String)result, null);
                    } else {
                        log.error("访问异常:" + request.getURI() + " 返回码:" + statusCode+" msg:"+result);
                        throw new HttpException((String)result, statusCode);
                    }
            }
        } catch (BussinessException e) {
            throw e;
        } catch (HttpException e) {
            throw e;
        } catch (Exception e) {
            String info = "doHttp " + request.getMethod() + " Exception:" + e.getMessage() + " url:" + request.getURI();
            throw new SystemException(info,e);
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
    }

    private static Object getResult(InputStream inputStream, int returnType, String charsetNamere) throws Exception {
        if (returnType == 1) {
            StringBuffer sb = new StringBuffer();
            byte[] buffer = new byte[4096];
            int read = 0;
            while (read != -1) {
                read = inputStream.read(buffer);
                if (read > 0) {
                    sb.append(new String(buffer, 0, read, charsetNamere));
                }
            }

//			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
//			String temp = new String(in.readLine().getBytes(charsetNamere));
//			StringBuffer sb=new StringBuffer();
//			/* 连接成一个字符串 */
//			while (temp != null) {
//				sb.append(temp);
//				temp = in.readLine();
//			}
            return sb.toString();
        } else if (returnType == 2) {
            //获取输入流
            return new SAXReader().read(inputStream);
        }
        return null;
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
            if (host.startsWith("https://")) {
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
                        sbQuery.append(URLEncoder.encode(JacksonUtil.getJson(query.getValue()), enc));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append(sbUrl.indexOf("?") > -1 ? "&" : "?").append(sbQuery);
            }
        }
        return sbUrl.toString();
    }
}
