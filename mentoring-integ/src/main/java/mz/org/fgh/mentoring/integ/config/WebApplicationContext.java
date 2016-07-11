/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.org.fgh.mentoring.integ.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.test.context.ActiveProfiles;

import mz.org.fgh.mentoring.core.config.ApplicationContext;

/**
 * @author Stélio Moiane
 *
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "mz.org.fgh.mentoring.core", "mz.org.fgh.mentoring.core.integ" })
@ActiveProfiles("live")
public class WebApplicationContext extends WebSecurityConfigurerAdapter {

	// @Inject
	// private UserContextService userContextService;

	// @Inject
	// private AuthenticationManager authenticationManager;

	@Bean
	@Profile("live")
	public AnnotationConfigApplicationContext coreApplicationContext() {
		return new AnnotationConfigApplicationContext(ApplicationContext.class);
	}

	// @Bean
	// public
	// AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken>
	// authenticationUserDetailsService() {
	// return new
	// UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>(this.userContextService);
	// }

	// @Bean
	// public AuthenticationProvider authenticationProvider() {
	// final PreAuthenticatedAuthenticationProvider
	// preAuthenticatedAuthenticationProvider = new
	// PreAuthenticatedAuthenticationProvider();
	// preAuthenticatedAuthenticationProvider
	// .setPreAuthenticatedUserDetailsService(this.authenticationUserDetailsService());
	// return preAuthenticatedAuthenticationProvider;
	// }
	//
	// @Bean
	// public CustomAuthenticationHandler customAuthenticationHandler() {
	// return new CustomAuthenticationHandler();
	// }
	//
	// @Bean
	// public PreAuthencicateFilter siteminderFilter() throws Exception {
	//
	// final PreAuthencicateFilter preAuthencicateFilter = new
	// PreAuthencicateFilter();
	// preAuthencicateFilter.setPrincipalRequestHeader("USER");
	// preAuthencicateFilter.setAuthenticationManager(this.authenticationManager);
	//
	// return preAuthencicateFilter;
	// }
	//
	// @Bean
	// public AuthenticationManager authenticationManager(final
	// List<AuthenticationProvider> providers) {
	// return new ProviderManager(providers);
	// }
	//
	// @Override
	// protected void configure(final HttpSecurity http) throws Exception {
	// http.addFilter(this.siteminderFilter()).httpBasic().authenticationEntryPoint(this.customAuthenticationHandler())
	// .and().csrf().disable().headers().disable().authorizeRequests().anyRequest().authenticated();
	// }
}
