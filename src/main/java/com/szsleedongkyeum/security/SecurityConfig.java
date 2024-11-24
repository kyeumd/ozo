package com.szsleedongkyeum.security;

import com.szsleedongkyeum.security.filter.RestAuthenticationFilter;
import com.szsleedongkyeum.security.handler.RestAccessDeniedHandler;
import com.szsleedongkyeum.security.handler.RestAuthenticationFailureHandler;
import com.szsleedongkyeum.security.handler.RestAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private final AuthenticationProvider restAuthenticationProvider;
    private final RestAuthenticationSuccessHandler restSuccessHandler;
    private final RestAuthenticationFailureHandler restFailureHandler;
    private final RestAccessDeniedHandler restAccessDeniedHandler;

    public SecurityConfig(
        @Qualifier("restAuthenticationProvider") AuthenticationProvider restAuthenticationProvider,
        RestAuthenticationSuccessHandler restSuccessHandler,
        RestAuthenticationFailureHandler restFailureHandler,
        RestAccessDeniedHandler restAccessDeniedHandler) {
        this.restAuthenticationProvider = restAuthenticationProvider;
        this.restSuccessHandler = restSuccessHandler;
        this.restFailureHandler = restFailureHandler;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(restAuthenticationProvider);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http
            .securityMatcher("/**")
            .authorizeHttpRequests(request -> request
                .requestMatchers("/css/**", "/images/**", "/favicon.*", "/*/icon-*").permitAll()
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/api/swagger-config", "/api/logistics", "/v3/api-docs/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin))
            .addFilterBefore(restAuthenticationFilter(http, authenticationManager), UsernamePasswordAuthenticationFilter.class)
            .authenticationManager(authenticationManager)
            .exceptionHandling(exception -> exception
                .accessDeniedHandler(restAccessDeniedHandler)
            )
            .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    private RestAuthenticationFilter restAuthenticationFilter(HttpSecurity http, AuthenticationManager authenticationManager) {
        RestAuthenticationFilter restAuthenticationFilter = new RestAuthenticationFilter(http);
        restAuthenticationFilter.setAuthenticationManager(authenticationManager);
        restAuthenticationFilter.setAuthenticationSuccessHandler(restSuccessHandler);
        restAuthenticationFilter.setAuthenticationFailureHandler(restFailureHandler);
        return restAuthenticationFilter;
    }
}
