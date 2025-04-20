package ru.vsu.foreign_language_courses.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.foreign_language_courses.dto.StudentRequest;
import ru.vsu.foreign_language_courses.dto.StudentResponse;
import ru.vsu.foreign_language_courses.services.StudentService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@RequestBody StudentRequest req) {
        StudentResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/students/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable String id, @RequestBody StudentRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
