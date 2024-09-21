package com.sanvic.springcloud.msv.cursos.repository;

import com.sanvic.springcloud.msv.cursos.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
}
