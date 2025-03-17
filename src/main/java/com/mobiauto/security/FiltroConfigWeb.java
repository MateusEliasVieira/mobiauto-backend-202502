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
                //                                                  Endpoints de Login OK                                                  //
                // ======================================================================================================================= //
                .requestMatchers(HttpMethod.POST, "/login/*").permitAll()
                // ======================================================================================================================= //
                //                                                  Endpoints de Swagger OK                                                //
                // ======================================================================================================================= //
                .requestMatchers(HttpMethod.POST, "/swagger-ui/*").permitAll()
                // ======================================================================================================================= //
                //                                                  Endpoints do Usuário OK                                                //
                // ======================================================================================================================= //
                .requestMatchers(HttpMethod.POST, "/usuario/salvar").hasAuthority(RolePerfilUsuario.ROLE_GERENTE.name()) // Apenas Administradores, Proprietarios e Gerentes podem criar
                .requestMatchers(HttpMethod.PUT, "/usuario/atualizar").hasAuthority(RolePerfilUsuario.ROLE_GERENTE.name()) // Apenas Administradores, Proprietarios e Gerentes podem editar
                .requestMatchers(HttpMethod.GET, "/usuario/listar").hasAuthority(RolePerfilUsuario.ROLE_GERENTE.name()) // Apenas Administradores, Proprietarios e Gerentes podem listar
                .requestMatchers(HttpMethod.GET, "/usuario/listar/*").hasAuthority(RolePerfilUsuario.ROLE_GERENTE.name()) // Apenas Administradores, Proprietarios e Gerentes podem listar
                .requestMatchers(HttpMethod.DELETE, "/usuario/deletar/*").hasAuthority(RolePerfilUsuario.ROLE_GERENTE.name()) // Apenas Administradores, Proprietarios e Gerentes podem deletar
                // ======================================================================================================================= //
                //                                                  Endpoints do Cliente OK                                                //
                // ======================================================================================================================= //
                .requestMatchers(HttpMethod.POST, "/cliente/salvar").permitAll() // Os clientes podem se cadastrar
                .requestMatchers(HttpMethod.GET, "/cliente/listar").hasAuthority(RolePerfilUsuario.ROLE_ASSISTENTE.name()) // Apenas Administradores, Proprietarios, Gerentes e Assistentes podem listar clientes
                // ======================================================================================================================= //
                //                                                  Endpoints de Revenda OK                                                //
                // ======================================================================================================================= //
                .requestMatchers(HttpMethod.POST, "/revenda/salvar").hasAuthority(RolePerfilUsuario.ROLE_ADMINISTRADOR.name()) // Apenas Administradores podem criar revenda
                .requestMatchers(HttpMethod.POST, "/revenda/adicionar-usuario").hasAuthority(RolePerfilUsuario.ROLE_ADMINISTRADOR.name()) // Apenas Administradores podem criar revenda
                .requestMatchers(HttpMethod.PUT, "/revenda/atualizar").hasAuthority(RolePerfilUsuario.ROLE_ADMINISTRADOR.name()) // Apenas Administradores podem editar revenda
                .requestMatchers(HttpMethod.GET, "/revenda/listar").hasAnyAuthority(RolePerfilUsuario.ROLE_ADMINISTRADOR.name()) // Apenas Administradores podem listar
                .requestMatchers(HttpMethod.GET, "/revenda/listar/*").hasAnyAuthority(RolePerfilUsuario.ROLE_ADMINISTRADOR.name()) // Apenas Administradores podem listar
                .requestMatchers(HttpMethod.DELETE, "/revenda/deletar/*").hasAuthority(RolePerfilUsuario.ROLE_ADMINISTRADOR.name()) // Apenas Administradores podem deletar revenda
                // ======================================================================================================================= //
                //                                                  Endpoints de Oportunidade                                              //
                // ======================================================================================================================= //
                .requestMatchers(HttpMethod.POST, "/oportunidade/criar").hasAnyAuthority(RolePerfilUsuario.ROLE_GERENTE.name()) // Administrador, Proprietário e Gerente podem criar
                .requestMatchers(HttpMethod.POST, "/oportunidade/concluir").hasAnyAuthority(RolePerfilUsuario.ROLE_GERENTE.name()) // Administrador, Proprietário e Gerente podem criar
                .requestMatchers(HttpMethod.POST, "/oportunidade/transferir").hasAnyAuthority(RolePerfilUsuario.ROLE_GERENTE.name()) // Administrador, Proprietário e Gerente podem criar
                .requestMatchers(HttpMethod.PUT, "/oportunidade/atualizar").hasAnyAuthority(RolePerfilUsuario.ROLE_GERENTE.name()) // Administrador, Proprietário e Gerente podem editar
                .requestMatchers(HttpMethod.GET, "/oportunidade/listar").hasAnyAuthority(RolePerfilUsuario.ROLE_ADMINISTRADOR.name()) // Administrador, Proprietário, Gerente e Assistente podem listar
                .requestMatchers(HttpMethod.GET, "/oportunidade/listar/*").hasAnyAuthority(RolePerfilUsuario.ROLE_ADMINISTRADOR.name()) // Administrador, Proprietário, Gerente e Assistente podem listar
                .requestMatchers(HttpMethod.DELETE, "/oportunidade/deletar/*").hasAnyAuthority(RolePerfilUsuario.ROLE_ADMINISTRADOR.name()) // Administrador, Proprietário e Gerente podem deletar
                // ======================================================================================================================= //
                //                                                  Endpoints do Atendimento                                               //
                // ======================================================================================================================= //
                .requestMatchers(HttpMethod.POST, "/atendimento/salvar").hasAnyAuthority(RolePerfilUsuario.ROLE_ASSISTENTE.name()) // Administrador, Proprietário e Gerente podem salvar atendimento
                .requestMatchers(HttpMethod.GET, "/atendimento/listar").hasAnyAuthority(RolePerfilUsuario.ROLE_ASSISTENTE.name()) // Administrador, Proprietário, Gerente e Assistente podem listar
                // ======================================================================================================================= //
                //                                                  Endpoints do Veículo                                                   //
                // ======================================================================================================================= //
                .requestMatchers(HttpMethod.POST, "/veiculo/salvar").hasAnyAuthority(RolePerfilUsuario.ROLE_ADMINISTRADOR.name()) // Administrador, Proprietário e Gerente podem salvar veículo
                .requestMatchers(HttpMethod.GET, "/veiculo/listar").hasAnyAuthority(RolePerfilUsuario.ROLE_ADMINISTRADOR.name())  // Administrador, Proprietário, Gerente e Assistente podem listar

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
