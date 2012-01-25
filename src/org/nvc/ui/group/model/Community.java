package org.nvc.ui.group.model;

public class Community 
{
	private long communityId;
	private String name;
	private long communityHeadId;
	
	public long getCommunityId() {
		return communityId;
	}
	public void setCommunityId(long communityId) {
		this.communityId = communityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCommunityHeadId() {
		return communityHeadId;
	}
	public void setCommunityHeadId(long communityHeadId) {
		this.communityHeadId = communityHeadId;
	}
}
