package com.example.usuarioservice.servicio;

import com.example.usuarioservice.entidades.Usuario;
import com.example.usuarioservice.feingclients.CarroFeingClient;
import com.example.usuarioservice.feingclients.MotoFeingClient;
import com.example.usuarioservice.modelos.Carro;
import com.example.usuarioservice.modelos.Moto;
import com.example.usuarioservice.repositorio.UsuarioRespositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioServiceImpl{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRespositorio usuarioRespositorio;

    @Autowired
    CarroFeingClient carroFeingClient;

    @Autowired
    MotoFeingClient motoFeingClient;

    public List<Carro> getCarros(int usuarioId){
        List<Carro> carros = restTemplate.getForObject("http://localhost:8082/carro/usuario/" + usuarioId, List.class);
        return carros;
    }

    public List<Moto> getMotos(int usuarioId){
        List<Moto> motos = restTemplate.getForObject("http://localhost:8083/moto/usuario/" + usuarioId, List.class);
        return motos;
    }
    public List<Usuario> getAll() {
        return usuarioRespositorio.findAll();
    }

    public Usuario getById(int id) {
        return usuarioRespositorio.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRespositorio.save(usuario);
    }

    public Carro saveCarro(int usuarioId, Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeingClient.save(carro);
        return nuevoCarro;
    }

    public Moto saveMoto(int usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motoFeingClient.save(moto);
        return nuevaMoto;
    }

    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
        Map<String, Object> resultado = new HashMap<>();
        Usuario usuario = usuarioRespositorio.findById(usuarioId).orElse(null);

        if(usuario == null){
            resultado.put("mensaje", "El usuario no existe");
            return resultado;
        }
        resultado.put("usuario", usuario);
        List<Carro> carros = carroFeingClient.getCarros(usuarioId);
        if(carros == null){
            resultado.put("mensaje", "El usuario no tiene carros");
        }else{
            resultado.put("carros", carros);
        }
        List<Moto> motos = motoFeingClient.getMotos(usuarioId);
        if(motos == null){
            resultado.put("mensaje", "El usuario no tiene motos");
        }else{
            resultado.put("motos", motos);
        }

        return resultado;

    }
}
