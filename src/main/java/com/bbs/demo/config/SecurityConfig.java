package com.bbs.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
				.authorizeHttpRequests((auth) -> auth
						//.requestMatchers("/").permitAll()
						//.requestMatchers("/admin").hasRole("ADMIN")
						//.requestMatchers("/list").hasRole("USER")
						.anyRequest().permitAll()	//authentical인가 뭐시기는 권한 얻어오라는 뜻
				);
		
		// 로그인 필요할 때 강제로 보내는 코드
		/*http
				.formLogin((auth) -> auth.loginPage("/login")
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/list")
						.failureUrl("/login?loginerror=1")
						.permitAll()
				);*/
		
		http
        .csrf((auth) -> auth.disable());
		
		return http.build();
	}
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
