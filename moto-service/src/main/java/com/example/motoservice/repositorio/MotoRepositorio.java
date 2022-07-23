package com.example.motoservice.repositorio;

import com.example.motoservice.entidades.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoRepositorio extends JpaRepository<Moto, Integer> {
    List<Moto> findByUsuarioId(int usuarioId);
}
