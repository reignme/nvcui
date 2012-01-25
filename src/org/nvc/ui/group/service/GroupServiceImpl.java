package org.nvc.ui.group.service;

import java.util.List;

import org.nvc.ui.group.dao.GroupDao;
import org.nvc.ui.group.model.Community;
import org.nvc.ui.group.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;

@Repository("GroupService")
public class GroupServiceImpl implements GroupService
{
	@Autowired private GroupDao groupDao = null;
	
	@Secured("ROLE_ADMIN")
	public List<Group> getAll()
	{
		return groupDao.getAll();
	}
	
	@Secured("ROLE_ADMIN")
	public Group get(long id)
	{
		return groupDao.get(id);
	}
	
	@Secured("ROLE_ADMIN")
	public void save(Group group)
	{
		if(group.getGroupId() == 0)
			groupDao.createGroup(group);
		else
			groupDao.updateGroup(group);
	}

	@Secured("ROLE_ADMIN")
	public Community[] getCommunity() 
	{	
		return groupDao.getCommunity();
	}
	
	public void updateGroup(Group group)
	{
		groupDao.updateGroup(group);
	}
	
	public void createGroup(Group group)
	{
		groupDao.createGroup(group);
	}
	
	public List<Group> getByServiceTime(int serviceTime)
	{
		return groupDao.getByServiceTime(serviceTime);
	}
	
	public Group getGroupByMemberName(String memberName)
	{
		return groupDao.getGroupByMemberName(memberName);
	}
	
}
