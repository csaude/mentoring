/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.org.fgh.mentoring.core.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Stélio Moiane
 *
 */
@Configuration
public class DataSourceBean {

	@Inject
	private Environment environment;

	@Bean(name = "dataSource")
	public DataSource dataSource() {

		final HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDataSourceClassName(this.environment.getProperty("db.dataSourceClassName"));
		dataSource.setUsername(this.environment.getProperty("db.username"));
		dataSource.setPassword(this.environment.getProperty("db.password"));
		dataSource.setMaximumPoolSize(Integer.valueOf(this.environment.getProperty("db.maximumPoolSize")));
		dataSource.setCatalog(this.environment.getProperty("db.catalog"));

		dataSource.addDataSourceProperty("cachePrepStmts", "true");
		dataSource.addDataSourceProperty("prepStmtCacheSize", "250");
		dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		dataSource.addDataSourceProperty("useServerPrepStmts", "true");

		return dataSource;
	}
}
