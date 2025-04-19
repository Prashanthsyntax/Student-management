package servlet;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dao.StudentDAO;
import model.Student;
import service.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private MongoClient mongoClient;
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        // MongoDB connection setup
        mongoClient = MongoClients.create("mongodb+srv://prashanthpendem2323:pendem2323@cluster0.jaswr.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");
        MongoDatabase database = mongoClient.getDatabase("StudentDB");
        StudentDAO studentDAO = new StudentDAO(database);
        studentService = new StudentService(studentDAO);
    }

    @Override
    public void destroy() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        String department = request.getParameter("department");
    
        // Create student object from form data
        Student student = new Student(id, name, email, age, department);
        
        // Add student to MongoDB
        studentService.addStudent(student);
    
        // Send confirmation message
        response.getWriter().write("Student Added Successfully");
    }
}
