package cl.duocuc.darmijo.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@EnableWebSecurity
@Configuration
@EnableWebMvc
@ComponentScan
public class WebSecurityConfig implements ApplicationContextAware, WebMvcConfigurer {
    
    private ApplicationContext applicationContext;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/images/**")
            .addResourceLocations("classpath:/static/images/");
        registry
            .addResourceHandler("/css/**")
            .addResourceLocations("classpath:/static/css/");
    }
    
    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        return http

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll().anyRequest().authenticated())
            .logout(l -> l
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("Authorization")
            )
            .build();
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    
    }
}
