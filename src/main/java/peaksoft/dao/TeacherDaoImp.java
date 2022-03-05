package peaksoft.dao;

import org.springframework.stereotype.Repository;
import peaksoft.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class TeacherDaoImp implements TeacherDao{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void saveTeacher(Teacher teacher) {
        entityManager.merge(teacher);
    }

    @Override
    public List<Teacher> getTeachers(Long id) {
        return entityManager.createQuery("select t from Teacher t where t.company.id=:id", Teacher.class).setParameter(("id"), id).getResultList();

    }

    @Override
    public Teacher getTeacherById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    @Override
    public void deleteTeacher(Long id) {
        entityManager.remove(getTeacherById(id));
    }

    @Override
    public void updateTeacher(Long id, Teacher updatedTeacher) {
        Teacher teacher = getTeacherById(id);
        teacher.setFirstName(updatedTeacher.getFirstName());
        teacher.setLastName(updatedTeacher.getLastName());
        teacher.setEmail(updatedTeacher.getEmail());
        entityManager.merge(teacher);
    }
}
