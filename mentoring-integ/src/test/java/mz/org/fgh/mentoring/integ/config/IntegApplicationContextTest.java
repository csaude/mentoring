/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Stélio Moiane
 *
 */

@Configuration
@ComponentScan(basePackages = { "mz.org.fgh.mentoring.integ" })
@EnableTransactionManagement
@Profile("test")
public class IntegApplicationContextTest {

}
