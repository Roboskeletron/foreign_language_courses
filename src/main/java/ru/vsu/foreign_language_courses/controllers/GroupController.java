package ru.vsu.foreign_language_courses.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.foreign_language_courses.dto.GroupRequest;
import ru.vsu.foreign_language_courses.dto.GroupResponse;
import ru.vsu.foreign_language_courses.services.GroupService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService service;

    @GetMapping
    public ResponseEntity<List<GroupResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<GroupResponse> create(@RequestBody GroupRequest req) {
        GroupResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/groups/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupResponse> update(@PathVariable String id, @RequestBody GroupRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
