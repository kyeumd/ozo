package com.szsleedongkyeum.config.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class JPAConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("test User");
    }

}