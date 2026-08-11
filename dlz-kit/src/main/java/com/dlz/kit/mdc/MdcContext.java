package com.dlz.kit.mdc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * Thread-bound scope managed via MDC.
 */
@Slf4j
public class MdcContext implements AutoCloseable {
    public final String mdcKey;
    private final String mdcValue;
    private String mdcValueOld;
    private boolean closed;
    private boolean needRestore;

    protected MdcContext(String mdcKey, String mdcValue) {
        assert mdcKey != null;
        assert mdcValue != null;
        mdcValueOld = MDC.get(mdcKey);
        this.mdcKey = mdcKey;
        this.mdcValue = mdcValue;
        this.needRestore = mdcValueOld==null || !mdcValue.equals(mdcValueOld);
        if(needRestore){
            MDC.put(mdcKey, mdcValue);
        }
    }

    public static MdcContext open(String mdcKey, String resolvedCaller) {
        return new MdcContext(mdcKey, resolvedCaller);
    }

    public String get() {
        return mdcValue;
    }

    @Override
    public void close() {
        if (closed || !needRestore) {
            return;
        }
        closed = true;
        if(mdcValueOld!=null){
            MDC.put(mdcKey, mdcValueOld);
        }else{
            if(mdcValue ==null){
                log.warn("traceId:{} is null!",mdcKey);
            }else{
                MDC.remove(mdcKey);
            }
        }
    }
}
