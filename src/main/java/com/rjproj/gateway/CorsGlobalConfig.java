package com.rjproj.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsGlobalConfig {

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
                "http://localhost:4200",
                "http://localhost:4500",
                "http://127.0.0.1:30055",
                "http://angular-app:4200",
                "http://ec2-51-20-206-136.eu-north-1.compute.amazonaws.com:4200",
                "http://ec2-13-53-224-124.eu-north-1.compute.amazonaws.com:4200",
                "http://13.53.224.124:4200",
                "http://51.20.206.136:4200",
                "http://a8685bbecfb704a3d9be9ff7c79d600a-1930177911.eu-north-1.elb.amazonaws.com",
                "https://localhost:4200",
                "http://miyembroprod.s3-website.eu-north-1.amazonaws.com"
                )
        ); // Allowed frontend origin
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed HTTP methods
        config.setAllowedHeaders(List.of("*")); // Allow all headers
        config.setAllowCredentials(true); // Allow credentials like cookies

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}