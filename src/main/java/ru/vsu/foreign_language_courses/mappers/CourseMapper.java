package ru.vsu.foreign_language_courses.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.vsu.foreign_language_courses.domain.documents.CourseDocument;
import ru.vsu.foreign_language_courses.domain.entities.Course;
import ru.vsu.foreign_language_courses.dto.CourseRequest;
import ru.vsu.foreign_language_courses.dto.CourseResponse;

import java.util.Date;
import java.util.UUID;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {UUID.class, Date.class}
)
public interface CourseMapper {
    @Mapping(target = "id", ignore = true)
    Course toEntity(CourseRequest dto);

    @Mapping(target = "id", expression = "java(entity.getId().toString())")
    CourseResponse toResponse(Course entity);

//    @Mapping(target = "id", ignore = true)
    Course toEntity(CourseDocument document);
}
