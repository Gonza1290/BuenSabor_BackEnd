package com.example.tp_final.Config;

import com.example.tp_final.Jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authRequest ->
              authRequest
                       .requestMatchers(publicEndPoints()).permitAll()
                      .requestMatchers(PathRequest.toH2Console()).permitAll()
                      .requestMatchers(authenticatedEndPoints()).authenticated()
                      .requestMatchers(empleadosEndPoints()).hasAnyAuthority("Cocinero","Cajero","Delivery","Administrador")
                      //.requestMatchers(new AntPathRequestMatcher("/api/v1/pedidos","/api/v1/personas/{id}")).authenticated()
                      .anyRequest().hasAnyAuthority("Administrador")
                )
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .sessionManagement(sessionManager->
                sessionManager
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();


    }
    private RequestMatcher publicEndPoints(){
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/auth/**"),
                new AntPathRequestMatcher("/api/v1/articulos/manufacturados/paged"),
                new AntPathRequestMatcher("/api/v1/articulos/insumos/paged"),
                new AntPathRequestMatcher("/api/v1/localidades/paged"),
                new AntPathRequestMatcher("/api/v1/articulos/insumos/searchByDenominacion"),
                new AntPathRequestMatcher("/api/v1/articulos/manufacturados/searchByDenominacion")
                );
    }
    private RequestMatcher authenticatedEndPoints(){
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/api/v1/pedidos/findByCliente/**"),
                new AntPathRequestMatcher("/api/v1/facturas/{id}"),
                new AntPathRequestMatcher("/api/v1/personas/searchByNombre/**"),
                new AntPathRequestMatcher("/api/v1/personas/{id}")


                );
    }
    private RequestMatcher empleadosEndPoints(){
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/api/v1/pedidos/**"),
                new AntPathRequestMatcher("/api/v1/rubros/**"),
                new AntPathRequestMatcher("/api/v1/unidadmedidas/**"),
                new AntPathRequestMatcher("/api/v1/articulos/manufacturados/**"),
                new AntPathRequestMatcher("/api/v1/articulos/insumos/**")

        );
    }

}
