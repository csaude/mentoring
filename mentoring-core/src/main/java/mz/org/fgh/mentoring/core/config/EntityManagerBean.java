/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.config;

import java.util.Properties;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import mz.co.mozview.frameworks.core.util.CleanDBAspect;
import mz.co.mozview.frameworks.core.util.CleanDBUtil;

/**
 * @author Stélio Moiane
 *
 */
@Configuration
public class EntityManagerBean {

	@Inject
	private Environment environment;

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource,
	        final JpaVendorAdapter jpaVendorAdapter) {

		final LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(dataSource);
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan(this.environment.getProperty("spring.packages.to.scan"));
		entityManager.setJpaProperties(this.jpaProperties());

		return entityManager;
	}

	@Bean(name = "jpaVendorAdapter")
	public JpaVendorAdapter jpaVendorAdapter() {

		final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setGenerateDdl(new Boolean(this.environment.getProperty("db.generateDdl")));
		hibernateJpaVendorAdapter.setDatabasePlatform(this.environment.getProperty("db.database"));

		return hibernateJpaVendorAdapter;
	}

	private Properties jpaProperties() {

		final Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", this.environment.getProperty("db.hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", this.environment.getProperty("db.hibernate.show_sql"));
		properties.setProperty("hibernate.format_sql", this.environment.getProperty("db.hibernate.format_sql"));
		properties.setProperty("hibernate.hbm2ddl.auto", this.environment.getProperty("db.hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults",
		        this.environment.getProperty("db.hibernate.temp.use_jdbc_metadata_defaults"));
		properties.setProperty("hibernate.enable_lazy_load_no_trans",
		        this.environment.getProperty("db.hibernate.enable_lazy_load_no_trans"));
		properties.setProperty("hibernate.generate_statistics",
		        this.environment.getProperty("db.hibernate.generate_statistics"));

		return properties;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean(name = "cleanDBUtil")
	@Profile("test")
	public CleanDBUtil cleanDBUtil() {
		return new CleanDBUtil();
	}

	@Bean(name = "cleanDBAspect")
	@Profile("test")
	public CleanDBAspect cleanDBAspect() {
		return new CleanDBAspect();
	}
}
