package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Student;

import java.util.List;
@Service
public interface ServiceStudent {
    void saveStudent(Student student);

    List<Student> getStudents(Long id);

    Student getStudentById(Long id);

    void deleteStudent(Long id);

    void updateStudent(Long id, Student updatedStudent);
}
