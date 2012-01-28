package org.nvc.ui.group.dao;

import java.util.List;

import org.nvc.ui.group.model.Community;
import org.nvc.ui.group.model.Group;
import org.nvc.ui.member.model.MemberAttendance;

public interface GroupDao {
	
	public List<Group> getAll();
	public Group get(long groupId);
	public void save(Group group);
	public Community[] getCommunity();
	
	public void createGroup(Group group);
	public void updateGroup(Group group);
	
	public List<Group> getByServiceTime(int serviceTime);
	
	public Group getGroupByMemberName(String memberName);
	
	public List<MemberAttendance> getMemberAttendanceByGroupId(long groupId, String date);
	
	public void makrAttendance(long memberId, String date);
	public void unmarkAttendance(long memberId, String date);
	
}
