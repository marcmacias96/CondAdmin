package com.mamp.software.condadmin;

import com.mamp.software.condadmin.services.UserService;
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

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private LoginSuccessHandler successHandler;

	@Autowired
	private UserService srvUser;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
		builder.userDetailsService(srvUser).passwordEncoder(passwordEncoder);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/css/**", "/js/**", "/img/**", "/sass/**","/fonts/**","/h2-console/**").permitAll()
			.antMatchers("/user/**").permitAll()
			.antMatchers("/house/**").hasAnyRole("USER-ADMIN","USER")
			.antMatchers("/expenses/**").hasAnyRole( "USER-ADMIN")
			.antMatchers("/incomes/**").hasAnyRole( "USER-ADMIN")
			.antMatchers("/annualcounts/**").hasAnyRole( "USER-ADMIN")
			.antMatchers("/owners/**","/ownerRest/**").hasAnyRole("USER-ADMIN")
			.antMatchers("/condominium/**").hasAnyRole("USER-ADMIN","USER")
			.anyRequest().authenticated()
			.and()
				.formLogin().successHandler(successHandler)
				.loginPage("/login").permitAll()
			.and()
				.logout().permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/error_403")
			.and()
				.csrf().ignoringAntMatchers("/h2-console/**")
			.and()
				.headers().frameOptions().sameOrigin();
	}
	
}
