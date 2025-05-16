package ru.vsu.foreign_language_courses.domain.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "students")
public class StudentDocument {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Date registeredAt;
}
