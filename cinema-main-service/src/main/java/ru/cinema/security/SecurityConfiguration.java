package ru.cinema.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 18.02.2024
 */
@Configuration
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/ticket", "/ticket/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin", "/admin/**").hasRole("ADMIN")
                        .requestMatchers("/", "/**").permitAll()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/users/login")
                        .usernameParameter("username")
                        .failureUrl("/users/login-error")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
                .build();
    }
}
