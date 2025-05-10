package ru.vsu.foreign_language_courses.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.foreign_language_courses.domain.Student;
import ru.vsu.foreign_language_courses.dto.StudentRequest;
import ru.vsu.foreign_language_courses.dto.StudentResponse;
import ru.vsu.foreign_language_courses.mappers.StudentMapper;
import ru.vsu.foreign_language_courses.repositories.StudentRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repo;
    private final StudentMapper mapper;

    public List<StudentResponse> findAll() {
        return repo.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public StudentResponse findById(String id) {
        Student entity = repo.findById(UUID.fromString(id)).orElseThrow();
        return mapper.toResponse(entity);
    }

    public StudentResponse create(StudentRequest req) {
        Student entity = mapper.toEntity(req);
        return mapper.toResponse(repo.save(entity));
    }

    public StudentResponse update(String id, StudentRequest req) {
        Student entity = mapper.toEntity(req);
        entity.setId(UUID.fromString(id));
        return mapper.toResponse(repo.save(entity));
    }

    public void delete(String id) {
        repo.deleteById(UUID.fromString(id));
    }
}
