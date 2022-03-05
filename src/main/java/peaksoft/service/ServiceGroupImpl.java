package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.GroupDao;
import peaksoft.model.Group;

import java.util.List;
@Service
public class ServiceGroupImpl implements ServiceGroup{
    private GroupDao groupDao;

    @Autowired
    public ServiceGroupImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public void saveGroup(Group group) {
        groupDao.saveGroup(group);
    }

    @Override
    public List<Group> getGroups(Long id) {
        return groupDao.getGroups(id);
    }

    @Override
    public Group getGroupById(Long id) {
        return groupDao.getGroupById(id);
    }

    @Override
    public void deleteGroup(Long id) {
        groupDao.deleteGroup(id);
    }

    @Override
    public void updateGroup(Long id, Group updatedGroup) {
        groupDao.updateGroup(id, updatedGroup);
    }

    @Override
    public List<Group> getGroupByCompanyId(Long id) {
        return groupDao.getGroupByCompanyId(id);
    }
}
