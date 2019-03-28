/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.config;

import mz.org.fgh.mentoring.integ.filter.JWTAuthenticationFilter;
import mz.org.fgh.mentoring.integ.resources.tutor.TutorResource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.test.context.ActiveProfiles;

import mz.org.fgh.mentoring.core.config.ApplicationContext;

/**
 * @author Stélio Moiane
 *
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "mz.org.fgh.mentoring.core", "mz.org.fgh.mentoring.integ" })
@ActiveProfiles("live")
public class WebApplicationContext extends WebSecurityConfigurerAdapter {
	@Bean
	@Profile("live")
	public AnnotationConfigApplicationContext coreApplicationContext() {
		return new AnnotationConfigApplicationContext(ApplicationContext.class);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new JWTAuthenticationFilter(ConfigUtils.retrieveJWTKeyFromRunTime()), BasicAuthenticationFilter.class).csrf().disable();
	}
}
