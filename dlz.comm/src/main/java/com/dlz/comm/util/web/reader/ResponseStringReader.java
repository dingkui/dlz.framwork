package com.dlz.comm.util.web.reader;

import com.dlz.comm.util.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * HTTP相关的操作
 *
 * @author dk
 */
@Slf4j
public class ResponseStringReader implements IResponseReader<String> {
    private static ResponseStringReader instance;
    public static ResponseStringReader getInstance(){
        if(instance==null){
            instance = new ResponseStringReader();
        }
        return instance;
    }
    public String read(InputStream inputStream, String charsetNamere) {
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[4096];

        try {
            int read = 0;
            while (read != -1) {
                read = inputStream.read(buffer);
                if (read > 0) {
                    sb.append(new String(buffer, 0, read, charsetNamere));
                }
            }
            return sb.toString();
        } catch (IOException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

}
