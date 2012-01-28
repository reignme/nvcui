package org.nvc.ui.group.service;

import java.util.List;

import org.nvc.ui.group.model.Community;
import org.nvc.ui.group.model.Group;
import org.nvc.ui.member.model.MemberAttendance;

public interface GroupService 
{
	public List<Group> getAll();
	
	public Group get(long groupId);
	
	public void save(Group group);
	
	public Community[] getCommunity();
	
	public void updateGroup(Group group);
	
	public void createGroup(Group group);
	
	public List<Group> getByServiceTime(int serviceTime);
	
	public Group getGroupByMemberName(String memberName);
	
	public List<MemberAttendance> getMemberAttendanceByGroupId(long groupId, String date);
	
	public void makrAttendance(long memberId, String date);
	
	public void unmarkAttendance(long memberId, String date);
}
