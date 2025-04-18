package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Student;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;

public class StudentDAO {
    private final MongoCollection<Document> studentCollection;

    // Constructor to initialize the student collection
    public StudentDAO(MongoDatabase database) {
        this.studentCollection = database.getCollection("students");
    }

    // Method to insert a student into MongoDB
    public void insertStudent(Student student) {
        Document doc = new Document("id", student.getId())
                .append("name", student.getName())
                .append("email", student.getEmail())
                .append("age", student.getAge())
                .append("department", student.getDepartment());
        studentCollection.insertOne(doc);
        System.out.println("✅ Student inserted into MongoDB successfully.");
    }

    // Method to fetch a student by ID
    public Student getStudentById(String id) {
        Bson query = Filters.eq("id", id);
        Document studentDoc = studentCollection.find(query).first();
        
        if (studentDoc != null) {
            return new Student(
                studentDoc.getString("id"),
                studentDoc.getString("name"),
                studentDoc.getString("email"),
                studentDoc.getInteger("age"),
                studentDoc.getString("department")
            );
        } else {
            System.out.println("❌ No student found with the given ID.");
            return null;
        }
    }

    // Method to update student details
    public void updateStudent(String id, Student student) {
        Bson query = Filters.eq("id", id);
        Document updatedDoc = new Document("name", student.getName())
                .append("email", student.getEmail())
                .append("age", student.getAge())
                .append("department", student.getDepartment());

        studentCollection.updateOne(query, new Document("$set", updatedDoc));
        System.out.println("✅ Student details updated successfully.");
    }

    // Method to delete a student by ID
    public void deleteStudent(String id) {
        Bson query = Filters.eq("id", id);
        studentCollection.deleteOne(query);
        System.out.println("✅ Student deleted successfully.");
    }
}
