package org.nvc.ui.member.dao;

import java.util.List;

import org.nvc.ui.member.model.Member;
import org.nvc.ui.member.model.MemberSearch;

public interface MemberDao {
	
	public List<Member> searchMembers(MemberSearch memberSearch);
	public Member getMember(int memberId);
	public Member getMember(String name);
	public void addMember(Member member);
	public void editMember(Member member);
	public List<Member> getMembersByGroup(long groupId);
	public List<Member> getMembersByFamily(long familyId);
	public List<Member> getMembersByRole(long roleId);
	
}
