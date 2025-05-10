CREATE TABLE courses
(
    id          UUID NOT NULL,
    name        VARCHAR(255),
    description TEXT,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_course PRIMARY KEY (id)
);

CREATE TABLE educations
(
    id          UUID NOT NULL,
    student_id  UUID,
    group_id   UUID,
    enrolled_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_education PRIMARY KEY (id)
);

CREATE TABLE groups
(
    id        UUID NOT NULL,
    name      VARCHAR(255),
    course_id UUID,
    schedule  TEXT[],
    CONSTRAINT pk_group PRIMARY KEY (id)
);

CREATE TABLE students
(
    id            UUID NOT NULL,
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    email         VARCHAR(255),
    registered_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_student PRIMARY KEY (id)
);

ALTER TABLE educations
    ADD CONSTRAINT FK_EDUCATION_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (id);

ALTER TABLE educations
    ADD CONSTRAINT FK_EDUCATION_ON_STUDENT FOREIGN KEY (student_id) REFERENCES students (id);

ALTER TABLE groups
    ADD CONSTRAINT FK_GROUP_ON_COURSE FOREIGN KEY (course_id) REFERENCES courses (id);