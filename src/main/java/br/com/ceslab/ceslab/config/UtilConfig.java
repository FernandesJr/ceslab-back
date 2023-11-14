package br.com.ceslab.ceslab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class UtilConfig {

    @Bean
    public Connection getConnectionDB(DataSource dataSource) throws SQLException {
        return dataSource.getConnection();
    }
}
