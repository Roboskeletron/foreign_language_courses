package ru.vsu.foreign_language_courses.services;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.vsu.foreign_language_courses.domain.Course;
import ru.vsu.foreign_language_courses.dto.CourseRequest;
import ru.vsu.foreign_language_courses.dto.CourseResponse;
import ru.vsu.foreign_language_courses.repositories.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository repo;
    private final EntityMapper mapper;

    public List<CourseResponse> findAll() {
        return repo.findAll().stream()
                .map(mapper::toCourseResponse)
                .collect(Collectors.toList());
    }

    public CourseResponse findById(String id) {
        Course ent = repo.findById(new ObjectId(id)).orElseThrow();
        return mapper.toCourseResponse(ent);
    }

    public CourseResponse create(CourseRequest req) {
        Course ent = mapper.toCourseEntity(req);
        return mapper.toCourseResponse(repo.save(ent));
    }

    public CourseResponse update(String id, CourseRequest req) {
        Course ent = mapper.toCourseEntity(req);
        ent.setId(new ObjectId(id));
        return mapper.toCourseResponse(repo.save(ent));
    }

    public void delete(String id) {
        repo.deleteById(new ObjectId(id));
    }
}
