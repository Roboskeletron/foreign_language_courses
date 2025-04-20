package ru.vsu.foreign_language_courses.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.foreign_language_courses.dto.EducationRequest;
import ru.vsu.foreign_language_courses.dto.EducationResponse;
import ru.vsu.foreign_language_courses.services.EducationService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/educations")
@RequiredArgsConstructor
public class EducationController {
    private final EducationService service;

    @GetMapping
    public ResponseEntity<List<EducationResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EducationResponse> create(@RequestBody EducationRequest req) {
        EducationResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/educations/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationResponse> update(@PathVariable String id, @RequestBody EducationRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
