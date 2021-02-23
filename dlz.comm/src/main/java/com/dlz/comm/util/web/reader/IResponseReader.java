package com.dlz.comm.util.web.reader;

import java.io.InputStream;

/**
 * HTTP请求结果读取接口
 *
 * @author dk
 */
public interface IResponseReader<T> {
    T read(InputStream inputStream, String charsetNamere);
}
