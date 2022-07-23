package com.example.carroservice.controlador;

import com.example.carroservice.entidades.Carro;
import com.example.carroservice.servicio.CarroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroServiceImpl carroServiceImpl;

    @GetMapping
    public ResponseEntity<List<Carro>> listarCarros(){
        List<Carro> carros = this.carroServiceImpl.getAll();
        if(carros.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Carro>>(carros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> obtenerCarro(@PathVariable int id){
        Carro carro = this.carroServiceImpl.getById(id);
        if(carro == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Carro>(carro, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro){
        return new ResponseEntity<>(this.carroServiceImpl.save(carro), HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarrosPorUsuarioId(@PathVariable int usuarioId){
        List<Carro> carros = this.carroServiceImpl.getAllByUsuarioId(usuarioId);
        if(carros.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Carro>>(carros, HttpStatus.OK);
    }
}
