package com.dlz.caller.mybatis;

import com.dlz.caller.DlzCallerProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

/**
 * Configuration for {@link DlzMybatisSqlLogInterceptor}.
 *
 * <p>The property names match MyBatis plugin declarations. All options default to {@code true}
 * so registering the interceptor enables its complete diagnostic output.</p>
 */
@Getter
@Setter
public class DlzSqlLogProperties extends DlzCallerProperties {

    /** Whether SQL logging is enabled. */
    private boolean enabled = true;

    /** Whether the application caller location is emitted. */
    private boolean showCaller = true;

    /** Whether the mapped statement identifier is emitted. */
    private boolean showMapper = true;

    public void apply(Properties source) {
        if (source == null) {
            return;
        }
        if (source.getProperty("enabled") != null) {
            setEnabled(Boolean.parseBoolean(source.getProperty("enabled")));
        }
        if (source.getProperty("showCaller") != null) {
            setShowCaller(Boolean.parseBoolean(source.getProperty("showCaller")));
        }
        if (source.getProperty("showMapper") != null) {
            setShowMapper(Boolean.parseBoolean(source.getProperty("showMapper")));
        }
        if (source.getProperty("injectCallerMdc") != null) {
            setInjectCallerMdc(Boolean.parseBoolean(source.getProperty("injectCallerMdc")));
        }
        if (source.getProperty("callerMdcKey") != null) {
            setCallerMdcKey(source.getProperty("callerMdcKey"));
        }
        String ignoredPackages = source.getProperty("ignoreCallerPackages");
        if (ignoredPackages != null) {
            for (String ignoredPackage : ignoredPackages.split(",")) {
                addIgnoreCallerPackage(ignoredPackage);
            }
        }
    }
}
