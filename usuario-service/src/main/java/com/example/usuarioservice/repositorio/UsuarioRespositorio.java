package com.example.usuarioservice.repositorio;

import com.example.usuarioservice.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRespositorio extends JpaRepository<Usuario, Integer> {
}
