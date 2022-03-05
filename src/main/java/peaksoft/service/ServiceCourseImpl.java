package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.CourseDao;
import peaksoft.model.Course;

import java.util.List;
@Service
public class ServiceCourseImpl implements ServiceCourse{
    private CourseDao courseDao;

    @Autowired
    public ServiceCourseImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void save(Course course) {
        courseDao.save(course);
    }

    @Override
    public List<Course> getCourses(Long id) {
        return courseDao.getCourses(id);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseDao.getCourseById(id);
    }

    @Override
    public void deleteCourse(Long id) {
        courseDao.deleteCourse(id);
    }

    @Override
    public void updateCourse(Long id, Course updatedCourse) {
        courseDao.updateCourse(id, updatedCourse);
    }

    @Override
    public Course getCourseByName(String name) {
        return courseDao.getCourseByName(name);
    }
}
