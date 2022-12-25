package com.murebackend.murebackend.Config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	private final UserDetailsService jwtUserDetailsService;

	private final JwtRequestFilter jwtRequestFilter;
	@Bean
	public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  	public WebSecurityConfig(UserDetailsService jwtUserDetailsService, JwtRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
		this.jwtUserDetailsService = jwtUserDetailsService;

	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/api/auth/").permitAll()
				.antMatchers("/api/users/register/").permitAll()

				.antMatchers(HttpMethod.GET, "/api/artists/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/albums/").permitAll()
				.antMatchers(HttpMethod.GET, "/api/songs/*/").permitAll()

				.antMatchers("/api/roles/").permitAll()

				.anyRequest().authenticated()
				.and().exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
					Map<String, Object> responseMap = new HashMap<>();
					ObjectMapper mapper = new ObjectMapper();
					response.setStatus(401);
					responseMap.put("error", true);
					responseMap.put("message", "Unauthorized");
					response.setHeader("content-type", "application/json");
					String responseMsg = mapper.writeValueAsString(responseMap);
					response.getWriter().write(responseMsg);
				})

				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
