package com.cursos.cl.cursos.controller;

import com.cursos.cl.cursos.model.Curso;
import com.cursos.cl.cursos.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping //obtener todos los cursos
    public List<Curso> listarCursos() {
        return cursoService.getCursos();
    }

    @PostMapping
    public ResponseEntity<?> agregarCurso(@Validated @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Body incompleto o inválido");
        }
        cursoService.saveCurso(curso);
        return ResponseEntity.ok(true);
    }

    @GetMapping("{id}")
    public Curso buscarCurso(@PathVariable Long id) {
        return cursoService.getCursoById(id);
    }

    @PutMapping("{id}")
    public Curso actualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        return cursoService.updateCurso(id, curso);
    }

    @DeleteMapping("{id}")
    public Boolean eliminarCurso(@PathVariable Long id) {
        return cursoService.deleteCurso(id);
    }
}