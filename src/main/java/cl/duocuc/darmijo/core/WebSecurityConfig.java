package cl.duocuc.darmijo.core;

import cl.duocuc.darmijo.core.filters.CustomJwtAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@EnableWebSecurity()
@Configuration
public class WebSecurityConfig {
    
    @Autowired
    CustomJwtAuthorizationFilter customJwtAuthorizationFilter;
    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        try {
            log.info("Iniciando security");
            http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/access/**").permitAll()
                    .anyRequest().authenticated())
                .addFilterAfter(customJwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        } catch (Exception e) {
            
            log.error("Error configuring security filter chain: {}", e.getMessage());
            e.printStackTrace();
        }
        
        
        return http.build();
        
    }
}
