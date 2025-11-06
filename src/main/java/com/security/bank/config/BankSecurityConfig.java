package com.security.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BankSecurityConfig {

	// Basic Authentication
	/*
	 Go to Postman
	 URL: http://localhost:8080/user/test
	 clear cookies (see below Send button)
	 in Authorization select Basic Auth
	 Enter the users we created InMemory Example Tony and pass
	 
	 click send 
	 If we want form based authentication we just need to replace httpBasi() to formLogin() run URL in browser
	 */
	@Autowired
	UserDetailsService userDetailsService;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  //with antMatcher when method level security is not implemented 
	//	http.csrf().disable().authorizeHttpRequests().antMatchers("/user/test").hasRole("ADMIN").anyRequest().authenticated().and().httpBasic();
		
		//with method level role based security
		http
		.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/user/register").permitAll()
		.and()
		.rememberMe().userDetailsService(userDetailsService)
		.and()
		.formLogin()
		.loginPage("/login").permitAll()
		.and().logout().deleteCookies("remember-me");
		//the remember-me is cookie name given in frontend by html page
		
		
		

		return http.build();

	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder ) throws Exception {
		
		return builder.getAuthenticationManager();
		
		
	}
	
	/*Now we are loading user data from database so no need of user() instead we will have to create bean of Authentcation manager*/
//	@Bean
//	public UserDetailsService users() {
//		UserDetails user_1=User.builder()
//				.username("Nikhil")
//				.password(passwordEncoder().encode("pass"))
//				.roles("ADMIN")
//				.build();
//		UserDetails user_2=User.builder()
//				.username("Tony")
//				.password(passwordEncoder().encode("pass"))
//				.roles("NORMAL")
//				.build();
//		
//		
//		return new InMemoryUserDetailsManager(user_1,user_2);
//	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
