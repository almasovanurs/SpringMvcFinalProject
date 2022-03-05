package peaksoft.dao;

import peaksoft.model.Course;

import java.util.List;

public interface CourseDao {
    void save(Course course);

    List<Course> getCourses(Long id);

    Course getCourseById(Long id);

    void deleteCourse(Long id);

    void updateCourse( Long id,Course updatedCourse);

    Course getCourseByName(String name);
}
