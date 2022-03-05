package peaksoft.dao;

import peaksoft.model.Student;

import java.util.List;

public interface StudentDao {
    void saveStudent(Student student);

    List<Student> getStudents(Long id);

    Student getStudentById(Long id);

    void deleteStudent(Long id);

    void updateStudent(Long id, Student updatedStudent);
}
