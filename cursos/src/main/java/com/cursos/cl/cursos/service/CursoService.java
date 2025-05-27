package com.cursos.cl.cursos.service;

import com.cursos.cl.cursos.model.Curso;
import com.cursos.cl.cursos.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService { //

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getCursos() {
        return cursoRepository.findAll();
    }

    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso getCursoById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
    }

    public Curso updateCurso(Long id, Curso cursoActualizado) {
        Curso cursoExistente = getCursoById(id);
        cursoExistente.setNombre(cursoActualizado.getNombre());
        cursoExistente.setDescripcion(cursoActualizado.getDescripcion());
        cursoExistente.setDuracion(cursoActualizado.getDuracion());
        return cursoRepository.save(cursoExistente);
    }

    public Boolean deleteCurso(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
