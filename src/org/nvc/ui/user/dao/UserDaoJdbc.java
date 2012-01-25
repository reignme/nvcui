package org.nvc.ui.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.nvc.ui.common.dao.BaseDao;
import org.nvc.ui.user.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("UserDao")
public class UserDaoJdbc extends BaseDao implements UserDao
{
	private final static String createUser = "insert into users values (0, ?, md5(?), ?, ?)";
	private final static String updateUser = "update users set username=?, password=md5(?), authority=?, enabled=? where user_id = ?";
	private final static String loadUser = "select * from users where user_id = ?";
	
	public synchronized long createUser(User user)
	{
		this.getJdbcTemplate().update(createUser, new Object[]{user.getUsername(), user.getPassword(), user.getAuthority(), 1});
		
		return this.getLastInsertedId("users");
	}
	
	public void updateLogin(User user)
	{
		this.getJdbcTemplate().update(updateUser, new Object[]{user.getUsername(), user.getPassword(), user.getAuthority(), user.getStatus(), user.getMemberId()});
	}
	
	public User loadUserById(Long userId)
	{
		return this.getJdbcTemplate().queryForObject(loadUser, new Object[]{userId}, new UserMapper());
	}
	
	class UserMapper implements RowMapper<User>
	{
		public User mapRow(ResultSet rs, int arg1) throws SQLException 
		{
			User user = new User();
			user.setMemberId(rs.getLong("user_id"));
			user.setUsername(rs.getString("username"));
			user.setAuthority(rs.getString("authority"));
			user.setStatus(rs.getInt("enabled"));
			
			return user;
		}
		
	}
}
