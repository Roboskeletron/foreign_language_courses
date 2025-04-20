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
@Document(collection = "educations")
public class Education {
    @Id
    private ObjectId id;
    private ObjectId studentId;
    private ObjectId groupId;
    private Date enrolledAt;
}
