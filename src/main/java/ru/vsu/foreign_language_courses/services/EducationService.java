package ru.vsu.foreign_language_courses.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.foreign_language_courses.domain.entities.Education;
import ru.vsu.foreign_language_courses.dto.EducationRequest;
import ru.vsu.foreign_language_courses.dto.EducationResponse;
import ru.vsu.foreign_language_courses.mappers.EducationMapper;
import ru.vsu.foreign_language_courses.repositories.EducationRepository;
import ru.vsu.foreign_language_courses.repositories.GroupRepository;
import ru.vsu.foreign_language_courses.repositories.StudentRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository repo;
    private final EducationMapper mapper;
    private final StudentRepository studentRepo;
    private final GroupRepository groupRepo;

    public List<EducationResponse> findAll() {
        return repo.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public EducationResponse findById(String id) {
        Education ent = repo.findById(UUID.fromString(id)).orElseThrow();
        return mapper.toResponse(ent);
    }

    public EducationResponse create(EducationRequest req) {
        Education ent = mapper.toEntity(req);

        var student = studentRepo.findById(UUID.fromString(req.studentId())).orElseThrow(EntityNotFoundException::new);
        var group = groupRepo.findById(UUID.fromString(req.groupId())).orElseThrow(EntityNotFoundException::new);

        ent.setStudent(student);
        ent.setGroup(group);

        return mapper.toResponse(repo.save(ent));
    }

    public EducationResponse update(String id, EducationRequest req) {
        Education ent = mapper.toEntity(req);
        ent.setId(UUID.fromString(id));

        var student = studentRepo.findById(UUID.fromString(req.studentId())).orElseThrow(EntityNotFoundException::new);
        var group = groupRepo.findById(UUID.fromString(req.groupId())).orElseThrow(EntityNotFoundException::new);

        ent.setStudent(student);
        ent.setGroup(group);

        return mapper.toResponse(repo.save(ent));
    }

    public void delete(String id) {
        repo.deleteById(UUID.fromString(id));
    }
}
