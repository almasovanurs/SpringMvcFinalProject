package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Teacher;

import java.util.List;
@Service
public interface ServiceTeacher{
    void saveTeacher(Teacher teacher);

    List<Teacher> getTeachers(Long id);

    Teacher getTeacherById(Long id);

    void deleteTeacher(Long id);

    void updateTeacher(Long id, Teacher updatedTeacher);
}
