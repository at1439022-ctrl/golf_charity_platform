package com.digitalheroes.golf_charity_platform.config;
import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()
            // FIX: Allow BOTH roles to access the dashboard
            .requestMatchers("/dashboard/**").hasAnyRole("SUBSCRIBER", "ADMIN") 
            .requestMatchers("/api/scores/**").hasAnyRole("SUBSCRIBER", "ADMIN")
            // Restricted Admin-only area
            .requestMatchers("/admin/**", "/api/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        // ... rest of your config

        .formLogin(form -> form
            .loginPage("/login") // This MUST be permitted in the requestMatchers above
            .defaultSuccessUrl("/dashboard", true)
            .permitAll() 
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/")
            .permitAll()
        );
    
    return http.build();
}
 @Bean
public UserDetailsService userDetailsService() {
    UserDetails subscriber = User.builder()
        .username("user@example.com")
        .password("{noop}user123") // {noop} is for plain text (development only)
        .roles("SUBSCRIBER")
        .build();

    UserDetails admin = User.builder()
        .username("admin@digitalheroes.co.in")
        .password("{noop}admin123")
        .roles("ADMIN")
        .build();

    return new InMemoryUserDetailsManager(subscriber, admin);
}
}