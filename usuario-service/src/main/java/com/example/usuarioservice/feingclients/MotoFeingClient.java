package com.example.usuarioservice.feingclients;

import com.example.usuarioservice.modelos.Carro;
import com.example.usuarioservice.modelos.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "moto-service", url = "http://localhost:8083/moto")
public interface MotoFeingClient {

    @PostMapping
    public Moto save(@RequestBody Moto moto);

    @GetMapping("/usuario/{usuarioId}")
    public List<Moto> getMotos(@PathVariable int usuarioId);

}
