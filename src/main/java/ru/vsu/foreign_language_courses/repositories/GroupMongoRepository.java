package ru.vsu.foreign_language_courses.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.foreign_language_courses.domain.documents.GroupDocument;

@Repository
public interface GroupMongoRepository extends MongoRepository<GroupDocument, ObjectId> {
}
