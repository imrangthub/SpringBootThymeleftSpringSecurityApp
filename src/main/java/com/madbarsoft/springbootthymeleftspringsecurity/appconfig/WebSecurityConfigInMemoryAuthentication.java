package com.madbarsoft.springbootthymeleftspringsecurity.appconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigInMemoryAuthentication extends WebSecurityConfigurerAdapter {   // For Im - Memory Auth
	
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
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
	    BCryptPasswordEncoder encoder = passwordEncoder();
	    auth.inMemoryAuthentication()
	    .withUser("imran").password(encoder.encode("123")).roles("USER")
	    .and()
	    .withUser("admin").password(encoder.encode("12345")).roles("USER", "ADMIN");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
//	 @Bean
//	 public static NoOpPasswordEncoder passwordEncoder() {
//	  return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	 }


}
