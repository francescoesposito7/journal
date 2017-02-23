package com.spring.boot.journal.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(usersQuery)
		.authoritiesByUsernameQuery(rolesQuery)
		.rolePrefix("ROLE_")
		.passwordEncoder(bCryptPasswordEncoder);
	}
	
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http
			.authorizeRequests()
				.antMatchers("/", "/*").permitAll()
				.antMatchers("/user/**").hasRole("USER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.and()
				.formLogin().loginPage("/login");
		
		http
			.exceptionHandling().accessDeniedPage("/403");
		
	}


}
