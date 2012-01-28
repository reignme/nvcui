package org.nvc.ui.member.model;

import org.nvc.ui.common.model.Person;

public class Member extends Person
{
	public static final int PRIMARY = 1;
	public static final int SPAUSE = 2;
	public static final int CHILD = 3;
	
	//search fields.
	//@NotEmpty(message = "Member ID is Required")
	private long memberId;
	
	//@NotEmpty(message = "Family ID is Required")
	private long familyId;
	
	private long groupId;
	
	private int baptised;
	private int religiousConviction;
	private int status;
	private int roleId;
	private int familyRole;
	
	public Member()
	{
		super();
		memberId = 0;
		familyId = 0;
		groupId = 0;
	}
	
	public void setBaptised(int flag)
	{
		this.baptised = flag;
	}
	
	public int getBaptised()
	{
		return this.baptised;
	}

	public int getStatus()
	{
		return status;
	}
	
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}
	
	public int getRoleId()
	{
		return roleId;
	}
	
	public void setReligiousConviction(int value)
	{
		religiousConviction = value;
	}
	
	public int getReligiousConviction()
	{
		return religiousConviction;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public long getFamilyId() {
		return familyId;
	}

	public void setFamilyId(long familyId) {
		this.familyId = familyId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	public String getRoleStr()
	{
		if(this.roleId == 1)
			return "목사님";
		else if(this.roleId == 2)
			return "마을장님";
		else if(this.roleId == 3)
			return "목자님";
		else
			return "평신도님";
	}
	
	public String getGenderStr()
	{
		if(this.getGender() == 0)
			return "여성";
		else
			return "남성";
	}
	
	public String getStatusStr()
	{
		if(this.getStatus() == 1)
		{
			return "등록";
		}
		else if(this.getStatus() == 2)
		{
			return "비등록";			
		}
		else
		{
			return "방문";
		}
	}

	public int getFamilyRole() {
		return familyRole;
	}

	public void setFamilyRole(int familyRole) {
		this.familyRole = familyRole;
	}
	
	
}
