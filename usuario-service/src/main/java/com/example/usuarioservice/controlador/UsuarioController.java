package com.example.usuarioservice.controlador;

import com.example.usuarioservice.entidades.Usuario;
import com.example.usuarioservice.modelos.Carro;
import com.example.usuarioservice.modelos.Moto;
import com.example.usuarioservice.servicio.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List<Usuario> usuarios = this.usuarioServiceImpl.getAll();
        if(usuarios.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable int id){
        Usuario usuario = this.usuarioServiceImpl.getById(id);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        return new ResponseEntity<>(this.usuarioServiceImpl.save(usuario), HttpStatus.OK);
    }

    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarros(@PathVariable int usuarioId){
        Usuario usuario = this.usuarioServiceImpl.getById(usuarioId);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Carro> carros = this.usuarioServiceImpl.getCarros(usuario.getId());
        if(carros == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Carro>>(carros, HttpStatus.OK);
    }

    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotos(@PathVariable int usuarioId){
        Usuario usuario = this.usuarioServiceImpl.getById(usuarioId);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Moto> motos = this.usuarioServiceImpl.getMotos(usuario.getId());
        if(motos == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Moto>>(motos, HttpStatus.OK);
    }

    //Metodos feignClient
    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro(@PathVariable int usuarioId, @RequestBody Carro carro){
        Carro nuevoCarro = this.usuarioServiceImpl.saveCarro(usuarioId, carro);
        return new ResponseEntity<>(nuevoCarro, HttpStatus.OK);
    }

    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable int usuarioId, @RequestBody Moto moto){
        Moto nuevaMoto = this.usuarioServiceImpl.saveMoto(usuarioId, moto);
        return new ResponseEntity<>(nuevaMoto, HttpStatus.OK);
    }

    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable int usuarioId){
        Map<String, Object> resultado = this.usuarioServiceImpl.getUsuarioAndVehiculos(usuarioId);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
