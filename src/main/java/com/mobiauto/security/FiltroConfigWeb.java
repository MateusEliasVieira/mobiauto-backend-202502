package com.mobiauto.security;

import com.mobiauto.domain.enums.RolePerfilUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class FiltroConfigWeb {

    @Autowired
    private FiltroInterceptador filtroInterceptador;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // Permite todas as origens, você pode personalizar isso
        // Methods http authorized
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("DELETE");
        configuration.addAllowedMethod("OPTIONS");
        // Headers http authorized
        configuration.addAllowedHeader("Authorization");
        configuration.addAllowedHeader("Content-Type");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(); // cross origin resource sharing (compartilhamento de recursos de origens cruzadas)

        http.csrf(AbstractHttpConfigurer::disable); // Habilita a segurança contra ataques csrf (Cross-site request forgery)

        http.formLogin(AbstractHttpConfigurer::disable); // Desabilita formulários de login html

        http.httpBasic(AbstractHttpConfigurer::disable);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Sem sessões

        http.authorizeHttpRequests((auth) -> auth

                // ======================================================================================================================= //
                //												     Endpoints de Login										               //
                // ======================================================================================================================= //
                .requestMatchers(HttpMethod.POST, "/login/*").permitAll()
                // ======================================================================================================================= //
                // ======================================================================================================================= //
                //												     Endpoints do Usuário										           //
                // ======================================================================================================================= //
                .requestMatchers(HttpMethod.POST, "/usuario/add").permitAll()//.hasAuthority(RolePerfilUsuario.ROLE_GERENTE.name())
                .requestMatchers(HttpMethod.PUT, "/usuario/up").hasAuthority(RolePerfilUsuario.ROLE_GERENTE.name())
                // ======================================================================================================================= //

                .anyRequest().authenticated());
        http.addFilterBefore(this.filtroInterceptador, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
