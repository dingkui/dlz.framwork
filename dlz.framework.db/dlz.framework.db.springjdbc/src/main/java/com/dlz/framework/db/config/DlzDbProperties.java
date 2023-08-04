package com.dlz.framework.db.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dlz.db.jdbc")
@Getter
@Setter
public class DlzDbProperties {
    /**
     * 是否启动jdbcSql
     */
    private boolean jdbcSql;
    /**
     * 是否显示结果日志
     */
    private boolean showResult = false;
    /**
     * 是否显示配置的sql
     */
    private boolean showKeySql = true;
    /**
     * 是否显示运行sql
     */
    private boolean showRunSql = false;
}