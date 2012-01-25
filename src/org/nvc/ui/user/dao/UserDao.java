package org.nvc.ui.user.dao;

import org.nvc.ui.login.model.Login;
import org.nvc.ui.user.dao.UserDaoJdbc.UserMapper;
import org.nvc.ui.user.model.User;

public interface UserDao 
{
	public long createUser(User user);
	
	public void updateLogin(User user);
	public User loadUserById(Long userId);
}
