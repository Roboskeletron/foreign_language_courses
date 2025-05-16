package ru.vsu.foreign_language_courses.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.foreign_language_courses.domain.entities.Group;
import ru.vsu.foreign_language_courses.dto.GroupRequest;
import ru.vsu.foreign_language_courses.dto.GroupResponse;
import ru.vsu.foreign_language_courses.mappers.GroupMapper;
import ru.vsu.foreign_language_courses.repositories.CourseRepository;
import ru.vsu.foreign_language_courses.repositories.GroupRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository repo;
    private final CourseRepository courseRepo;
    private final GroupMapper mapper;

    public List<GroupResponse> findAll() {
        return repo.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public GroupResponse findById(String id) {
        Group ent = repo.findById(UUID.fromString(id)).orElseThrow();
        return mapper.toResponse(ent);
    }

    public GroupResponse create(GroupRequest req) {
        Group ent = mapper.toEntity(req);

        var course = courseRepo.findById(UUID.fromString(req.courseId())).orElseThrow(EntityNotFoundException::new);
        ent.setCourse(course);

        return mapper.toResponse(repo.save(ent));
    }

    public GroupResponse update(String id, GroupRequest req) {
        Group ent = mapper.toEntity(req);
        ent.setId(UUID.fromString(id));

        var course = courseRepo.findById(UUID.fromString(req.courseId())).orElseThrow(EntityNotFoundException::new);
        ent.setCourse(course);

        return mapper.toResponse(repo.save(ent));
    }

    public void delete(String id) {
        repo.deleteById(UUID.fromString(id));
    }
}
