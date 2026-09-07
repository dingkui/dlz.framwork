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

    /** Registration-time switch. Manual integrations must check it before registering the plugin. */
    private boolean enabled = true;

    /** Whether the application caller location is emitted. */
    private boolean showCaller = true;

    /** Whether the mapped statement identifier is emitted. */
    private boolean showMapper = false;
}
