package ru.vsu.foreign_language_courses.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "students")
public class Student {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String email;
    private Date registeredAt;
}
