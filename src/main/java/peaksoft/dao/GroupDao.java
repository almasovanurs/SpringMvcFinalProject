package peaksoft.dao;

import peaksoft.model.Group;

import java.util.List;

public interface GroupDao {
    void saveGroup(Group group);

    List<Group> getGroups(Long id);

    Group getGroupById(Long id);

    void deleteGroup(Long id);

    void updateGroup(Long id, Group updatedGroup);

    List<Group>getGroupByCompanyId(Long id);
}
