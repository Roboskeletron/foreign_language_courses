package ru.vsu.foreign_language_courses.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.vsu.foreign_language_courses.domain.Education;
import ru.vsu.foreign_language_courses.dto.EducationRequest;
import ru.vsu.foreign_language_courses.dto.EducationResponse;

import java.util.Date;
import java.util.UUID;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {UUID.class, Date.class}
)
public interface EducationMapper {
    @Mapping(target = "id", ignore = true)
    Education toEntity(EducationRequest dto);

    @Mapping(target = "id", expression = "java(entity.getId().toString())")
    @Mapping(target = "studentId", expression = "java(entity.getStudent().getId().toString())")
    @Mapping(target = "groupId", expression = "java(entity.getGroup().getId().toString())")
    EducationResponse toResponse(Education entity);
}
