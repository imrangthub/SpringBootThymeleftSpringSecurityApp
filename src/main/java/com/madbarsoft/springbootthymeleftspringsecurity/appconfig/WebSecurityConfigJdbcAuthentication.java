package com.madbarsoft.springbootthymeleftspringsecurity.appconfig;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigJdbcAuthentication extends WebSecurityConfigurerAdapter {
	
	
	
	@Autowired
	DataSource dataSource;
	
	 @Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	      .antMatchers("/css/**")
	      .antMatchers("/js/**")
	      .antMatchers("/loginform/**")
	      .antMatchers("/vendor/**");
	  }
		
	   @Override
	   protected void configure(HttpSecurity http) throws Exception {
	       http
		         .authorizeRequests()
		     		 .antMatchers("/book/**").hasRole("USER")
		       		 .antMatchers("/admin/**").hasRole("ADMIN")	
		             .antMatchers("/", "/home").permitAll()
		             .antMatchers("/login").permitAll()
		             .anyRequest().authenticated()
		             .and()
	            .formLogin()
		             .loginPage("/login")
		             .defaultSuccessUrl("/home")
		             .permitAll()
		             .and()
		         .logout() 
		             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		             .logoutSuccessUrl("/home")
			         .permitAll()
		             .and()
		         .exceptionHandling().accessDeniedPage("/403");
	   }
	   
		@Autowired
		public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
			
		  auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery(
				"select username,password, enabled from users where username=?")
			.authoritiesByUsernameQuery(
				"select username, role from user_roles where username=?");
		}
	   
	   
	   
	   
	
	//  For Default authentication
	   
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(
	 * passwordEncoder()); }
	 */
	
	   @Bean
	   public PasswordEncoder passwordEncoder() {
	       PasswordEncoder encoder = new BCryptPasswordEncoder();
	       return encoder;
	   }

}
