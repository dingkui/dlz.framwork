package com.dlz.caller.mybatis;

import com.dlz.caller.DlzCallerProperties;
import lombok.Getter;
import lombok.Setter;



/**
 * Configuration for {@link DlzMybatisSqlLogInterceptor}.
 *
 */
@Getter
@Setter
public class DlzSqlLogProperties extends DlzCallerProperties {

    public enum LogLevel { DEBUG, INFO }

    /** Standalone plugin retains DEBUG; the starter defaults to INFO. */
    private LogLevel logLevel = LogLevel.DEBUG;

    /** Whether SQL logging is enabled. */
    private boolean enabled = true;

    /** Whether the application caller location is emitted. */
    private boolean showCaller = true;

    /** Whether the mapped statement identifier is emitted. */
    private boolean showMapper = false;
}
