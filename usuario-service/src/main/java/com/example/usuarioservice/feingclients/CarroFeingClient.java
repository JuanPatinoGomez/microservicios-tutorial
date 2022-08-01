package com.example.usuarioservice.feingclients;

import com.example.usuarioservice.modelos.Carro;
import com.example.usuarioservice.modelos.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "carro-service")
//@RequestMapping("/carro")
public interface CarroFeingClient {

    @PostMapping("/carro")
    public Carro save(@RequestBody Carro carro);

    @GetMapping("/carro/usuario/{usuarioId}")
    public List<Carro> getCarros(@PathVariable int usuarioId);
}
