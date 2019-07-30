package com.madbarsoft.springbootthymeleftspringsecurity.appconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
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
             .antMatchers("/", "/home").permitAll()
             .antMatchers("/login").permitAll()
             .anyRequest().authenticated()
             .and()
             .formLogin().loginPage("/login").permitAll()
	         .and()
	         .logout() .permitAll()
	         .and()
	         .exceptionHandling().accessDeniedPage("/403");
    }
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("imran").password("123").roles("USER");
	}


}
