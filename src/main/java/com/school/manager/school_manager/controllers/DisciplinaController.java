package com.school.manager.school_manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.manager.school_manager.dtos.DisciplinaRequest;
import com.school.manager.school_manager.helper.ResponseHelper;
import com.school.manager.school_manager.services.DisciplinaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {
    private String className = "Disciplina";
    private String POST_SUCCESS_MSG = className + " criada com sucesso!";
    private String PUT_SUCCESS_MSG = className + " atualizada com sucesso!";

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<?> getAllDisciplinas() {
        try {
            return ResponseEntity.ok(ResponseHelper.buildResponse(disciplinaService.findAll()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDisciplinaById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ResponseHelper.buildResponse(disciplinaService.findById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createDisciplina(@Valid @RequestBody DisciplinaRequest request, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> mensagens = result.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
                return ResponseEntity.badRequest().body(ResponseHelper.buildResponse(null, mensagens.toString()));
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseHelper.buildResponse(disciplinaService.save(request), POST_SUCCESS_MSG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDisciplina(@PathVariable Long id, @Valid @RequestBody DisciplinaRequest disciplina, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> mensagens = result.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
                return ResponseEntity.badRequest().body(ResponseHelper.buildResponse(null, mensagens.toString()));
            }

            return ResponseEntity.ok(ResponseHelper.buildResponse(disciplinaService.update(disciplina, id), PUT_SUCCESS_MSG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDisciplina(@PathVariable Long id) {
        try {
            disciplinaService.deleteById(id);
            return ResponseEntity.ok(ResponseHelper.buildResponse(null, className + " com id " + id + " deletada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }
}
