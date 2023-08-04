package com.dlz.framework.db.helper.config;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dlz.comm.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import com.dlz.framework.db.helper.support.AsyncUtils;
import com.dlz.framework.db.helper.support.IDbOp;
import com.dlz.framework.db.helper.support.dbs.*;
import com.dlz.framework.db.helper.util.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
@EnableConfigurationProperties({HelperProperties.class})
public class HelperConfiguration {
	@Autowired
	HelperProperties properties;
	@Autowired
	@Lazy
	AsyncUtils asyncUtils;

	private static Set<Class<?>> scanPackage(String basePackage, Class<? extends Annotation> annotationClass) {
		Set<Class<?>> classes = new HashSet<>();
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(annotationClass));

		for (BeanDefinition beanDefinition : scanner.findCandidateComponents(basePackage)) {
			try {
				Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
				classes.add(clazz);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return classes;
	}

	@PostConstruct
	private void scan() {
		if (!properties.autoUpdate || StringUtils.isEmpty(properties.packageName)) {
			return;
		}
		Set<Class<?>> set = scanPackage(properties.packageName, TableName.class);
		for (Class<?> clazz : set) {
			if (properties.type.equals("sqlite")) {
				asyncUtils.initTableSync(clazz);
			} else {
				asyncUtils.initTable(clazz);
			}
		}
	}

	@Bean("sqlThreadPool")
	@Lazy
	public AsyncTaskExecutor sqlThreadPool() {
		ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
		asyncTaskExecutor.setMaxPoolSize(properties.maxPoolSize);
		asyncTaskExecutor.setCorePoolSize(properties.corePoolSize);
		asyncTaskExecutor.setThreadNamePrefix("sql-thread-pool-");
		asyncTaskExecutor.initialize();
		log.info("AsyncTaskExecutor init ...");
		return asyncTaskExecutor;
	}
	@Bean
	@Lazy
	public IDbOp dbOp(JdbcTemplate jdbcTemplate) {
		log.info("DbOp init ...");
		if (properties.getType().equals("sqlite")) {
			return new DbOpSqlite(jdbcTemplate);
		} else if (properties.getType().equals("mysql")) {
			return new DbOpMysql(jdbcTemplate);
		} else if (properties.getType().equals("postgresql")) {
			return new DbOpPostgresql(jdbcTemplate);
		}
		return new DbOpMysql(jdbcTemplate);
	}
	@Bean
	@Lazy
	public SqlHelper sqlHelper(IDbOp op,JdbcTemplate jdbcTemplate) {
		log.info("SqlHelper init ...");
		return new SqlHelper(op,jdbcTemplate);
	}
	@Bean
	@Lazy
	public AsyncUtils asyncUtils(IDbOp op) {
		log.info("AsyncUtils init ...");
		return new AsyncUtils(op);
	}
}
