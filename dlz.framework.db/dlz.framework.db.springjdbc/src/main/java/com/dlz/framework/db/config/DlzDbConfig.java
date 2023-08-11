package com.dlz.framework.db.config;

import com.dlz.framework.db.DbInfo;
import com.dlz.framework.db.cache.DbOprationCache;
import com.dlz.framework.db.cache.TableInfoCache;
import com.dlz.framework.db.convertor.dbtype.ATableCloumnMapper;
import com.dlz.framework.db.convertor.dbtype.TableCloumnMapper;
import com.dlz.framework.db.dao.DaoOperator;
import com.dlz.framework.db.dao.IDlzDao;
import com.dlz.framework.db.dao.MyJdbcTemplate;
import com.dlz.framework.db.holder.DefaultSqlholder;
import com.dlz.framework.db.holder.ISqlHolder;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.db.service.impl.CommServiceImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author: dk
 * @date: 2020-10-15
 */
@Slf4j
@Getter
@Setter
@EnableConfigurationProperties({DlzDbProperties.class})
public class DlzDbConfig {
    @Bean
    @Lazy
    public DbOprationCache dbOprationCache() {
        log.debug("default DbOprationCache init ...");
        return new DbOprationCache();
    }

    @Bean
    @Lazy
    public TableInfoCache tableInfoCache() {
        log.debug("default TableInfoCache init ...");
        return new TableInfoCache();
    }

    @Bean(name = "tableCloumnMapper")
    @Lazy
    @ConditionalOnMissingBean(name = "tableCloumnMapper")
    public ATableCloumnMapper tableCloumnMapper() {
        log.debug("default tableCloumnMapper init ...");
        return new TableCloumnMapper();
    }

    @Bean(name = "dbInfo")
    @Lazy
    @ConditionalOnMissingBean(name = "dbInfo")
    public DbInfo dbInfo(ISqlHolder sql,DlzDbProperties dbProperties) {
        log.debug("default dbInfo init ...");
        return new DbInfo(sql, dbProperties);
    }

    @Bean(name = "dlzDao")
    @Lazy
    @ConditionalOnMissingBean(name = "dlzDao")
    public IDlzDao dlzDao(JdbcTemplate jdbc) {
        log.debug("default dlzDao init ...");
        return new DaoOperator(jdbc);
    }


    @Bean(name = "sqlHolder")
    @Lazy
    @ConditionalOnMissingBean(name = "sqlHolder")
    public ISqlHolder sqlHolder(IDlzDao dao,DlzDbProperties dbProperties) {
        log.debug("default sqlHolder init ...");
        return new DefaultSqlholder(dao,dbProperties);
    }

    @Bean(name = "commService")
    @Lazy
    @DependsOn({"dbInfo"})
    @ConditionalOnMissingBean(name = "commService")
    public ICommService commService(IDlzDao dao,DlzDbProperties dbProperties) {
        log.debug("default commService init ...");
        return new CommServiceImpl(dao,dbProperties);
    }

    @Bean(name = "JdbcTemplate")
    @Lazy
    @ConditionalOnMissingBean(name = "JdbcTemplate")
    public JdbcTemplate JdbcTemplate(DataSource dataSource) {
        log.debug("default JdbcTemplate init ...");
        return new MyJdbcTemplate(dataSource);
    }
}
