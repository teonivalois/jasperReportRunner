package cloud.valois.java.reportRunner.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class WebConfig {

	@Bean(name = "db")
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.url(System.getenv("DATABASE_JDBC_URL"))
				.username(System.getenv("DATABASE_USERNAME"))
				.password(System.getenv("DATABASE_PASSWORD"))
				.driverClassName(System.getenv("DATABASE_DRIVER_CLASSNAME"))
				.build();
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("db") DataSource ds) {
		return new JdbcTemplate(ds);
	}
}