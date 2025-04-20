package ru.vsu.foreign_language_courses.dto;

import java.util.Date;

public record EducationRequest(
        String studentId,
        String groupId,
        Date enrolledAt
) {

}
