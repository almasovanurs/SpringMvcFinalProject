package peaksoft.dao;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseDaoImpl implements CourseDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Course course) {
        entityManager.merge(course);
    }

    @Override
    public List<Course> getCourses(Long id) {
        return entityManager.createQuery("SELECT c FROM Course c where c.company.id=:id", Course.class).setParameter("id", id).getResultList();
    }

    @Override
    public Course getCourseById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public void deleteCourse(Long id) {
        entityManager.remove(getCourseById(id));
    }

    @Override
    public void updateCourse(Long id, Course updatedCourse) {
        Course course = getCourseById(id);
        course.setCourseName(updatedCourse.getCourseName());
        course.setDuration(updatedCourse.getDuration());
        entityManager.merge(course);
    }

    @Override
    public Course getCourseByName(String name) {
        return entityManager.createQuery("select c from Course c where c.courseName like:name", Course.class).setParameter("name", name).getSingleResult();
    }
}