package com.school.manager.school_manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.school.manager.school_manager.dtos.DisciplinaRequest;
import com.school.manager.school_manager.helper.ResponseHelper;
import com.school.manager.school_manager.models.Disciplina;
import com.school.manager.school_manager.services.DisciplinaService;
import com.school.manager.school_manager.views.Views;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<?> getAllDisciplinas() {
        try {
            List<Disciplina> items = disciplinaService.findAll();
            return ResponseEntity.ok(ResponseHelper.buildResponse(items));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<?> getDisciplinaById(@PathVariable Long id) {
        try {
            Disciplina disciplina = disciplinaService.findById(id);
            return ResponseEntity.ok(ResponseHelper.buildResponse(disciplina));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<?> createDisciplina(@RequestBody DisciplinaRequest request) {
        try {
            Disciplina itemCriado = disciplinaService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseHelper.buildResponse(itemCriado, "Disciplina criada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<?> updateDisciplina(@PathVariable Long id, @RequestBody DisciplinaRequest disciplina) {
        try {
            Disciplina retorno = disciplinaService.update(disciplina, id);
            return ResponseEntity.ok(ResponseHelper.buildResponse(retorno, "Disciplina atualizada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDisciplina(@PathVariable Long id) {
        try {
            disciplinaService.deleteById(id);
            return ResponseEntity.ok(ResponseHelper.buildResponse(null, "Disciplina " + id + " deletada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
