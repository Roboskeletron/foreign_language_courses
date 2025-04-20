package ru.vsu.foreign_language_courses.dto;

import java.util.Date;

public record CourseRequest(
        String name,
        String description,
        Date createdAt
) {

}
