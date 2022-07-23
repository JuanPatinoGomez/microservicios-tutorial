package com.example.carroservice.servicio;

import com.example.carroservice.entidades.Carro;
import com.example.carroservice.repositorio.CarroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroServiceImpl{

    @Autowired
    private CarroRepositorio carroRepositorio;

    public List<Carro> getAll() {
        return carroRepositorio.findAll();
    }

    public Carro getById(int id) {
        return carroRepositorio.findById(id).orElse(null);
    }

    public Carro save(Carro carro) {
        return carroRepositorio.save(carro);
    }

    public List<Carro> getAllByUsuarioId(int usuarioId){
        return carroRepositorio.findByUsuarioId(usuarioId);
    }
}
