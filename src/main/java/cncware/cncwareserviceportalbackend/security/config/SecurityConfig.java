package cncware.cncwareserviceportalbackend.security.config;

import cncware.cncwareserviceportalbackend.security.handlers.CustomAccessDeniedHandler;
import cncware.cncwareserviceportalbackend.security.handlers.CustomAuthenticationEntryPoint;
import cncware.cncwareserviceportalbackend.security.jwt.JwtAuthenticationFilter;
import cncware.cncwareserviceportalbackend.security.servics.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/error").permitAll()

                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers("/companies/**").hasRole("ADMIN")

                        .requestMatchers("/statuses/**").hasAnyRole("ADMIN", "USER", "CONSULTANT")

                        .requestMatchers("GET", "/tickets/**").hasAnyRole("ADMIN", "USER", "CONSULTANT")
                        .requestMatchers("POST", "/tickets").hasAnyRole("ADMIN", "USER", "CONSULTANT")
                        .requestMatchers("PUT", "/tickets/**").hasAnyRole("ADMIN", "CONSULTANT")
                        .requestMatchers("DELETE", "/tickets/**").hasAnyRole("ADMIN", "CONSULTANT")

                        .requestMatchers("POST", "/messages/**").hasAnyRole("ADMIN", "USER", "CONSULTANT")
                        .requestMatchers("DELETE", "/messages/**").hasAnyRole("ADMIN", "CONSULTANT")

                        .requestMatchers("GET", "/timers/**").hasAnyRole("ADMIN", "USER", "CONSULTANT")
                        .requestMatchers("POST", "/timers").hasAnyRole("USER", "CONSULTANT")
                        .requestMatchers("PUT", "/timers/**").hasAnyRole("USER", "CONSULTANT")
                        .requestMatchers("DELETE", "/timers/**").hasAnyRole("ADMIN", "CONSULTANT")

                        .requestMatchers("GET", "/notifications/**").hasAnyRole("ADMIN", "USER", "CONSULTANT")
                        .requestMatchers("POST", "/notifications").hasAnyRole("ADMIN", "CONSULTANT")
                        .requestMatchers("DELETE", "/notifications/**").hasRole("ADMIN")

                        .anyRequest().denyAll()
                )
                .userDetailsService(customUserDetailsService)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
