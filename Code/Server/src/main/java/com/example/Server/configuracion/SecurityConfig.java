package com.example.Server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Configuración de Seguridad para el Backend
 * 
 * Esta configuración:
 * - Deshabilita CSRF (necesario para APIs REST)
 * - Permite acceso sin autenticación a todos los endpoints
 * - Configura CORS para permitir peticiones desde el frontend (localhost:4200)
 * 
 * NOTA: Esta es una configuración para DESARROLLO.
 * En PRODUCCIÓN deberías implementar autenticación JWT o similar.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    /**
     * Configuración de seguridad HTTP
     * Permite acceso público a todos los endpoints sin autenticación
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Habilitar CORS con la configuración personalizada
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // Deshabilitar CSRF (no necesario para APIs REST stateless)
            .csrf(csrf -> csrf.disable())
            
            // Configurar autorizaciones - permitir todo sin autenticación
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll()  // Permitir todos los endpoints de API
                .requestMatchers("/h2-console/**").permitAll()  // Permitir consola H2
                .anyRequest().permitAll()  // Permitir cualquier otra petición
            )
            
            // Deshabilitar frame options para permitir H2 console
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())
            );
        
        return http.build();
    }
    
    /**
     * Configuración de CORS (Cross-Origin Resource Sharing)
     * Permite que el frontend Angular se comunique con el backend
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Permitir peticiones desde el frontend Angular
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:4200",  // Angular dev server
            "http://localhost:8080"   // Mismo origen
        ));
        
        // Permitir todos los métodos HTTP
        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"
        ));
        
        // Permitir todos los headers
        configuration.setAllowedHeaders(Arrays.asList("*"));
        
        // Permitir credenciales (cookies, authorization headers)
        configuration.setAllowCredentials(true);
        
        // Exponer headers personalizados si los hay
        configuration.setExposedHeaders(Arrays.asList(
            "Authorization", 
            "Content-Type",
            "X-Total-Count"
        ));
        
        // Cachear la configuración CORS por 1 hora
        configuration.setMaxAge(3600L);
        
        // Aplicar configuración a todos los endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}
