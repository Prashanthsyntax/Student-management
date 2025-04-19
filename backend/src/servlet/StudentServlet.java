package servlet;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dao.StudentDAO;
import model.Student;
import service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://prashanthpendem2323:pendem2323@cluster0.jaswr.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");) {
            MongoDatabase database = mongoClient.getDatabase("student_db");

            StudentDAO studentDAO = new StudentDAO(database);
            studentService = new StudentService(studentDAO);
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
    
        Student student = new Student(id, name, email, age, department);
        studentService.addStudent(student);
    
        response.getWriter().write("Student Added Successfully");
    }
    

    // You can later add doGet, doPut, and doDelete if needed
}

