package com.sanvic.springcloud.msv.cursos.controller;

import com.sanvic.springcloud.msv.cursos.models.Usuario;
import com.sanvic.springcloud.msv.cursos.models.entity.Curso;
import com.sanvic.springcloud.msv.cursos.services.CursoService;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CursoController {

    private final CursoService service;

    @Autowired
    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok().body(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Curso> optionalCurso = service.porId(id);
        if (optionalCurso.isPresent()) {
            return ResponseEntity.ok(optionalCurso.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result){
        if(result.hasErrors()){
            return validar(result);
        }

        Curso guardarDB = service.guardar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardarDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return validar(result);
        }

        Optional<Curso> o = service.porId(id);
        if (o.isPresent()) {
            Curso cursoDB = o.get();
            cursoDB.setNombre(curso.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cursoDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Curso> o = service.porId(id);
        if (o.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<?> asignarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId){
        Optional<Usuario> o = Optional.empty();
        try {
            service.asignarUsuario(usuario, cursoId);
        }
        catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje",
                            "No existe el usuario por el id o error en la comunicacion:" + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/crear-usuario/{cursoId}")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId){
        Optional<Usuario> o = Optional.empty();
        try {
            service.crearUsuario(usuario, cursoId);
        }
        catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje",
                            "No se pudo crear el usuario o error en la comunicacion:" + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-usuario/{cursoId}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId){
        Optional<Usuario> o = Optional.empty();
        try {
            service.eliminarUsuario(usuario, cursoId);
        }
        catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje",
                            "No existe el usuario pro el id o error en la comunicacion:" + e.getMessage()));
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errores.put(e.getField(), "El campo " + e.getField() + " es requerido " + e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
