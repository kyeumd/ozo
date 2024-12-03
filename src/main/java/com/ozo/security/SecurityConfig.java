package com.ozo.security;

import com.ozo.security.jwt.JwtAuthenticationFilter;
import com.ozo.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {

        http
            .securityMatcher("/**")
            .authorizeHttpRequests(request -> request
                .requestMatchers("/ozo/login", "/ozo/signup").permitAll()
                .requestMatchers("/css/**", "/images/**", "/favicon.*", "/*/icon-*").permitAll()
                .requestMatchers("/3o3/swagger.html", "/3o3/swagger-ui.html", "/3o3/swagger-ui/**", "/api/swagger-config", "/api/logistics",
                    "/v3/api-docs/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin))
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
            .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

}
