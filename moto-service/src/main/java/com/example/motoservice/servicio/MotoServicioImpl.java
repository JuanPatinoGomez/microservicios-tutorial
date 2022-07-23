package com.example.motoservice.servicio;

import com.example.motoservice.entidades.Moto;
import com.example.motoservice.repositorio.MotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoServicioImpl{

    @Autowired
    private MotoRepositorio motoRepositorio;

    public List<Moto> getAll() {
        return motoRepositorio.findAll();
    }

    public Moto getById(int id) {
        return motoRepositorio.findById(id).orElse(null);
    }

    public Moto save(Moto moto) {
        return motoRepositorio.save(moto);
    }

    public List<Moto> getAllByUsuarioId(int usuarioId) {
        return motoRepositorio.findByUsuarioId(usuarioId);
    }
}
