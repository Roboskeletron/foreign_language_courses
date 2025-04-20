// MongoDB Setup Script for "Language Courses" Application

// Switch to the target database (it will be created if it doesn’t exist)
use language_courses;

// ==== 1. Create Collections ==== //
// Explicit creation is optional; inserts will create collections automatically.
db.createCollection("students");
db.createCollection("courses");
db.createCollection("groups");
db.createCollection("educations");

// ==== 2. Create Indexes ==== //
// Ensure unique emails for students
db.students.createIndex({ email: 1 }, { unique: true });
// Full-text search on course name and description
db.courses.createIndex({ name: "text", description: "text" });
// Fast lookup of groups by course
db.groups.createIndex({ courseId: 1 });
// Prevent duplicate enrollments and speed joins
db.educations.createIndex({ studentId: 1, groupId: 1 }, { unique: true });

// ==== 3. Generate IDs for Initial Documents ==== //
var courseEnglishId = ObjectId();
var courseSpanishId = ObjectId();

var studentAliceId = ObjectId();
var studentBobId   = ObjectId();

var groupEngMonId  = ObjectId();
var groupSpaTueId  = ObjectId();

// ==== 4. Insert Initial Data ==== //

// Courses
db.courses.insertMany([
    {
        _id: courseEnglishId,
        name: "English for Beginners",
        description: "Introduction to English grammar, vocabulary, and basic conversation skills.",
        createdAt: new Date()
    },
    {
        _id: courseSpanishId,
        name: "Spanish Intermediate",
        description: "Deepen your Spanish skills: complex grammar, reading comprehension, and speaking practice.",
        createdAt: new Date()
    }
]);

// Students
db.students.insertMany([
    {
        _id: studentAliceId,
        firstName: "Alice",
        lastName: "Ivanova",
        email: "alice.ivanova@example.com",
        registeredAt: new Date()
    },
    {
        _id: studentBobId,
        firstName: "Bob",
        lastName: "Petrov",
        email: "bob.petrov@example.com",
        registeredAt: new Date()
    }
]);

// Groups
// Each group links to a course and has a schedule

db.groups.insertMany([
    {
        _id: groupEngMonId,
        name: "ENG-Beg Mon/Wed",
        courseId: courseEnglishId,
        schedule: ["Monday 18:00", "Wednesday 18:00"]
    },
    {
        _id: groupSpaTueId,
        name: "SPA-Int Tue/Thu",
        courseId: courseSpanishId,
        schedule: ["Tuesday 19:00", "Thursday 19:00"]
    }
]);

// Educations (Enrollments)
// Junction between students and groups

db.educations.insertMany([
    {
        studentId: studentAliceId,
        groupId: groupEngMonId,
        enrolledAt: new Date()
    },
    {
        studentId: studentBobId,
        groupId: groupSpaTueId,
        enrolledAt: new Date()
    }
]);

print("MongoDB setup completed: collections created, indexes set, and initial data inserted.");
