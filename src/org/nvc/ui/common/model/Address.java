package org.nvc.ui.common.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Address 
{
	@NotEmpty(message = "street is Required")
	private String street;
	
	@NotEmpty(message = "city is Required")
	private String city;
	
	@NotEmpty(message = "state is Required")
	private String state;
	
	@NotEmpty(message = "country is Required")
	private String country;
	
	@NotEmpty(message = "zipCode is Required")
	private String zipCode;
	
	public void setStreet(String street)
	{
		this.street = street;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public String getCountry()
	{
		return country;
	}

	public void setZipCode(String zip)
	{
		this.zipCode = zip;
	}
	
	public String getZipCode()
	{
		return zipCode;
	}
}
