package servlet;

import com.mongodb.MongoClient;
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
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("student_db");

        StudentDAO studentDAO = new StudentDAO(database);
        studentService = new StudentService(studentDAO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));

        Student student = new Student(id, name, age);
        studentService.addStudent(student);

        response.getWriter().write("Student Added Successfully");
    }

    // You can later add doGet, doPut, and doDelete if needed
}

