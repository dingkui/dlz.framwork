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
    private boolean showMapper = false;
}
