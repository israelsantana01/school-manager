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

import com.school.manager.school_manager.dtos.turmas.TurmaRequest;
import com.school.manager.school_manager.helper.ResponseHelper;
import com.school.manager.school_manager.services.TurmaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {
    private String className = "Turma";
    private String POST_SUCCESS_MSG = className + " criada com sucesso!";
    private String PUT_SUCCESS_MSG = className + " atualizada com sucesso!";
    private String DELETE_SUCCESS_MSG = className + " deletada com sucesso!";

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public ResponseEntity<?> getAllTurmas() {
        try {
            return ResponseEntity.ok(ResponseHelper.buildResponse(turmaService.findAll()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlunoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ResponseHelper.buildResponse(turmaService.findById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createTurma(@Valid @RequestBody TurmaRequest turma, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> mensagens = result.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
                return ResponseEntity.badRequest().body(ResponseHelper.buildResponse(null, mensagens.toString()));
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseHelper.buildResponse(turmaService.save(turma), POST_SUCCESS_MSG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTurma(@PathVariable Long id, @Valid @RequestBody TurmaRequest turma, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> mensagens = result.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
                return ResponseEntity.badRequest().body(ResponseHelper.buildResponse(null, mensagens.toString()));
            }

            return ResponseEntity.ok(ResponseHelper.buildResponse(turmaService.update(turma, id), PUT_SUCCESS_MSG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTurma(@PathVariable Long id) {
        try {
            turmaService.deleteById(id);
            return ResponseEntity.ok(ResponseHelper.buildResponse(null, className + " com id " + id + " deletada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }
}
