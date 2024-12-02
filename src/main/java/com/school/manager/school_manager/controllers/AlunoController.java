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

import com.school.manager.school_manager.models.Aluno;
import com.school.manager.school_manager.service.AlunoService;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    // Listar todos os alunos
    @GetMapping
    public ResponseEntity<?> getAllAlunos() {
        try {
            List<Aluno> alunos = alunoService.findAll();
            return ResponseEntity.ok(alunos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Buscar aluno por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAlunoById(@PathVariable Long id) {
        try {
            Aluno aluno = alunoService.findById(id);
            return ResponseEntity.ok(aluno);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Criar um novo aluno
    @PostMapping
    public ResponseEntity<?> createAluno(@RequestBody Aluno aluno) {
        try {
            alunoService.save(aluno);
            return ResponseEntity.status(HttpStatus.CREATED).body("Aluno salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // // Atualizar um aluno existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        try {
            aluno.setId(id);
            alunoService.update(aluno);
            return ResponseEntity.ok("Aluno salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // // Deletar um aluno
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAluno(@PathVariable Long id) {
        try {
            alunoService.deleteById(id);
            return ResponseEntity.ok("Aluno deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
