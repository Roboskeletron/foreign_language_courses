package ru.vsu.foreign_language_courses.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.vsu.foreign_language_courses.domain.documents.GroupDocument;
import ru.vsu.foreign_language_courses.domain.entities.Group;
import ru.vsu.foreign_language_courses.dto.GroupRequest;
import ru.vsu.foreign_language_courses.dto.GroupResponse;

import java.util.Date;
import java.util.UUID;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {UUID.class, Date.class}
)
public interface GroupMapper {
    @Mapping(target = "id", ignore = true)
    Group toEntity(GroupRequest dto);

    @Mapping(target = "id", expression = "java(entity.getId().toString())")
    @Mapping(target = "courseId", expression = "java(entity.getCourse().getId().toString())")
    GroupResponse toResponse(Group entity);

//    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    Group toEntity(GroupDocument document);
}
