package org.nvc.ui.group.model;

public class Group 
{
	private long communityId;
	private String communityName;
	private long communityHeadId;
	private String communityHeadName;
	
	private int groupId;
	private String name;
	private int numberOfMember;
	
	private long shepherdId;
	private String shepherdName;
	private String shepherdEmailAddress;
	private String shepherdPhoneNumber;
	
	private long secondShepherdId;
	private String secondShepherdName;
	private String secondShepherdEmailAddress;
	private String secondShepherdPhoneNumber;
	
	private int roomNumber;
	private int numberOfFamily;
	private String ageGroup;
	private int serviceTime;
	
	public Group()
	{
		name = "";
		roomNumber = 0;
	}
	
	public void setGroupId(int groupId)
	{
		this.groupId = groupId; 
	}
	
	public int getGroupId()
	{
		return this.groupId;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setNumberOfMember(int count)
	{
		this.numberOfMember = count;
	}
	
	public int getNumberOfMember()
	{
		return this.numberOfMember;
	}
	
	public String getName()
	{
		return name;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getNumberOfFamily() {
		return numberOfFamily;
	}

	public void setNumberOfFamily(int numberOfFamily) {
		this.numberOfFamily = numberOfFamily;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(long communityId) {
		this.communityId = communityId;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public long getShepherdId() {
		return shepherdId;
	}

	public void setShepherdId(long shepherdId) {
		this.shepherdId = shepherdId;
	}

	public String getShepherdName() {
		return shepherdName;
	}

	public void setShepherdName(String shepherdName) {
		this.shepherdName = shepherdName;
	}

	public String getShepherdEmailAddress() {
		return shepherdEmailAddress;
	}

	public void setShepherdEmailAddress(String shepherdEmailAddress) {
		this.shepherdEmailAddress = shepherdEmailAddress;
	}

	public String getShepherdPhoneNumber() {
		return shepherdPhoneNumber;
	}

	public void setShepherdPhoneNumber(String shepherdPhoneNumber) {
		this.shepherdPhoneNumber = shepherdPhoneNumber;
	}

	public long getSecondShepherdId() {
		return secondShepherdId;
	}

	public void setSecondShepherdId(long secondShepherdId) {
		this.secondShepherdId = secondShepherdId;
	}

	public String getSecondShepherdName() {
		return secondShepherdName;
	}

	public void setSecondShepherdName(String secondShepherdName) {
		this.secondShepherdName = secondShepherdName;
	}

	public String getSecondShepherdEmailAddress() {
		return secondShepherdEmailAddress;
	}

	public void setSecondShepherdEmailAddress(String secondShepherdEmailAddress) {
		this.secondShepherdEmailAddress = secondShepherdEmailAddress;
	}

	public String getSecondShepherdPhoneNumber() {
		return secondShepherdPhoneNumber;
	}

	public void setSecondShepherdPhoneNumber(String secondShepherdPhoneNumber) {
		this.secondShepherdPhoneNumber = secondShepherdPhoneNumber;
	}

	public long getCommunityHeadId() {
		return communityHeadId;
	}

	public void setCommunityHeadId(long communityHeadId) {
		this.communityHeadId = communityHeadId;
	}

	public String getCommunityHeadName() {
		return communityHeadName;
	}

	public void setCommunityHeadName(String communityHeadName) {
		this.communityHeadName = communityHeadName;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	
}

