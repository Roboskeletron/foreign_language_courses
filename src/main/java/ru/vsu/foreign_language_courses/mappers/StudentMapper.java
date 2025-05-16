package ru.vsu.foreign_language_courses.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.vsu.foreign_language_courses.domain.documents.StudentDocument;
import ru.vsu.foreign_language_courses.domain.entities.Student;
import ru.vsu.foreign_language_courses.dto.StudentRequest;
import ru.vsu.foreign_language_courses.dto.StudentResponse;

import java.util.Date;
import java.util.UUID;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {UUID.class, Date.class}
)
public interface StudentMapper {
    @Mapping(target = "id", ignore = true)
    Student toEntity(StudentRequest dto);

    @Mapping(target = "id", expression = "java(entity.getId().toString())")
    StudentResponse toResponse(Student entity);

//    @Mapping(target = "id", ignore = true)
    Student toEntity(StudentDocument document);
}
