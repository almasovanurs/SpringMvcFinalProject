package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.TeacherDao;
import peaksoft.model.Teacher;

import java.util.List;
@Service
public class ServiceTeacherImpl implements ServiceTeacher{
    private TeacherDao teacherDao;

    @Autowired
    public ServiceTeacherImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherDao.saveTeacher(teacher);
    }

    @Override
    public List<Teacher> getTeachers(Long id) {
        return teacherDao.getTeachers(id);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherDao.getTeacherById(id);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherDao.deleteTeacher(id);
    }

    @Override
    public void updateTeacher(Long id, Teacher updatedTeacher) {
        teacherDao.updateTeacher(id, updatedTeacher);
    }

}
