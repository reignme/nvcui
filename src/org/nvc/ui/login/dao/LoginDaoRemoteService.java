package org.nvc.ui.login.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nvc.ui.common.dao.BaseDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class LoginDaoRemoteService extends BaseDao implements UserDetailsService 
{
	private final static String query = "select * from users where username = ?";
	
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException
	{
		//then spring will compare the user input with the following data returned from DAO
		try
		{
			User user = template.queryForObject(query, new Object[]{username}, new UserMapper());
			return user;
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return null;
		}
	}
	
	class UserMapper implements RowMapper<User>
	{
		public User mapRow(ResultSet rs, int arg1) throws SQLException 
		{
			User user = new User(rs.getString("username"), rs.getString("password"), rs.getBoolean("enabled"), true, true, true, getAuthorities(rs.getString("authority")));
			return user;
		}
	}
	
	 private List<GrantedAuthority> getAuthorities(String authority) 
	 {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new GrantedAuthorityImpl(authority));
        
        return authList;
	}
}
