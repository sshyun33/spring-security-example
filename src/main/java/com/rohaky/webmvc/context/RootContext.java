package com.rohaky.webmvc.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.rohaky.webmvc.service"})
public class RootContext {

   @Bean
   DataSource dataSource() {
     DriverManagerDataSource ds = new DriverManagerDataSource();
       ds.setDriverClassName("com.mysql.jdbc.Driver");
       ds.setUrl("jdbc:mysql://www.rohaky.com/test");
       ds.setUsername("root");
       ds.setPassword("apmsetup");
       return ds;
   }
}
