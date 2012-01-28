package org.nvc.ui.group.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.nvc.ui.common.util.DateUtil;
import org.nvc.ui.group.service.GroupService;
import org.nvc.ui.group.model.Group;
import org.nvc.ui.member.model.Member;
import org.nvc.ui.member.model.MemberAttendance;
import org.nvc.ui.member.model.MemberRole;
import org.nvc.ui.member.service.MemberService;
import org.nvc.ui.util.SecurityUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/group")

public class GroupController 
{
	private static final String LIST_VIEW = "group/group_list";
	private static final String DETAIL_VIEW = "group/group_detail";
	private static final String CREATE_VIEW = "group/group_info_input";
	private static final String LIST_AJAX = "group/group_ajax";
	private static final String MEMBER_LIST_VIEW = "member/member_list";
	private static final String MEMBER_ATTENDACE = "member/member_attendance";
	
	@Autowired private GroupService groupService;
	@Autowired private MemberService memberService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/list")
	public ModelAndView list()
	{
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Map<String, Object> modelMap = new HashMap<String, Object>();

		modelMap.put("title", "Cell List - " + userName);
		List<Group> groupList = groupService.getAll();
		
		modelMap.put("groupList", groupList);
		ModelAndView mav = new ModelAndView(LIST_VIEW, modelMap);
		return mav;
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/view/{groupId}")
	public ModelAndView view(@PathVariable long groupId)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();

		modelMap.put("title", "Detail");
		Group group = groupService.get(groupId);
		List<Member> members = memberService.getMembersByGroup(groupId);
		
		modelMap.put("group", group);
		modelMap.put("memberList", members);
		ModelAndView mav = new ModelAndView(MEMBER_LIST_VIEW, modelMap);
		return mav;
	}
	
	@RequestMapping("/view/myGroup")
	public ModelAndView viewMyGroup()
	{
		String userName = "";
		try
		{
			userName = SecurityUtil.getUsername();
		}
		catch(Exception e)
		{
			
		}
		
		Map<String, Object> modelMap = new HashMap<String, Object>();

		modelMap.put("title", "Detail");
		Group group = groupService.getGroupByMemberName(userName);
		List<Member> members = memberService.getMembersByGroup(group.getGroupId());
		
		modelMap.put("group", group);
		modelMap.put("memberList", members);
		ModelAndView mav = new ModelAndView(MEMBER_LIST_VIEW, modelMap);
		return mav;
		
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/new")
	public ModelAndView create()
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		Group group = new Group();
		group.setGroupId(0);
		modelMap.put("group", group);
		modelMap.put("communityList", groupService.getCommunity());
		modelMap.put("communityHeadList", memberService.getMembersByRole(MemberRole.COMMUNITY_HEAD));
		modelMap.put("shepherdList", memberService.getMembersByRole(MemberRole.SHEPHERD));
		
		ModelAndView mav = new ModelAndView(CREATE_VIEW, modelMap);
		
		return mav;
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/edit/{groupId}")
	public ModelAndView edit(@PathVariable long groupId)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();

		modelMap.put("title", "Edit");
		Group group = groupService.get(groupId);
		modelMap.put("communityList", groupService.getCommunity());
		//modelMap.put("communityHeadList", memberService.getMembersByRole(MemberRole.COMMUNITY_HEAD));
		modelMap.put("group", group);
		ModelAndView mav = new ModelAndView(CREATE_VIEW, modelMap);
		return mav;
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/save")
	public ModelAndView save(@Valid Group group, BindingResult result)
	{
		groupService.save(group);
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("group", group);
		modelMap.put("communityList", groupService.getCommunity());
		modelMap.put("communityHeadList", memberService.getMembersByRole(MemberRole.COMMUNITY_HEAD));
		modelMap.put("shepherdList", memberService.getMembersByRole(MemberRole.SHEPHERD));
		
		
		ModelAndView mav = new ModelAndView(CREATE_VIEW, modelMap);
		
		return mav;
	}
	
	@RequestMapping("/groupAjax/{serviceTime}")
	public ModelAndView getListByService(@PathVariable int serviceTime)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("groupList", groupService.getByServiceTime(serviceTime));
		
		ModelAndView mav = new ModelAndView(LIST_AJAX, modelMap);
		return mav;
	}
	
	@RequestMapping("/attendance/{groupId}")
	public ModelAndView attendance(@PathVariable long groupId, HttpServletRequest request)
	{
		String userName = "";
		try
		{
			userName = SecurityUtil.getUsername();
		}
		catch(Exception e)
		{
			
		}
		
		Map<String, Object> modelMap = new HashMap<String, Object>();

		modelMap.put("title", "Detail");
		//Group group = groupService.getGroupByMemberName(userName);
		Group group = groupService.get(groupId);
		modelMap.put("group", group);
		
		String requestDate = request.getParameter("date");
		Date targetDate;
		
		if(requestDate != null)
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				targetDate = format.parse(requestDate);
			}
			catch(Exception e)
			{
				System.out.println("Error while converting, date is set to today");
				targetDate = new Date();
			}
		}
		else
			targetDate = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(targetDate);
		
		while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) 
		{
			cal.add(Calendar.DAY_OF_WEEK, -1);
		}
		
		String tmpMonth = DateUtil.formatNumber(cal.get(Calendar.MONTH) + 1);
		String tmpDate = DateUtil.formatNumber(cal.get(Calendar.DATE)); 
		modelMap.put("targetDate", cal.get(Calendar.YEAR)+"-"+tmpMonth+"-"+tmpDate);
		
		List<MemberAttendance> members = groupService.getMemberAttendanceByGroupId((long)group.getGroupId(), cal.get(Calendar.YEAR)+"-"+tmpMonth+"-"+cal.get(Calendar.DATE));
		modelMap.put("members", members);
		
		
		modelMap.put("previousSunday", DateUtil.getPreviousSunday(cal));
		DateUtil.getNextSunday(cal);
		modelMap.put("nextSunday", DateUtil.getNextSunday(cal));
	
		ModelAndView mav = new ModelAndView(MEMBER_ATTENDACE, modelMap);
		return mav;
	}
	
	@RequestMapping("/attendance/save")
	public ModelAndView attendanceSave(HttpServletRequest req, HttpServletResponse resp)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		List<MemberAttendance> members = groupService.getMemberAttendanceByGroupId(Long.parseLong(req.getParameter("groupId")), req.getParameter("date"));
		
		String date = req.getParameter("date");
		String groupId = req.getParameter("groupId");
		
		for(MemberAttendance ma: members)
		{
			String tmpVal = req.getParameter("attendanceDate_"+ma.getMemberId());
			
			
			if(tmpVal.equals("yes"))
			{
				groupService.makrAttendance(ma.getMemberId(), date);
			}
			else
			{
				groupService.unmarkAttendance(ma.getMemberId(), date);
			}
			
		}
		
		/**
		HashMap<String, String> reqMap = (HashMap<String,String>) req.getParameterMap();
		
		for(Map.Entry<String, String> e : reqMap.entrySet())
		    System.out.println(": "+e.getValue());
		*/
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("/group/attendance/"+groupId+"?date="+date, true, true, true));
		
		return mav;
	}
	
	@RequestMapping("/myAttendance")
	public ModelAndView myAttendance(HttpServletRequest req, HttpServletResponse resp)
	{
		String userName = "";
		try
		{
			userName = SecurityUtil.getUsername();
		}
		catch(Exception e)
		{
			
		}
		
		Group group = groupService.getGroupByMemberName(userName);
		
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("/group/attendance/"+group.getGroupId(), true, true, true));
		
		return mav;
	}
	
}
