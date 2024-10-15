//package ru.films.config;
//
//import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
//import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//
//@KeycloakConfiguration
//public class SecurityConfig {
//
//    /**
//     * Настройка Keycloak как провайдера аутентификации.
//     */
//    @Bean
//    public KeycloakAuthenticationProvider keycloakAuthenticationProvider() {
//        KeycloakAuthenticationProvider keycloakAuthenticationProvider = new KeycloakAuthenticationProvider();
//        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
//        authorityMapper.setPrefix("ROLE_");
//        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(authorityMapper);
//        return keycloakAuthenticationProvider;
//    }
//
//    /**
//     * Основная конфигурация безопасности.
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(authorize -> authorize
//                .requestMatchers("/public/**").permitAll()   // Публичные маршруты
//                .anyRequest().authenticated()                // Все остальные маршруты защищены
//            )
//            .oauth2ResourceServer(oauth2 -> oauth2
//                .jwt(jwt -> jwt                            // Использование JWT для проверки токенов
//                    .jwkSetUri("http://localhost:8082/realms/spmia-realm/protocol/openid-connect/certs") // Замените на URL JWK-сета вашего Keycloak
//                )
//            )
//            .csrf(csrf -> csrf
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())  // Настройка CSRF с использованием токенов
//            );
//
//        return http.build();
//    }
//
//    /**
//     * Настройка стратегии сессии.
//     */
//    @Bean
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new NullAuthenticatedSessionStrategy();
//    }
//
//    /**
//     * Регистрация пользователей в сессиях (если требуется).
//     */
//    @Bean
//    protected SessionRegistryImpl sessionRegistry() {
//        return new SessionRegistryImpl();
//    }
//}
