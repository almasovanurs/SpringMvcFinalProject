package peaksoft.dao;

import org.springframework.stereotype.Repository;
import peaksoft.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class StudentDaoImpl implements StudentDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void saveStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    public List<Student> getStudents(Long id) {
        return entityManager.createQuery("select s from Student s where s.groups.id=:id",Student.class).setParameter("id",id).getResultList();
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public void deleteStudent(Long id) {
        entityManager.remove(getStudentById(id));
    }

    @Override
    public void updateStudent(Long id, Student updatedStudent) {
        Student student = getStudentById(id);
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());
        student.setStudyFormat(updatedStudent.getStudyFormat());
        entityManager.merge(student);
    }
}
