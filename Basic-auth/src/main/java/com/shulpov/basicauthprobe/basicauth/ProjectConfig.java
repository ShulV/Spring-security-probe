package com.shulpov.basicauthprobe.basicauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    // Для настройки обработки аутентификации авторизации
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // Customizer - контракт, который нужно реализовывать для определения настройки любого элемента Spring Security:
        // аутентификация, авторизация, CSRF, CORS...
        http.httpBasic(Customizer.withDefaults());// Поведение по умолчанию, даже если бы явно не написали
        http.authorizeHttpRequests(
                // Аутентификация для всех запросов включена
                c -> c.anyRequest().authenticated()
                // Аутентификация для всех запросов выключена
//                c -> c.anyRequest().permitAll()
        );

        // Можно внедрять userDetailsService в общей конфигурации, если не нужен его ибн в контексте
//        var user = User.withUsername("vshulpov")
//                .password("12345")
//                .roles("read")// Пока не важно какая роль, она просто должна быть
//                .build();
//        var userDetailsService = new InMemoryUserDetailsManager(user);
//        http.userDetailsService(userDetailsService);

        return http.build();
    }

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
