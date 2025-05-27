package com.cursos.cl.cursos.controller;

import com.cursos.cl.cursos.model.Curso;
import com.cursos.cl.cursos.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; //para controlar la respuesta HTTP
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
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(true);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> buscarCurso(@PathVariable Long id) {
        try {
            Curso curso = cursoService.getCursoById(id);
            return ResponseEntity.ok(curso);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Curso con id " + id + " no encontrado");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> actualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        try {
            Curso actualizado = cursoService.updateCurso(id, curso);
            return ResponseEntity.ok(actualizado); //si el curso existe devuelve un 200 OK con el curso actualizado
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Curso con id " + id + " no encontrado"); //si no existe, envía una excepción y responde con un eror 404
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarCurso(@PathVariable Long id) {
        boolean eliminado = cursoService.deleteCurso(id);
        if (eliminado) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Curso con id " + id + " no encontrado");
        }
    }
}