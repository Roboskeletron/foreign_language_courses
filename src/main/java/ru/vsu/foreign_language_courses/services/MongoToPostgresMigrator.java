package ru.vsu.foreign_language_courses.services;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.vsu.foreign_language_courses.domain.entities.Course;
import ru.vsu.foreign_language_courses.domain.entities.Group;
import ru.vsu.foreign_language_courses.domain.entities.Student;
import ru.vsu.foreign_language_courses.mappers.*;
import ru.vsu.foreign_language_courses.repositories.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MongoToPostgresMigrator implements CommandLineRunner {
    private final StudentRepository studentRepository;
    private final StudentMongoRepository studentMongoRepository;
    private final CourseRepository courseRepository;
    private final CourseMongoRepository courseMongoRepository;
    private final GroupRepository groupRepository;
    private final GroupMongoRepository groupMongoRepository;
    private final EducationRepository educationRepository;
    private final EducationMongoRepository educationMongoRepository;

    private final EducationMapper educationMapper;
    private final GroupMapper groupMapper;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;

    @Override
    @Transactional
    public void run(String... args) {
        var courses = migrateCourses();
        var students = migrateStudents();
        var groups = migrateGroups(courses);
        migrateEducations(students, groups);
    }

    private Map<UUID, Student> migrateStudents() {
        var documents = studentMongoRepository.findAll();
        var students = documents.stream()
                .map(studentMapper::toEntity)
                .toList();

        var studentMap = students.stream().collect(Collectors.toMap(
                s -> s.getId(),
                s -> {
                    s.setId(null);
                    return s;
                })
        );

        studentRepository.saveAll(studentMap.values());

        return studentMap;
    }

    private Map<UUID, Course> migrateCourses() {
        var documents = courseMongoRepository.findAll();
        var courses = documents.stream()
                .map(courseMapper::toEntity)
                .toList();

        var coursesMap = courses.stream().collect(Collectors.toMap(
                c -> c.getId(),
                s -> {
                    s.setId(null);
                    return s;
                })
        );

        courseRepository.saveAll(courses);

        return coursesMap;
    }

    private Map<UUID,Group> migrateGroups(Map<UUID, Course> courses) {
        var documents = groupMongoRepository.findAll();
        var groups = documents.stream()
                .map(d -> {
                    var group = groupMapper.toEntity(d);
                    group.setCourse(courses.get(d.getCourseId()));

                    return group;
                })
                .toList();

        var groupsMap = groups.stream().collect(Collectors.toMap(
                c -> c.getId(),
                s -> {
                    s.setId(null);
                    return s;
                })
        );

        groupRepository.saveAll(groups);

        return groupsMap;
    }

    private void migrateEducations(Map<UUID, Student> students, Map<UUID,Group> groups) {
        var documents = educationMongoRepository.findAll();

        var educations = documents.stream()
                .map(d -> {
                    var education = educationMapper.toEntity(d);

                    education.setId(null);

                    education.setStudent(students.get(d.getStudentId()));
                    education.setGroup(groups.get(d.getGroupId()));

                    return education;
                })
                .toList();

        educationRepository.saveAll(educations);
    }
}

