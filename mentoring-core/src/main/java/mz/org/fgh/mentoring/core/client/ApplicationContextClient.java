/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.client;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import mz.org.fgh.mentoring.core.config.DataSourceBean;
import mz.org.fgh.mentoring.core.config.EntityManagerBean;

/**
 * @author Stélio Moiane
 *
 */
@Configuration
@ComponentScan(basePackages = { "mz.co.mozview.frameworks.core", "mz.org.fgh.mentoring.core" })
@Import({ DataSourceBean.class, EntityManagerBean.class })
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource("classpath:db-client.properties")
@EnableJpaRepositories(basePackages = "mz.org.fgh.mentoring.core", repositoryImplementationPostfix = "Helper")
@Profile("client")
public class ApplicationContextClient {

}
