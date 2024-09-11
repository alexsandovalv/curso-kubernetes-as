package com.sanvic.springcloud.msv.cursos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cursos_usuarios")
public class CursoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "usuario_id", unique = true)
    private Long usuarioId;

    @Override
    public boolean equals(Object obj) {
        if( this == obj) {
            return true;
        }
        if( !(obj instanceof CursoUsuario) ) {
            return false;
        }
        CursoUsuario other = (CursoUsuario) obj;
        return this.usuarioId != null && this.usuarioId.equals(other.usuarioId);
    }
}
