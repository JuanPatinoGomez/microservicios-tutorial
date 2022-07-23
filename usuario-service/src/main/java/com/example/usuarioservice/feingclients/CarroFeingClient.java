package com.example.usuarioservice.feingclients;

import com.example.usuarioservice.modelos.Carro;
import com.example.usuarioservice.modelos.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "carro-service", url = "http://localhost:8082/carro")
//@RequestMapping("/carro")
public interface CarroFeingClient {

    @PostMapping
    public Carro save(@RequestBody Carro carro);

    @GetMapping("/usuario/{usuarioId}")
    public List<Carro> getCarros(@PathVariable int usuarioId);
}
