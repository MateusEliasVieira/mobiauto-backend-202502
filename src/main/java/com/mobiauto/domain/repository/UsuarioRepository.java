package com.mobiauto.domain.repository;

import com.mobiauto.domain.enums.RolePerfilUsuario;
import com.mobiauto.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Modifying
    @Query("UPDATE Usuario u SET u.nome = :nome, u.email = :email, u.perfil = :perfil WHERE u.idUsuario = :idUsuario")
    void atualizarUsuarioPorId(@Param("idUsuario") Long idUsuario, @Param("nome") String nome, @Param("email") String email, @Param("perfil") RolePerfilUsuario perfil);

//  @Query("SELECT u FROM Usuario u WHERE u.token = :token")
    Optional<Usuario> findByToken(@Param("token") String token);

    Optional<Usuario> findByEmail(String email);
}
