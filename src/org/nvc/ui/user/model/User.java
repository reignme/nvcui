package org.nvc.ui.user.model;

import org.nvc.ui.login.model.Login;

public class User extends Login{

    private long memberId;
    private String authority;
    private int status;
 
    
    public User()
    {
    	super();
    }
    
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
}
