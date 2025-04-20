package ru.vsu.foreign_language_courses.dto;

import java.util.List;

public record GroupResponse(
        String id,
        String name,
        String courseId,
        List<String> schedule
) {

}
