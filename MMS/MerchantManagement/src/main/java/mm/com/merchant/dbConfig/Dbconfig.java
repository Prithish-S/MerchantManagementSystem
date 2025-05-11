package mm.com.merchant.dbConfig;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class Dbconfig {

	private final String URL = "jdbc:postgresql://localhost/MM_DB?currentSchema=mm_db";
	private final String USER = "postgres";
	private final String DRIVER = "org.postgresql.Driver";
	private final String PASSWORD = "12345678";
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	DataSourceConfig dataSourceConfig;

	@Primary
	@Bean
	@Qualifier(value = "jdbcTemplate")
	public JdbcTemplate jdbcMasterTemplate() {
		if (null == jdbcTemplate) {
			DataSource dataSource = new DataSource();
			dataSource.setDriverClassName(DRIVER);
			dataSource.setUrl(URL);
			dataSource.setUsername(USER);
			dataSource.setPassword(PASSWORD);
			jdbcTemplate = new JdbcTemplate(dataSource);
			dataSourceConfig.setJdbcTemplate(jdbcTemplate);
		}
		return jdbcTemplate;
	}

}