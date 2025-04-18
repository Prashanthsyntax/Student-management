package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Student;
import org.bson.Document;

public class StudentDAO {
    private final MongoCollection<Document> studentCollection;

    public StudentDAO(MongoDatabase database) {
        this.studentCollection = database.getCollection("students");
    }

    public void insertStudent(Student student) {
        Document doc = new Document("id", student.getId())
                .append("name", student.getName())
                .append("email", student.getEmail())
                .append("age", student.getAge())
                .append("department", student.getDepartment());

        studentCollection.insertOne(doc);
        System.out.println("✅ Student inserted into MongoDB successfully.");
    }
}
