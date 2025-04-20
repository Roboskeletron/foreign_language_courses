package ru.vsu.foreign_language_courses.dto;

import java.util.Date;

public record CourseResponse(
        String id,
        String name,
        String description,
        Date createdAt
) {

}
