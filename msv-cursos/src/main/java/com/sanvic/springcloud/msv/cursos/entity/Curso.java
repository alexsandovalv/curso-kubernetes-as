package com.sanvic.springcloud.msv.cursos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CursoUsuario> cursoUsuarios;

    public void addCursoUsuario(CursoUsuario cursoUsuario) {
        cursoUsuarios.add(cursoUsuario);
    }

    public void removeCursoUsuario(CursoUsuario cursoUsuario) {
        cursoUsuarios.remove(cursoUsuario);
    }

}
