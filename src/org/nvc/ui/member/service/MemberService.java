package org.nvc.ui.member.service;

import java.util.List;

import org.nvc.ui.member.model.Member;
import org.nvc.ui.member.model.MemberRole;
import org.nvc.ui.member.model.MemberSearch;

public interface MemberService 
{
	public List<Member> getMembers();
	
	public List<Member> searchMembers(MemberSearch memberSearch);
	
	public Member getMember(int memberId);
	
	public void addMember(Member member);
	
	public void editMember(Member member);
	
	public List<Member> getMembersByGroup(long groupId);

	public List<Member> getMembersByFamily(long familyId);

	public MemberRole[] getMemberRole();
	
	public List<Member> getMembersByRole(long roleId);
	
}
