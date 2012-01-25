package org.nvc.ui.group.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.nvc.ui.group.service.GroupService;
import org.nvc.ui.group.model.Group;
import org.nvc.ui.member.model.Member;
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

@Controller
@RequestMapping("/group")

public class GroupController 
{
	private static final String LIST_VIEW = "group/group_list";
	private static final String DETAIL_VIEW = "group/group_detail";
	private static final String CREATE_VIEW = "group/group_info_input";
	private static final String LIST_AJAX = "group/group_ajax";
	
	private static final String MEMBER_LIST_VIEW = "member/member_list";
	
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

}
