package ru.vsu.foreign_language_courses.dto;

import org.bson.types.ObjectId;

import java.util.Date;

public record EducationResponse(
        String id,
        String studentId,
        String groupId,
        Date enrolledAt
) {

}
