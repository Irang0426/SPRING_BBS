package com.bbs.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final CustomUserDetailsService customUserDetailsService;
	
	private final LoginSuccessHandler loginSuccessHandler;
	
	public SecurityConfig(CustomUserDetailsService customUserDetailsService, LoginSuccessHandler loginSuccessHandler) {
		this.customUserDetailsService = customUserDetailsService;
		this.loginSuccessHandler = loginSuccessHandler;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		
		
		http
				.csrf(CsrfConfigurer::disable)
				.cors(Customizer.withDefaults())
				.authorizeHttpRequests((auth) -> auth
						.requestMatchers("/", "/login/**").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.anyRequest().hasAnyRole("TEST1", "TEST2", "TEST3", "TEST4", "TEST5", "TEST6", "TEST7", "TEST8", "TEST9", "ADMIN")	//authentical인가 뭐시기는 권한 얻어오라는 뜻
				)
				.formLogin((auth) -> 
					auth.loginPage("/login")
						.loginProcessingUrl("/login")
						.successHandler(loginSuccessHandler)
						.failureUrl("/login?loginerror=1")
				);
		

		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	    AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        return builder.build();
	}
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
