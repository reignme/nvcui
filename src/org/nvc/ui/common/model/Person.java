package org.nvc.ui.common.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class Person 
{
	@NotEmpty(message = "Last Name is Required")
	private String lastName;
	
	@NotEmpty(message = "First Name is Required")
	private String firstName;
	
	@NotEmpty(message = "First Name is Required")
	private String koreanName;
	
	private Address address;
	
	@NotEmpty(message = "homePhone is Required")
	private String homePhone;
	private String homeArea;
	private String homeNum1;
	private String homeNum2;
	
	
	//@NotEmpty(message = "birthDate is Required")
	//@Pattern(regexp = "[0-9]{1,2}/[0-3]{2}/[0-9]{2}")
	private String birthDate;
	
	private String birthYear;
	private String birthMonth;
	private String birthDay;
	
	
	private int age;
	private String cellPhone;
	private String cellArea;
	private String cellNum1;
	private String cellNum2;
	
	private String workPhone;
	private String workArea;
	private String workNum1;
	private String workNum2;
	private String emailAddress;
	private int gender;
	
	public Person()
	{
		address = new Address();
	}
	
	public int getAge()
	{
		return age;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	public Address getAddress()
	{
		return address;
	}
	
	public void setCellPhone(String number)
	{
		cellPhone = number;
		
		if(cellPhone != null)
		{
			//split the numbers.
			String[] numbers = cellPhone.split("-");
			
			this.cellArea = numbers[0];
			this.cellNum1 = numbers[1];
			this.cellNum2 = numbers[2];
		}
		
	}
	
	public void setCellArea(String area)
	{
		this.cellArea = area;
	}
	
	public void setCellNum1(String num)
	{
		this.cellNum1 = num;
	}
	
	public void setCellNum2(String num)
	{
		this.cellNum2 = num;
	}
	
	public String getCellPhone()
	{
		return cellArea + "-" + cellNum1 + "-" + cellNum2;
	}
	
	public void setHomePhone(String number)
	{
		
		homePhone = number;
		
		if(homePhone != null)
		{
			//split the numbers.
			String[] numbers = homePhone.split("-");
			
			this.homeArea = numbers[0];
			this.homeNum1 = numbers[1];
			this.homeNum2 = numbers[2];
		}
	}
	
	public void setHomeArea(String area)
	{
		this.homeArea = area;
	}
	
	public void setHomeNum1(String num)
	{
		this.homeNum1 = num;
	}
	
	public void setHomeNum2(String num)
	{
		this.homeNum2 = num;
	}
	
	public String getHomePhone()
	{
		return homeArea + "-" + homeNum1 + "-" + homeNum2;
	}
	
	public void setWorkPhone(String number)
	{
		workPhone = number;
		
		if(workPhone != null)
		{
			//split the numbers.
			String[] numbers = workPhone.split("-");
			
			this.workArea = numbers[0];
			this.workNum1 = numbers[1];
			this.workNum2 = numbers[2];
		}
	}
	
	public void setWorkArea(String area)
	{
		this.workArea = area;
	}
	
	public void setWorkNum1(String num)
	{
		this.workNum1 = num;
	}
	
	public void setWorkNum2(String num)
	{
		this.workNum2 = num;
	}
	
	public String getWorkPhone()
	{
		return workArea + "-" + workNum1 + "-" + workNum2;
	}
	
	public void setBirthDate(String date)
	{
		birthDate = date;
		
		String[] numbers = birthDate.split("-");
		
		this.birthYear = numbers[0];
		this.birthMonth = numbers[1];
		this.birthDay = numbers[2];
		
	}
	
	public String getBirthDate()
	{
		return birthYear + "-" + birthMonth + "-" + birthDay;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public String getGenderStr()
	{
		if(this.gender == 0)
			return "여";
		else
			return "남";
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getKoreanName() {
		return koreanName;
	}

	public void setKoreanName(String koreanName) {
		this.koreanName = koreanName;
	}

	public String getHomeArea() {
		return homeArea;
	}

	public String getHomeNum1() {
		return homeNum1;
	}

	public String getHomeNum2() {
		return homeNum2;
	}

	public String getCellArea() {
		return cellArea;
	}

	public String getCellNum1() {
		return cellNum1;
	}

	public String getCellNum2() {
		return cellNum2;
	}

	public String getWorkArea() {
		return workArea;
	}

	public String getWorkNum1() {
		return workNum1;
	}

	public String getWorkNum2() {
		return workNum2;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
}
