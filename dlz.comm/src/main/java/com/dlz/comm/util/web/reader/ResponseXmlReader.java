package com.dlz.comm.util.web.reader;

import com.dlz.comm.util.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.InputStream;

/**
 * HTTP相关的操作
 *
 * @author dk
 */
@Slf4j
public class ResponseXmlReader implements IResponseReader<Document> {
    private static final SAXReader saxReader = new SAXReader();
    private static ResponseXmlReader instance;
    public static ResponseXmlReader getInstance(){
        if(instance==null){
            instance = new ResponseXmlReader();
        }
        return instance;
    }

    @Override
    public Document read(InputStream inputStream, String charsetNamere) {
        try {
            InputSource source = new InputSource(inputStream);
            if (charsetNamere != null) {
                source.setEncoding(charsetNamere);
            }
            return saxReader.read(inputStream);
        } catch (DocumentException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }
}
