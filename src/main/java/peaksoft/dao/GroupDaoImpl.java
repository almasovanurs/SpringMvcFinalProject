package peaksoft.dao;

import org.springframework.stereotype.Repository;
import peaksoft.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class GroupDaoImpl implements GroupDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveGroup(Group group) {
        entityManager.merge(group);
    }

    @Override
    public List<Group> getGroups(Long id) {
        return entityManager.createQuery("Select gr from Group gr", Group.class).getResultList();
    }

    @Override
    public Group getGroupById(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public void deleteGroup(Long id) {
        entityManager.remove(getGroupById(id));
    }

    @Override
    public void updateGroup(Long id, Group updatedGroup) {
        Group group = getGroupById(id);
        group.setGroupName(updatedGroup.getGroupName());
        group.setDateOfStart(updatedGroup.getDateOfStart());
        group.setDateOfFinish(updatedGroup.getDateOfFinish());
        entityManager.merge(group);
    }

    @Override
    public List<Group> getGroupByCompanyId(Long id) {
        return entityManager.createQuery("Select gr from Group gr where gr.company.id=:id", Group.class).setParameter("id",id).getResultList();
    }

}
