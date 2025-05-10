package ru.vsu.foreign_language_courses.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.foreign_language_courses.domain.Course;
import ru.vsu.foreign_language_courses.dto.CourseRequest;
import ru.vsu.foreign_language_courses.dto.CourseResponse;
import ru.vsu.foreign_language_courses.mappers.CourseMapper;
import ru.vsu.foreign_language_courses.repositories.CourseRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository repo;
    private final CourseMapper mapper;

    public List<CourseResponse> findAll() {
        return repo.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public CourseResponse findById(String id) {
        Course ent = repo.findById(UUID.fromString(id)).orElseThrow();
        return mapper.toResponse(ent);
    }

    public CourseResponse create(CourseRequest req) {
        Course ent = mapper.toEntity(req);
        return mapper.toResponse(repo.save(ent));
    }

    public CourseResponse update(String id, CourseRequest req) {
        Course ent = mapper.toEntity(req);
        ent.setId(UUID.fromString(id));
        return mapper.toResponse(repo.save(ent));
    }

    public void delete(String id) {
        repo.deleteById(UUID.fromString(id));
    }
}
