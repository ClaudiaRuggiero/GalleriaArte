package it.uniroma3.galleria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)

		.passwordEncoder(new BCryptPasswordEncoder())
		.usersByUsernameQuery("SELECT username,password,1 FROM admin WHERE username=?")
		.authoritiesByUsernameQuery("SELECT username,role FROM admin WHERE username=?");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.authorizeRequests()
		.antMatchers("/newPainting","/newArtist","/deletePainting","/deleteArtist").access("hasAuthority('ADMIN')")
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.failureUrl("/login.html?error=true")
		.and()
		.logout()
		.logoutSuccessUrl("/")
		.permitAll()
		.and()
		.sessionManagement()
		.maximumSessions(1)
		.expiredUrl("/login?expired");

	}
}
