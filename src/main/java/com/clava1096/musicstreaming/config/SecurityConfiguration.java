package com.clava1096.musicstreaming.config;

import com.clava1096.musicstreaming.config.jwt.JwtAuthEntryPoint;
import com.clava1096.musicstreaming.config.jwt.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration{

    private final JwtAuthFilter jwtAuthFilter;

    private final JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/reg",
                                "/login",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/actuator/**"
                        )
                        .permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Маршруты для треков
                        .requestMatchers(HttpMethod.POST, "/track").hasAnyRole("ARTIST", "ADMIN", "MODERATOR")
                        .requestMatchers(HttpMethod.PUT, "/track/{id}/upload").hasAnyRole("ARTIST", "ADMIN", "MODERATOR")
                        .requestMatchers(HttpMethod.GET, "/tracks/{id}", "/track/{id}/stream-url").hasAnyRole("USER", "ARTIST", "MODERATOR", "ADMIN")

                        // Маршруты для жанров
                        .requestMatchers(HttpMethod.POST, "/genres").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/genres/{id}").hasRole("ADMIN")

                        // Маршруты для альбомов
                        .requestMatchers(HttpMethod.POST, "/albums").hasAnyRole("ARTIST", "ADMIN", "MODERATOR")
                        .requestMatchers(HttpMethod.GET, "/albums/{id}").hasAnyRole("USER", "ARTIST", "MODERATOR", "ADMIN")

                        // Маршруты для артистов
                        .requestMatchers(HttpMethod.POST, "/artists").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/artists/requests").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/artists/{id}").hasAnyRole("USER", "ARTIST", "MODERATOR", "ADMIN")

                        // Маршруты для медиа-типов
                        .requestMatchers(HttpMethod.POST, "/media-type/create").hasRole("ARTIST")
                        .requestMatchers(HttpMethod.GET, "/media-type/{id}").hasAnyRole("USER", "ARTIST", "MODERATOR", "ADMIN")

                        // Админ-панель
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Все остальные запросы требуют аутентификации
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(handler -> handler.authenticationEntryPoint(unauthorizedHandler))
                .build();
    }

}
