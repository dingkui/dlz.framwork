package com.dlz.framework.db.config;

import com.dlz.framework.db.DbInfo;
import com.dlz.framework.db.dao.IDlzDao;
import com.dlz.framework.db.dao.MyJdbcTemplate;
import com.dlz.framework.db.cache.DbOprationCache;
import com.dlz.framework.db.cache.TableInfoCache;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.db.service.impl.CommServiceImpl;
import com.dlz.framework.db.dao.DaoOperator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author: dk
 * @date: 2020-10-15
 */
@Slf4j
@Getter
@Setter
public class DlzDbConfig {


    @Bean
    @Lazy
    public DbOprationCache dbOprationCache() {
        return new DbOprationCache();
    }

    @Bean
    @Lazy
    public TableInfoCache tableInfoCache() {
        return new TableInfoCache();
    }

    @Bean(name = "dbInfo")
    @Lazy
    public DbInfo dbInfo() {
        return new DbInfo();
    }

    @Bean(name = "dlzDao")
    @Lazy
    @ConditionalOnMissingBean(name = "dlzDao")
    public IDlzDao dlzDao() {
        return new DaoOperator();
    }

    @Bean
    @Lazy
    @DependsOn({"dbInfo"})
    public ICommService commService() {
        return new CommServiceImpl();
    }

    @Bean(name = "JdbcTemplate")
    @Lazy
    @ConditionalOnMissingBean(name = "JdbcTemplate")
    public JdbcTemplate JdbcTemplate() {
        return new MyJdbcTemplate();
    }

}
