package org.nvc.ui.group.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.nvc.ui.common.dao.BaseDao;

import org.nvc.ui.common.util.JsonUtil;
import org.nvc.ui.group.model.Community;
import org.nvc.ui.group.model.Group;
import org.nvc.ui.member.model.MemberAttendance;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.nvc.ui.common.util.DateUtil;

@Repository("groupDao")
public class GroupDaoImpl extends BaseDao implements GroupDao 
{
	
	private static final String getGroupAll = 
		"SELECT g . * , floor(datediff(now(), max( birth_date )) / 365.25 ) max_age , floor(datediff(now(), min( birth_date )) / 365.25 ) min_age, count(distinct m.member_id) group_count, count(distinct m.family_id) family_count, c.member_id c_id, c.korean_name c_name, c.cell_phone c_cellPhone, c.email c_email, s.member_id  s_id, s.korean_name s_name, s.cell_phone s_cell_phone, s.email s_email "+
		"FROM groups g "+
		"LEFT JOIN "+
		"( "+
			"SELECT member_id, group_id, korean_name, cell_phone, email "+
			"FROM member "+
			"WHERE role_id =2 "+
		") "+
		"c ON c.group_id = g.group_id "+
		"left join "+
		"( "+
			"SELECT member_id, group_id, korean_name, cell_phone, email "+
			"FROM member "+
			"WHERE role_id =3 "+
		") "+
		"s on s.group_id = g.group_id "+
		"LEFT JOIN (select * from member where role_id in (3,4)) m on m.group_id = g.group_id group by g.group_id";
	
	private static final String getGroupById = "select * from groups where group_id = ?";
	
	private static final String createGroup = "insert into groups values (0, ?, ?, ?)";
	private static final String updateGroup = "update groups set name = ?, service_time = ?, room_number = ? where group_id = ?";
	
	private static final String getGroupByServiceTime = "select * from groups where service_time = ?";
	private static final String getGroupByUserName = "select g.* from groups g, users u, member m where u.member_id = m.member_id and m.group_id = g.group_id and u.username = ?";
	
	private static final String getGroupMemberAttendance = "select ml.member_id, ml.korean_name, att.attendDate from (select m.member_id, m.korean_name from member m, groups g where m.group_id = g.group_id and g.group_id = ?) ml left join ( select * from attendance where attendDate = ?) att on att.memberId = ml.member_id";
	private static final String markAttendance = "insert ignore into attendance values (?, ?)";
	private static final String unmarkAttendance = "delete from attendance where memberId = ? and attendDate = ?";
	
	public void makrAttendance(long memberId, String date)
	{
		this.getJdbcTemplate().update(markAttendance, new Object[]{memberId, date});
	}
	
	public void unmarkAttendance(long memberId, String date)
	{
		this.getJdbcTemplate().update(unmarkAttendance, new Object[]{memberId, date});
	}
	
	public List<MemberAttendance> getMemberAttendanceByGroupId(long groupId, String date)
	{
		return this.getJdbcTemplate().query(getGroupMemberAttendance, new Object[]{groupId, date}, new AttendanceMapper());
	}
	
	
	public void createGroup(Group group)
	{
		this.getJdbcTemplate().update(createGroup, new Object[]{group.getName(), group.getServiceTime(), group.getRoomNumber()});
	}
	
	public void updateGroup(Group group)
	{
		this.getJdbcTemplate().update(updateGroup, new Object[]{group.getName(), group.getServiceTime(), group.getRoomNumber(), group.getGroupId()});
	}
	
	public List<Group> getAll()
	{
		return (List<Group>)this.getJdbcTemplate().query(getGroupAll, new Object[]{}, new GroupMapper());
	}
	
	public List<Group> getByServiceTime(int serviceTime)
	{
		return this.getJdbcTemplate().query(getGroupByServiceTime, new Object[]{serviceTime}, new GroupMinMapper());
	}
	
	public Group get(long groupId)
	{
		return this.getJdbcTemplate().queryForObject(getGroupById, new Object[]{groupId}, new GroupMinMapper());
	}
	
	public Group getGroupByMemberName(String memberName)
	{
		return this.getJdbcTemplate().queryForObject(getGroupByUserName, new Object[]{memberName}, new GroupMinMapper());
	}
	
	public void save(Group group)
	{
		String jsonGroup = JsonUtil.generateJson(group);
		
		System.out.println(jsonGroup);
	}

	public Community[] getCommunity() 
	{
		Community[] communityList = new Community[3];
		
		Community c1 = new Community();
		
		c1.setName("믿음마을");
		
		communityList[0] = c1;
		
		Community c2 = new Community();
		//c2.setCommunityHeadId(2);
		c2.setName("사랑마을");
		
		communityList[1] = c2;
		
		Community c3 = new Community();
		//c3.setCommunityHeadId(3);
		c3.setName("소망마을");
		
		communityList[2] = c3;
		
		return communityList;
	}
	
	class AttendanceMapper implements RowMapper<MemberAttendance>
	{
		public MemberAttendance mapRow(ResultSet rs, int arg1) throws SQLException 
		{
			MemberAttendance att = new MemberAttendance();
			
			att.setAttendanceDate(rs.getDate("attendDate"));
			att.setMemberId(rs.getLong("member_id"));
			att.setMemberName(rs.getString("korean_name"));
			
			return att;
		}
	}
	
	class GroupMapper implements RowMapper<Group>
	{
		public Group mapRow(ResultSet rs, int arg1) throws SQLException 
		{
			Group group = new Group();
			group.setGroupId(rs.getInt("group_id"));
			group.setName(rs.getString("name"));
			group.setRoomNumber(rs.getInt("room_number"));
			group.setNumberOfMember(rs.getInt("group_count"));
			group.setNumberOfFamily(rs.getInt("family_count"));
			
			if(group.getNumberOfMember() == 0)
				group.setAgeGroup("N/A");
			else
				group.setAgeGroup(rs.getString("min_age") + " " + rs.getString("max_age"));	
			
			group.setShepherdEmailAddress(rs.getString("s_email"));
			group.setShepherdId(rs.getLong("s_id"));
			group.setShepherdPhoneNumber(rs.getString("s_cell_phone"));
			group.setShepherdName(rs.getString("s_name"));
			
			group.setCommunityHeadId(rs.getLong("c_id"));
			group.setCommunityHeadName(rs.getString("c_name"));
			
			group.setServiceTime(rs.getInt("service_time"));
			
			return group;
		}	
	}
	
	class GroupMinMapper implements RowMapper<Group>
	{
		public Group mapRow(ResultSet rs, int arg1) throws SQLException 
		{
			Group group = new Group();
			group.setGroupId(rs.getInt("group_id"));
			group.setName(rs.getString("name"));
			group.setRoomNumber(rs.getInt("room_number"));
			group.setServiceTime(rs.getInt("service_time"));
			return group;
		}
		
	}
	
}
