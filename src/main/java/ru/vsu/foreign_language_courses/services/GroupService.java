package ru.vsu.foreign_language_courses.services;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.vsu.foreign_language_courses.domain.Group;
import ru.vsu.foreign_language_courses.dto.GroupRequest;
import ru.vsu.foreign_language_courses.dto.GroupResponse;
import ru.vsu.foreign_language_courses.repositories.GroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository repo;
    private final EntityMapper mapper;

    public List<GroupResponse> findAll() {
        return repo.findAll().stream()
                .map(mapper::toGroupResponse)
                .collect(Collectors.toList());
    }

    public GroupResponse findById(String id) {
        Group ent = repo.findById(new ObjectId(id)).orElseThrow();
        return mapper.toGroupResponse(ent);
    }

    public GroupResponse create(GroupRequest req) {
        Group ent = mapper.toGroupEntity(req);
        return mapper.toGroupResponse(repo.save(ent));
    }

    public GroupResponse update(String id, GroupRequest req) {
        Group ent = mapper.toGroupEntity(req);
        ent.setId(new ObjectId(id));
        return mapper.toGroupResponse(repo.save(ent));
    }

    public void delete(String id) {
        repo.deleteById(new ObjectId(id));
    }
}
