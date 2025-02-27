package com.shulpov.basicauthprobe.basicauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig {

    // Если переопределяем userDetailsService, то и passwordEncoder должны тоже переопределить
    @Bean
    UserDetailsService userDetailsService() {
        var user = User.withUsername("vshulpov")
                .password("12345")
                .roles("read")// Пока не важно какая роль, она просто должна быть
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();// Тестовый инкодер-заглушка, никак не кодирует данные
    }
}
