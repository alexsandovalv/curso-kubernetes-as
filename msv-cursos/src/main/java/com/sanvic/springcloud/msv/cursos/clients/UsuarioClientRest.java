package com.sanvic.springcloud.msv.cursos.clients;

import com.sanvic.springcloud.msv.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "msv-usuarios", url = "localhost:8081")
public interface UsuarioClientRest {

    @GetMapping("/{id}")
    public Usuario detalle(@PathVariable Long id);

    @PostMapping("/")
    Usuario crear(@RequestBody Usuario usuario);

}
