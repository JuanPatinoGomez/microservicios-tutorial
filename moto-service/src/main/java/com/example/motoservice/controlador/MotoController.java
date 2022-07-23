package com.example.motoservice.controlador;

import com.example.motoservice.entidades.Moto;
import com.example.motoservice.servicio.MotoServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    private MotoServicioImpl motoServicioImpl;


    @GetMapping
    public ResponseEntity<List<Moto>> listarMotos(){
        List<Moto> motos = this.motoServicioImpl.getAll();
        if(motos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Moto>>(motos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerMoto(@PathVariable int id){
        Moto moto = this.motoServicioImpl.getById(id);
        if(moto == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Moto>(moto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto){
        return new ResponseEntity<>(this.motoServicioImpl.save(moto), HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotosPorUsuarioId(@PathVariable int usuarioId){
        List<Moto> motos = this.motoServicioImpl.getAllByUsuarioId(usuarioId);
        if(motos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Moto>>(motos, HttpStatus.OK);
    }


}
