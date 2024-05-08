package com.example.setup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(reg -> {
            reg.requestMatchers("/").permitAll();
            reg.requestMatchers("/api/user/**").hasRole("USER");
            reg.requestMatchers("/api/admin/**").hasRole("ADMIN");
            reg.anyRequest().authenticated();
        });
        http.httpBasic(withDefaults());
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        var user = User.builder()
                .username("user")
                .password("$2a$12$.mYdT0quk43vPBNjglyfSeJoOLd7KCWDQxUBAODCVftNuiCFra7Ee")
                .roles("USER")
                .build();
        var admin = User.builder()
                .username("admin")
                .password("$2a$12$OaedKjZPKcHIxNf0sist5.N/zN0A1rRE0Ot48ERc7DX4r2lCuT/2G")
                .roles("ADMIN","USER")
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
