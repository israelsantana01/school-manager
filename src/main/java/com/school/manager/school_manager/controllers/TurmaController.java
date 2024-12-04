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

import com.school.manager.school_manager.dtos.turmas.TurmaGetAllResponse;
import com.school.manager.school_manager.dtos.turmas.TurmaGetByIdResponse;
import com.school.manager.school_manager.helper.ResponseHelper;
import com.school.manager.school_manager.models.Turma;
import com.school.manager.school_manager.services.TurmaService;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public ResponseEntity<?> getAllTurmas() {
        try {
            List<TurmaGetAllResponse> res = turmaService.findAll();
            return ResponseEntity.ok(ResponseHelper.buildResponse(res));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlunoById(@PathVariable Long id) {
        try {
            TurmaGetByIdResponse turma = turmaService.findById(id);
            return ResponseEntity.ok(ResponseHelper.buildResponse(turma));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createTurma(@RequestBody Turma turma) {
        try {
            Turma turmaCriada = turmaService.save(turma);
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseHelper.buildResponse(turmaCriada, "Turma criada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTurma(@PathVariable Long id, @RequestBody Turma turma) {
        try {
            turma.setId(id);
            turmaService.update(turma);
            return ResponseEntity.ok(ResponseHelper.buildResponse(turma, "Turma atualizada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTurma(@PathVariable Long id) {
        try {
            turmaService.deleteById(id);
            return ResponseEntity.ok(ResponseHelper.buildResponse(null, "Turma " + id + " deletada com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
