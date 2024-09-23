package com.official.kento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Securityで必要なオブジェクトを生成するConfigクラス
 * @author	Kento Kodama
 * @version	1.0.0
 * @since	1.0.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	/**
	 * bcryptアルゴリズムでハッシュ化を行うエンコーダのオブジェクトを生成します
	 * @return PasswordEncoderオブジェクト
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * SecurityFilterChainのオブジェクトを生成します
	 * @param http	HTTPセキュリティオブジェクト
	 * @return		SecurityFilterChainオブジェクト
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
				.requestMatchers("/css/**").permitAll()
				.requestMatchers("/js/**").permitAll()
				.requestMatchers("/image/**").permitAll()
				.requestMatchers("/").permitAll()
				.requestMatchers("/header").permitAll()
				.requestMatchers("/footer").permitAll()
				.requestMatchers("/error_page").permitAll()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/register").permitAll()
				.requestMatchers("/account_list").permitAll()
				.requestMatchers("/photo/**").permitAll()
				.anyRequest().authenticated())
			.formLogin(formLogin -> formLogin
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/login")
				.permitAll())
			.sessionManagement(session -> session
				.invalidSessionUrl("/login")
				.maximumSessions(1))
			.logout(logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login"))
			.headers(headers -> headers
				.frameOptions(frameOptions -> frameOptions.sameOrigin()));

		return http.build();
	}
}