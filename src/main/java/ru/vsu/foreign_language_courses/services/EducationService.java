package ru.vsu.foreign_language_courses.services;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.vsu.foreign_language_courses.domain.Education;
import ru.vsu.foreign_language_courses.dto.EducationRequest;
import ru.vsu.foreign_language_courses.dto.EducationResponse;
import ru.vsu.foreign_language_courses.repositories.EducationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository repo;
    private final EntityMapper mapper;

    public List<EducationResponse> findAll() {
        return repo.findAll().stream()
                .map(mapper::toEducationResponse)
                .collect(Collectors.toList());
    }

    public EducationResponse findById(String id) {
        Education ent = repo.findById(new ObjectId(id)).orElseThrow();
        return mapper.toEducationResponse(ent);
    }

    public EducationResponse create(EducationRequest req) {
        Education ent = mapper.toEducationEntity(req);
        return mapper.toEducationResponse(repo.save(ent));
    }

    public EducationResponse update(String id, EducationRequest req) {
        Education ent = mapper.toEducationEntity(req);
        ent.setId(new ObjectId(id));
        return mapper.toEducationResponse(repo.save(ent));
    }

    public void delete(String id) {
        repo.deleteById(new ObjectId(id));
    }
}
