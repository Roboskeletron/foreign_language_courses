package ru.vsu.foreign_language_courses.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.foreign_language_courses.dto.CourseRequest;
import ru.vsu.foreign_language_courses.dto.CourseResponse;
import ru.vsu.foreign_language_courses.services.CourseService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService service;

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CourseResponse> create(@RequestBody CourseRequest req) {
        CourseResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/courses/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> update(@PathVariable String id, @RequestBody CourseRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
