/*
 * MozView Technologies, Lda. 2010 - 2015
 */
package mz.org.fgh.mentoring.core.config;

import java.util.Properties;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

/**
 * @author Stélio Moiane
 *
 */
@Configuration
public class EmailBean {

	@Inject
	private Environment environment;

	@Bean(name = "mailSender")
	public JavaMailSender mailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(this.environment.getProperty("mail.hostname"));
		javaMailSender.setUsername(this.environment.getProperty("mail.username"));
		javaMailSender.setPassword(this.environment.getProperty("mail.password"));
		javaMailSender.setProtocol(this.environment.getProperty("mail.protocol"));
		javaMailSender.setPort(Integer.valueOf(this.environment.getProperty("mail.port")));
		javaMailSender.setJavaMailProperties(this.javaMailProperties());

		return javaMailSender;
	}

	private Properties javaMailProperties() {

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", this.environment.getProperty("mail.smtp.auth"));
		properties.setProperty("mail.smtp.starttls.enable", this.environment.getProperty("mail.smtp.starttls.enable"));
		properties.setProperty("mail.smtp.debug", this.environment.getProperty("mail.smtp.debug"));

		return properties;
	}

	@Bean(name = "mailMessage")
	public SimpleMailMessage mailMessage() {
		return new SimpleMailMessage();
	}

	@Bean(name = "velocityEngine")
	public VelocityEngineFactoryBean velocityEngine() {

		VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
		velocityEngineFactoryBean.setResourceLoaderPath(this.environment.getProperty("velocity.resourceLoaderPath"));
		velocityEngineFactoryBean.setPreferFileSystemAccess(new Boolean(this.environment
				.getProperty("velocity.preferFileSystemAccess")));

		return velocityEngineFactoryBean;
	}
}
