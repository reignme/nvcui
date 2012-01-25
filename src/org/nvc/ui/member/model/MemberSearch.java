package org.nvc.ui.member.model;

public class MemberSearch {

	//search field.
	
	private String name;
	private String phoneNumber;
	private String searchField;
	private String zipcode;
	private String status;
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setPhoneNumber(String number)
	{
		this.phoneNumber = number;
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
