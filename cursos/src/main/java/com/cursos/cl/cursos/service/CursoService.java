package com.cursos.cl.cursos.service;

import com.cursos.cl.cursos.model.Curso;
import com.cursos.cl.cursos.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marca la clase como un servicio Spring
public class CursoService { //

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getCursos() {
        return cursoRepository.findAll(); // Obtiene todos los cursos
    }

    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso); // Guarda el curso
    }

    public Curso getCursoById(Long id) {
        return cursoRepository.findById(id) // Busca el curso por ID o lanza una excepción si no existe
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
    }

    public Curso updateCurso(Long id, Curso cursoActualizado) {
        Curso cursoExistente = getCursoById(id); // Verifica existencia
        cursoExistente.setNombre(cursoActualizado.getNombre());
        cursoExistente.setDescripcion(cursoActualizado.getDescripcion());
        cursoExistente.setDuracion(cursoActualizado.getDuracion());
        return cursoRepository.save(cursoExistente); // Guarda cambios
    }

    public Boolean deleteCurso(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return true; // Confirmación de borrado
        } else {
            return false;
        }
    }
}
