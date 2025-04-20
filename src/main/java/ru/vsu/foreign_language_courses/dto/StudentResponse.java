package ru.vsu.foreign_language_courses.dto;

import java.util.Date;

public record StudentResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Date registeredAt
) {

}
