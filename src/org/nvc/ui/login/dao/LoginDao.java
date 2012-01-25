package org.nvc.ui.login.dao;

import org.nvc.ui.common.model.Admin;
import org.nvc.ui.login.model.Login;
import org.springframework.security.core.userdetails.*;


public interface LoginDao 
{
	public int authenticateAdmin(Admin admin);
	public UserDetails  loadUserByUsername(String username);
	public Login loadUserById(long userId);
	public void updateLogin(Login login);
	public long createLogin(Login login);
	
}
