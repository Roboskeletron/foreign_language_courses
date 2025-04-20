package ru.vsu.foreign_language_courses.services;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.vsu.foreign_language_courses.domain.*;
import ru.vsu.foreign_language_courses.dto.*;

import java.util.Date;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
imports = {
        Date.class,
        ObjectId.class,
})
public interface EntityMapper {
    // Student
    @Mapping(target = "id", ignore = true)
    Student toStudentEntity(StudentRequest dto);

    @Mapping(target = "id", expression = "java(entity.getId().toHexString())")
    StudentResponse toStudentResponse(Student entity);

    // Course
    @Mapping(target = "id", ignore = true)
    Course toCourseEntity(CourseRequest dto);

    @Mapping(target = "id", expression = "java(entity.getId().toHexString())")
    CourseResponse toCourseResponse(Course entity);

    // Group
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courseId", expression = "java(new ObjectId(dto.courseId()))")
    Group toGroupEntity(GroupRequest dto);

    @Mapping(target = "id", expression = "java(entity.getId().toHexString())")
    @Mapping(target = "courseId", expression = "java(entity.getCourseId().toHexString())")
    GroupResponse toGroupResponse(Group entity);

    // Education
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "studentId", expression = "java(new ObjectId(dto.studentId()))")
    @Mapping(target = "groupId", expression = "java(new ObjectId(dto.groupId()))")
    Education toEducationEntity(EducationRequest dto);

    @Mapping(target = "id", expression = "java(entity.getId().toHexString())")
    @Mapping(target = "studentId", expression = "java(entity.getStudentId().toHexString())")
    @Mapping(target = "groupId", expression = "java(entity.getGroupId().toHexString())")
    EducationResponse toEducationResponse(Education entity);
}
