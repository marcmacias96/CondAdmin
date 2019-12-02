package com.mamp.software.condadmin;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mamp.software.condadmin.security.LoginSuccessHandler;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private LoginSuccessHandler successHandler;	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
		PasswordEncoder encoder = passwordEncoder();
		UserBuilder users = User//.builder().passwordEncoder(password -> encoder.encode(password));
		builder.inMemoryAuthentication().withUser(users.username("admin").password("12345").roles("ADMIN"))
										.withUser(users.username("user").password("12345").roles("USER"));
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()
			.antMatchers("/house/**").hasAnyRole("ADMIN")
			.antMatchers("/expenses/**").hasAnyRole("ADMIN")
			.antMatchers("/incomes/**").hasAnyRole("ADMIN")
			.antMatchers("/owners/**").hasAnyRole("ADMIN")
			.anyRequest().authenticated()
			.and()
				.formLogin().successHandler(successHandler)
				.loginPage("/login").permitAll()
			.and()
				.logout().permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/error_403");
			
	}
	
}
