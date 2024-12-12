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

import com.school.manager.school_manager.dtos.professores.ProfessorRequest;
import com.school.manager.school_manager.helper.ResponseHelper;
import com.school.manager.school_manager.services.ProfessorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {
    private String className = "Professor(a)";
    private String POST_SUCCESS_MSG = className + " criado(a) com sucesso!";
    private String PUT_SUCCESS_MSG = className + " atualizado(a) com sucesso!";

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<?> getAllAlunos() {
        try {
            return ResponseEntity.ok(ResponseHelper.buildResponse(professorService.findAll()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlunoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ResponseHelper.buildResponse(professorService.findById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createProfessor(@Valid @RequestBody ProfessorRequest professor, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> mensagens = result.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
                return ResponseEntity.badRequest().body(ResponseHelper.buildResponse(null, mensagens.toString()));
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseHelper.buildResponse(professorService.save(professor), POST_SUCCESS_MSG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAluno(@PathVariable Long id, @Valid @RequestBody ProfessorRequest professor, BindingResult result) {
        try {
             if (result.hasErrors()) {
                List<String> mensagens = result.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
                return ResponseEntity.badRequest().body(ResponseHelper.buildResponse(null, mensagens.toString()));
            }

            return ResponseEntity.ok(ResponseHelper.buildResponse(professorService.update(professor, id), PUT_SUCCESS_MSG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAluno(@PathVariable Long id) {
        try {
            professorService.deleteById(id);
            return ResponseEntity.ok(ResponseHelper.buildResponse(null, className + " com id " + id + " deletado(a) com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }
}
