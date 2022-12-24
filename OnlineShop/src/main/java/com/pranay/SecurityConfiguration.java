package com.pranay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.pranay.service.UserSecurityService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserSecurityService uss;

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(uss);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http = http.csrf().disable();

		http = http.authorizeRequests()
				.antMatchers("/register").permitAll()
				.antMatchers("/admin/register").permitAll()
				.antMatchers("/mobile").permitAll()
				.anyRequest().authenticated().and().httpBasic().and()
				.formLogin()
				.loginPage("/login").usernameParameter("email").passwordParameter("password").permitAll().and();
	}
}
