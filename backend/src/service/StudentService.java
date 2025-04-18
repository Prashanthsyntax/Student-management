package service;

import dao.StudentDAO;
import model.Student;

public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void addStudent(Student student) {
        studentDAO.insertStudent(student);
    }

    public Student getStudentById(String id) {
        return studentDAO.getStudentById(id);
    }

    public void updateStudent(String id, Student student) {
        studentDAO.updateStudent(id, student);
    }

    public void deleteStudent(String id) {
        studentDAO.deleteStudent(id);
    }
}
