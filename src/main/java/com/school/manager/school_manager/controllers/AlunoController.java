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

import com.school.manager.school_manager.dtos.alunos.AlunoRequest;
import com.school.manager.school_manager.helper.ResponseHelper;
import com.school.manager.school_manager.services.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    private String className = "Aluno(a)";
    private String POST_SUCCESS_MSG = className + " criado(a) com sucesso!";
    private String PUT_SUCCESS_MSG = className + " atualizado(a) com sucesso!";
    private String DELETE_SUCCESS_MSG = className + " deletado(a) com sucesso!";

    @Autowired
    private AlunoService alunoService;

    // Listar todos os alunos
    @GetMapping
    public ResponseEntity<?> getAllAlunos() {
        try {
            return ResponseEntity.ok(ResponseHelper.buildResponse(alunoService.findAll()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    // Buscar aluno por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAlunoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ResponseHelper.buildResponse(alunoService.findById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    // Criar um novo aluno
    @PostMapping
    public ResponseEntity<?> createAluno(@Valid @RequestBody AlunoRequest aluno, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> mensagens = result.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
                return ResponseEntity.badRequest().body(ResponseHelper.buildResponse(null, mensagens.toString()));
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseHelper.buildResponse(alunoService.save(aluno), POST_SUCCESS_MSG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    // // Atualizar um aluno existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAluno(@PathVariable Long id, @Valid @RequestBody AlunoRequest aluno, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> mensagens = result.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
                return ResponseEntity.badRequest().body(ResponseHelper.buildResponse(null, mensagens.toString()));
            }

            return ResponseEntity.ok(ResponseHelper.buildResponse(alunoService.update(aluno, id), PUT_SUCCESS_MSG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    // // Deletar um aluno
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAluno(@PathVariable Long id) {
        try {
            alunoService.deleteById(id);
            return ResponseEntity.ok(ResponseHelper.buildResponse(null, DELETE_SUCCESS_MSG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }
}
