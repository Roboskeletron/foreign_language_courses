package ru.vsu.foreign_language_courses.dto;

import java.util.Date;

public record StudentRequest(
        String firstName,
        String lastName,
        String email,
        Date registeredAt
) {

}
