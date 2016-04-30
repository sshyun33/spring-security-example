package com.rohaky.webmvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, order = 0)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*        auth
                .inMemoryAuthentication()
                    .withUser("user").password("password").authorities("USER");*/
        auth
                .userDetailsService(jdbcDaoImpl());


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resource/**", "/favicon.ico");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("PERM_ACCESS_ADMIN_PAGE")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
//                    .defaultSuccessUrl("/security", true)
                .permitAll();


    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    JdbcDaoImpl jdbcDaoImpl() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setEnableGroups(true);
        jdbcDao.setEnableAuthorities(false);
        jdbcDao.setDataSource(dataSource);
        return jdbcDao;
    }
}
