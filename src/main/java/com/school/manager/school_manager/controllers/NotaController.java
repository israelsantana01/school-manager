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

import com.school.manager.school_manager.dtos.notas.NotaRequest;
import com.school.manager.school_manager.helper.ResponseHelper;
import com.school.manager.school_manager.services.NotaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    private String className = "Nota";
    private String POST_SUCCESS_MSG = className + " criada com sucesso!";
    private String PUT_SUCCESS_MSG = className + " atualizada com sucesso!";

    @Autowired
    private NotaService notaService;

    @GetMapping
    public ResponseEntity<?> getAllNotas() {
        try {
            return ResponseEntity.ok(ResponseHelper.buildResponse(notaService.findAll()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @GetMapping("/{alunoId}")
    public ResponseEntity<?> getNotaById(@PathVariable Long alunoId) {
        try {
            return ResponseEntity.ok(ResponseHelper.buildResponse(notaService.findByAlunoId(alunoId)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createNota(@Valid @RequestBody NotaRequest aluno, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> mensagens = result.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
                return ResponseEntity.badRequest().body(ResponseHelper.buildResponse(null, mensagens.toString()));
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseHelper.buildResponse(notaService.save(aluno), POST_SUCCESS_MSG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNota(@PathVariable Long id, @Valid @RequestBody NotaRequest aluno, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> mensagens = result.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
                return ResponseEntity.badRequest().body(ResponseHelper.buildResponse(null, mensagens.toString()));
            }

            return ResponseEntity.ok(ResponseHelper.buildResponse(notaService.update(aluno, id), PUT_SUCCESS_MSG));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNota(@PathVariable Long id) {
        try {
            notaService.deleteById(id);
            return ResponseEntity.ok(ResponseHelper.buildResponse(null, className + " com id " + id + " deletada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseHelper.buildResponse(null, e.getMessage()));
        }
    }
}
