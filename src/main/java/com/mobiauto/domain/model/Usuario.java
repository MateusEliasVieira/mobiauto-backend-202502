package com.mobiauto.domain.model;

import com.mobiauto.domain.enums.RolePerfilUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    private RolePerfilUsuario perfil;
    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (this.perfil == RolePerfilUsuario.ROLE_PROPRIETARIO) {
            return List.of(new SimpleGrantedAuthority("ROLE_PROPRIETARIO"), new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"), new SimpleGrantedAuthority("ROLE_GERENTE"), new SimpleGrantedAuthority("ROLE_ASSISTENTE"));
        }
        else if (this.perfil == RolePerfilUsuario.ROLE_ADMINISTRADOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"), new SimpleGrantedAuthority("ROLE_GERENTE"), new SimpleGrantedAuthority("ROLE_ASSISTENTE"));
        }
        else if (this.perfil == RolePerfilUsuario.ROLE_GERENTE) {
            return List.of(new SimpleGrantedAuthority("ROLE_GERENTE"), new SimpleGrantedAuthority("ROLE_ASSISTENTE"));
        }
        else if (this.perfil == RolePerfilUsuario.ROLE_ASSISTENTE) {
            return List.of(new SimpleGrantedAuthority("ROLE_ASSISTENTE"));
        }
        else {
            return null;
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}