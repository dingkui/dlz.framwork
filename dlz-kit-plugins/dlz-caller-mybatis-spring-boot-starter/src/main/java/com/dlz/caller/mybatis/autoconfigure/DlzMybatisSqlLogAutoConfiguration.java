package com.dlz.caller.mybatis.autoconfigure;

import com.dlz.caller.mybatis.DlzMybatisSqlLogInterceptor;
import com.dlz.caller.mybatis.DlzSqlLogProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Shared Boot 2/3 configuration, without servlet or logging implementation dependencies. */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(SqlSessionFactory.class)
@ConditionalOnProperty(prefix = "dlz.caller.mybatis.sql-log", name = "enabled", havingValue = "true", matchIfMissing = true)
@AutoConfigureBefore(name = {"org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration",
        "com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration"})
public class DlzMybatisSqlLogAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = "dlz.caller.mybatis.sql-log")
    public DlzSqlLogProperties dlzSqlLogProperties() {
        return new DlzSqlLogProperties();
    }

    @Bean
    @ConditionalOnMissingBean(DlzMybatisSqlLogInterceptor.class)
    public DlzMybatisSqlLogInterceptor dlzMybatisSqlLogInterceptor(DlzSqlLogProperties properties) {
        return new DlzMybatisSqlLogInterceptor(properties);
    }
}
