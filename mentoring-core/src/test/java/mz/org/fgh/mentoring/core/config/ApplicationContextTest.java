/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.org.fgh.mentoring.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Stélio Moiane
 *
 */
@Configuration
@ComponentScan(basePackages = { "mz.co.mozview.frameworks.core", "mz.co.mozview.wezumicro.core" })
@Import({ DataSourceBean.class, EntityManagerBean.class, EmailBean.class })
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource("classpath:db-test.properties")
@EnableJpaRepositories(basePackages = "mz.co.mozview.wezumicro.core", repositoryImplementationPostfix = "Helper")
@EnableScheduling
@Profile("test")
public class ApplicationContextTest {

}
