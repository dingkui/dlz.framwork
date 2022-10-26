package com.dlz.comm.util.web;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

public class HttpConnUtil {
    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLContextBuilder builder = null;

    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, (TrustStrategy) (x509Certificates, s) -> true);
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

    public static HttpClient wrapClient(String host,RequestConfig requestConfig) {
        HttpClientBuilder clientBuilder = HttpClientBuilder.create().setConnectionManager(cm).setConnectionManagerShared(true);
        if(requestConfig != null){
//            clientBuilder.setDefaultRequestConfig(RequestConfig.custom()
//                    .setSocketTimeout(10000)
//                    .setConnectTimeout(100)
//                    .setConnectionRequestTimeout(100)
//                    .build());
            clientBuilder.setDefaultRequestConfig(requestConfig);
        }
        if (host.startsWith(HTTPS)) {
            return clientBuilder.setSSLSocketFactory(sslsf).build();
        }
        return clientBuilder.build();
    }
    public static HttpClient wrapClient(String host) {
        return wrapClient(host,null);
    }
}