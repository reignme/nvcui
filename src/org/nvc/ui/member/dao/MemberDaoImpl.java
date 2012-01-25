package org.nvc.ui.member.dao;

import org.nvc.ui.member.model.Member;
import org.nvc.ui.member.model.MemberSearch;

import org.nvc.ui.common.model.Address;
import org.nvc.ui.common.util.HttpUtil;
import org.nvc.ui.common.util.JsonUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.nvc.ui.common.dao.BaseDao;

@Repository("memberDao")
public class MemberDaoImpl extends BaseDao implements MemberDao
{	
	private static final String getMemberById = "select m.*, r.name  from member m, user_role r where m.role_id = r.role_id and member_id = ?";
	private static final String getMembersByFamilyId = "select m.*, r.name  from member m, user_role r where m.role_id = r.role_id and family_id = ?";
	private static final String getMemberByGroupId = "select m.*, r.name  from member m, user_role r where m.role_id = r.role_id and group_id = ? order by family_id";
	private static final String getMemberByRoleId = "select m.*, r.name  from member m, user_role r where m.role_id = r.role_id and m.role_id = ?";
	private static final String addMember = "insert into member values (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String updateFamilyId = "update member set family_id = ? where member_id = ?";
	private static final String createFamilyId = "insert into family values (?, ?)";
	private static final String updateMember = "update member set birth_date=?, email=?, home_phone=?, group_id = ?, work_phone=?, cell_phone=?, gender=?, last_name=?, first_name=?, korean_name=?, street=?, city=?, state=?, zipcode=?, country=?, status=?, baptised=?, religiousConviction=?, role_id=?, family_role=? where member_id = ?";
	private static final String searchMemberByKoreanName = "select m.*, r.name  from member m, user_role r where m.role_id = r.role_id and korean_name like ?";
	private static final String searchMemberByStatus =  "select m.*, r.name  from member m, user_role r where m.role_id = r.role_id and status != 1";
	
	static
	{
		Properties entries;
		try 
		{
			entries = org.springframework.core.io.support.PropertiesLoaderUtils.loadAllProperties("nvcui.properties");
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public List<Member> searchMembers(MemberSearch memberSearch)
	{
		if(memberSearch.getSearchField().equals("name"))
			return (List<Member>)this.getJdbcTemplate().query(searchMemberByKoreanName, new Object[]{"%"+memberSearch.getName()+"%"}, new MemberMapper());
		else
			return (List<Member>)this.getJdbcTemplate().query(searchMemberByStatus, new Object[]{}, new MemberMapper());
	}
	
	public Member getMember(int memberId)
	{
		return this.getJdbcTemplate().queryForObject(getMemberById, new Object[]{memberId}, new MemberMapper());
	}
	
	public Member getMember(String name)
	{
		return null;
	}
	
	public synchronized void addMember(Member member)
	{	
		this.getJdbcTemplate().update(addMember, new Object[]{member.getGroupId(), member.getFamilyId(), member.getLastName(), member.getFirstName(), member.getKoreanName(), member.getHomePhone(), member.getCellPhone(), member.getWorkPhone(), member.getEmailAddress(), member.getRoleId(), member.getStatus(), member.getBaptised(), member.getReligiousConviction(), member.getBirthDate(), member.getGender(), member.getAddress().getStreet(), member.getAddress().getCity(), member.getAddress().getState(), member.getAddress().getZipCode(), member.getAddress().getCountry(), member.getFamilyRole()});
		
		member.setMemberId(this.getLastInsertedId("member"));
		
		if(member.getFamilyId() == 0)
		{
			getJdbcTemplate().update(createFamilyId, new Object[]{member.getFamilyId(), member.getMemberId()});
			member.setFamilyId(this.getLastInsertedId("family"));
			this.getJdbcTemplate().update(updateFamilyId, new Object[]{member.getFamilyId(), member.getMemberId()});
		}
		
	}
	
	public void editMember(Member member)
	{
		getJdbcTemplate().update(updateMember, new Object[]{member.getBirthDate(), member.getEmailAddress(), member.getHomePhone(), member.getGroupId(), member.getWorkPhone(), member.getCellPhone(), member.getGender(), member.getLastName(), member.getFirstName(), member.getKoreanName(), member.getAddress().getStreet(), member.getAddress().getCity(), member.getAddress().getState(), member.getAddress().getZipCode(), member.getAddress().getCountry(), member.getStatus(), member.getBaptised(), member.getReligiousConviction(), member.getRoleId(), member.getFamilyRole(), member.getMemberId()});
	}
	
	public List<Member> getMembersByGroup(long groupId)
	{
		return this.getJdbcTemplate().query(getMemberByGroupId, new Object[]{groupId}, new MemberMapper());
	}
	
	public List<Member> getMembersByFamily(long familyId)
	{
		List<Member> members = (List<Member>)this.getJdbcTemplate().query(getMembersByFamilyId, new Object[]{familyId}, new MemberMapper());
		
		return members;
	}
	
	public List<Member> getMembersByRole(long roleId)
	{
		return (List<Member>)this.getJdbcTemplate().query(getMemberByRoleId, new Object[]{roleId}, new MemberMapper());
	}
	
	class MemberMapper implements RowMapper<Member>
	{
		public Member mapRow(ResultSet rs, int arg1) throws SQLException 
		{
			Member member = new Member();
			member.setMemberId(rs.getLong("member_id"));
			member.setBirthDate(rs.getString("birth_date"));
			member.setFamilyId(rs.getLong("family_id"));
			member.setEmailAddress(rs.getString("email"));
			member.setHomePhone(rs.getString("home_phone"));
			member.setGroupId(rs.getLong("group_id"));
			member.setWorkPhone(rs.getString("work_phone"));
			member.setCellPhone(rs.getString("cell_phone"));
			member.setGender(rs.getInt("gender"));
			member.setLastName(rs.getString("last_name"));
			member.setFirstName(rs.getString("first_name"));
			member.setKoreanName(rs.getString("korean_name"));
			member.setRoleId(rs.getInt("role_id"));
			member.setReligiousConviction(rs.getInt("religiousConviction"));
			member.setStatus(rs.getInt("status"));
			member.setBaptised(rs.getInt("baptised"));
			member.setFamilyRole(rs.getInt("family_role"));
			
			Address addr = new Address();
			
			addr.setStreet(rs.getString("street"));
			addr.setCity(rs.getString("city"));
			addr.setState(rs.getString("state"));
			addr.setZipCode(rs.getString("zipcode"));
			addr.setCountry(rs.getString("country"));
			
			member.setAddress(addr);
			
			return member;
		}
		
	}
}
