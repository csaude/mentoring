/**
 *
 */
package mz.org.fgh.mentoring.core.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Stélio Moiane
 *
 */
@Configuration
public class FlywayBean {

	@Inject
	private DataSource dataSource;

	@Profile("live")
	@Bean(initMethod = "migrate")
	public Flyway flyway() {
		final Flyway flyway = new Flyway();

		flyway.setBaselineOnMigrate(Boolean.TRUE);
		flyway.setLocations("classpath:/db/migration");
		flyway.setValidateOnMigrate(Boolean.FALSE);
		flyway.setDataSource(this.dataSource);
		flyway.setTable("schema_version");

		return flyway;
	}
}
