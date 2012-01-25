package org.nvc.ui.member.model;

public class MemberRole 
{
	public static final int PASTOR = 1;
	public static final int COMMUNITY_HEAD = 2;
	public static final int SHEPHERD = 3;
	//public static final int CHRISTIAN = 4;
	private long roleId;
	private String name;
	
	public long getRoleId() 
	{
		return roleId;
	}
	
	public void setRoleId(long roleId) 
	{
		this.roleId = roleId;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
}
