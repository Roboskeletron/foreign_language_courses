package ru.vsu.foreign_language_courses.services;

@Component
public class MongoToPostgresMigrator implements CommandLineRunner {
    private final StudentMongoRepo mongoRepo;
    private final StudentJpaRepo jpaRepo;

    public MongoToPostgresMigrator(StudentMongoRepo mongoRepo, StudentJpaRepo jpaRepo) {
        this.mongoRepo = mongoRepo;
        this.jpaRepo   = jpaRepo;
    }

    @Override
    public void run(String... args) {
        List<StudentDoc> students = mongoRepo.findAll();                  // Читаем из MongoDB:contentReference[oaicite:17]{index=17}
        List<StudentEntity> entities = students.stream()
                .map(doc -> {
                    StudentEntity e = new StudentEntity();
                    e.setFirstName(doc.getFirstName());
                    e.setLastName(doc.getLastName());
                    e.setEmail(doc.getEmail());
                    e.setRegisteredAt(doc.getRegisteredAt());
                    return e;
                })
                .collect(Collectors.toList());

        jpaRepo.saveAll(entities);                                        // Пакетная вставка в Postgres:contentReference[oaicite:18]{index=18}
        System.out.println("Migrated " + entities.size() + " students."); // Логируем результат:contentReference[oaicite:19]{index=19}
    }
}

