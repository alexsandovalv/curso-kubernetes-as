package com.sanvic.springcloud.msv.usuarios.repository;

import com.sanvic.springcloud.msv.usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
