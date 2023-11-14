package com.codegym.phimchill.configuration;

import com.codegym.phimchill.security.JwtAuthEntryPoint;
import com.codegym.phimchill.security.JwtAuthFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableAutoConfiguration
@EnableAsync
@EnableWebSecurity
@ComponentScan(basePackages = {"com.codegym.phimchill"})
public class SecurityConfiguration {
    @Autowired
    private JwtAuthEntryPoint authEntryPoint;

    @Bean
    public Filter jwtAuthenticationFilter() {
        return new JwtAuthFilter();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable);

        http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authEntryPoint)
                        .accessDeniedPage("/api/auth/access-denied"))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/auth/register").permitAll());

        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/auth/login").permitAll());

        // Configure remember me (save token in database)
        http.rememberMe((remember) -> remember
                .tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(24 * 60 * 60)
        );

        // Use JwtAuthorizationFilter to check token -> get user info
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }
}