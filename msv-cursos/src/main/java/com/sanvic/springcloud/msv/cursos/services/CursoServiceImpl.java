package com.sanvic.springcloud.msv.cursos.services;

import com.sanvic.springcloud.msv.cursos.clients.UsuarioClientRest;
import com.sanvic.springcloud.msv.cursos.models.Usuario;
import com.sanvic.springcloud.msv.cursos.models.entity.Curso;
import com.sanvic.springcloud.msv.cursos.models.entity.CursoUsuario;
import com.sanvic.springcloud.msv.cursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService{

    private final CursoRepository repository;

    private final UsuarioClientRest usuarioClientRest;

    @Autowired
    public CursoServiceImpl(CursoRepository repository, UsuarioClientRest usuarioClientRest) {
        this.repository = repository;
        this.usuarioClientRest = usuarioClientRest;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> o = repository.findById(cursoId);
        if( o.isPresent() ) {
            Usuario userMsvc = usuarioClientRest.detalle(usuario.getId());

            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(userMsvc.getId());

            curso.addCursoUsuario(cursoUsuario);
            repository.save(curso);

            return Optional.of(userMsvc);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> o = repository.findById(cursoId);
        if( o.isPresent() ) {
            Usuario userNuevoMsvc = usuarioClientRest.crear(usuario);

            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(userNuevoMsvc.getId());

            curso.addCursoUsuario(cursoUsuario);
            repository.save(curso);

            return Optional.of(userNuevoMsvc);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> o = repository.findById(cursoId);
        if( o.isPresent() ) {
            Usuario userMsvc = usuarioClientRest.detalle(usuario.getId());

            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(userMsvc.getId());

            curso.removeCursoUsuario(cursoUsuario);
            repository.save(curso);

            return Optional.of(userMsvc);
        }
        return Optional.empty();
    }
}
