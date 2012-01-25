package org.nvc.ui.login.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Login
{
    @NotEmpty(message = "username is Required")
    private String username;
    
    //@Pattern(regexp="\\s{8,}", message= "Min password length is 8")
    private String password;
    
    public String getUsername()
    {
    	return username;
    }
    
    public void setUsername(String name)
    {
    	username = name;
    }
    
    public String getPassword()
    {
    	return password;
    }
    
    public void setPassword(String password)
    {
    	this.password = password; 
    }
}
