package org.nvc.ui.member.service;

import java.util.List;

import org.codehaus.jackson.JsonGenerator;

import org.nvc.ui.member.dao.MemberDao;
import org.nvc.ui.member.model.Member;
import org.nvc.ui.member.model.MemberRole;
import org.nvc.ui.member.model.MemberSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("MemberService")
public class MemberServiceImpl implements MemberService 
{

	@Autowired private MemberDao memberDao = null;
	
	public List<Member> getMembers()
	{
		return null;
	}
	
	public List<Member> searchMembers(MemberSearch memberSearch)
	{
		return memberDao.searchMembers(memberSearch);	
	}
	
	public Member getMember(int memberId)
	{
		return memberDao.getMember(memberId);
	}
	
	public void addMember(Member member)
	{
		memberDao.addMember(member);
	}
	
	public void editMember(Member member)
	{
		memberDao.editMember(member);
	}
	
	public List<Member> getMembersByGroup(long groupId)
	{
		return memberDao.getMembersByGroup(groupId);
	}
	
	public List<Member> getMembersByFamily(long familyId)
	{
		return memberDao.getMembersByFamily(familyId);
	}

	public MemberRole[] getMemberRole() 
	{
		//test data.
		MemberRole[] roles = new MemberRole[4];
		
		MemberRole role1 = new MemberRole();
		role1.setName("목사님");
		role1.setRoleId(1);
		roles[0] = role1;
		
		MemberRole role2 = new MemberRole();
		role2.setName("마을장님");
		role2.setRoleId(2);
		roles[1] = role2;
		
		MemberRole role3 = new MemberRole();
		role3.setName("목자님");
		role3.setRoleId(3);
		roles[2] = role3;
		
		MemberRole role4 = new MemberRole();
		role4.setName("평신도님");
		role4.setRoleId(4);
		roles[3] = role4;
		
		return roles;
	}
	
	public List<Member> getMembersByRole(long roleId)
	{
		return memberDao.getMembersByRole(roleId);
	}
}
