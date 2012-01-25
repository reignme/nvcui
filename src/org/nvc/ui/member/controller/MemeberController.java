package org.nvc.ui.member.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import org.nvc.ui.common.model.Address;
import org.nvc.ui.group.model.Group;
import org.nvc.ui.group.service.GroupService;
import org.nvc.ui.member.dao.MemberDao;
import org.nvc.ui.member.model.Member;
import org.nvc.ui.member.model.MemberSearch;
import org.nvc.ui.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemeberController 
{
	private static final String SEARCH_VIEW = "member/member_search";
	private static final String CREATE_VIEW = "member/member_info_input";
	private static final String MEMBER_LIST_VIEW = "member/member_list";
	
	@Autowired private MemberService memberService;
	@Autowired private GroupService groupService;
	
	@RequestMapping("/search")
	public ModelAndView initSearch()
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		MemberSearch memberSearch = new MemberSearch();
		modelMap.put("memberSearch", memberSearch);
		modelMap.put("title", "Member Search");	
		ModelAndView mav = new ModelAndView(SEARCH_VIEW, modelMap);
		return mav;
	}
	
	@RequestMapping("/search/process")
	public ModelAndView doSearch(MemberSearch memberSearch, BindingResult result)
	{
		List<Member> members = memberService.searchMembers(memberSearch);
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		//keep the inputs
		modelMap.put("memberSearch", memberSearch);
		modelMap.put("title", "Member Search Result");
		modelMap.put("memberList", members);
		
		ModelAndView mav = new ModelAndView(MEMBER_LIST_VIEW, modelMap);
		return mav;
	}
	
	@RequestMapping("/new")
	public ModelAndView addNewUser(HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		Calendar cal=Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		List<Integer> years = new ArrayList<Integer>();
		
		for(int i = year; i < 100; i--)
		{
			years.add(new Integer(i));
		}
		
		Member member = new Member();
		modelMap.put("serviceTime", 1);
		
		if(request.getParameter("familyId") != null)
		{
			member.setFamilyId(Integer.parseInt(request.getParameter("familyId")));
			Address addr = new Address();
			
			addr.setZipCode(request.getParameter("zipcode"));
			
			member.setHomePhone(request.getParameter("hp"));
			
			addr.setCity(request.getParameter("city"));
			addr.setStreet(request.getParameter("street"));
			
			member.setAddress(addr);
			member.setGroupId(Long.parseLong(request.getParameter("group")));
			
			modelMap.put("serviceTime", request.getParameter("serviceTime"));
		}
		
		modelMap.put("member", member);
		modelMap.put("type", "new");
		modelMap.put("year_list", years);
		modelMap.put("title", "Create New Member");	
		modelMap.put("output", "");
		modelMap.put("roleList", memberService.getMemberRole());
		modelMap.put("groupList", groupService.getAll());
		
		ModelAndView mav = new ModelAndView(CREATE_VIEW, modelMap);
		return mav;
	}
	
	@RequestMapping("/edit/{memberId}")
	public ModelAndView edit(@PathVariable int memberId, HttpServletRequest request)
	{
		Member member = memberService.getMember(memberId);
		Map<String, Object> modelMap = new HashMap<String, Object>();

		Group group = groupService.get(member.getGroupId());
		StringBuilder sb = new StringBuilder();
		
		sb.append("familyId="+member.getFamilyId());
		sb.append("&hp="+member.getHomePhone());
		sb.append("&street="+member.getAddress().getStreet());
		sb.append("&city="+member.getAddress().getCity());
		sb.append("&state="+member.getAddress().getState());
		sb.append("&zipcode="+member.getAddress().getZipCode());
		sb.append("&group="+group.getGroupId());
		sb.append("&serviceTime="+group.getServiceTime());
		
		modelMap.put("member", member);
		modelMap.put("serviceTime", group.getServiceTime());
		modelMap.put("title", "Edit Member");
		modelMap.put("type", "edit");
		modelMap.put("roleList", memberService.getMemberRole());
		
		modelMap.put("groupList", groupService.getByServiceTime(group.getServiceTime()));
		modelMap.put("familyAttribute", sb.toString());
		
		
		ModelAndView mav = new ModelAndView(CREATE_VIEW, modelMap);
		return mav;
	}
	
	
	@RequestMapping("/save")
	public ModelAndView addNewUser(@Valid Member member, BindingResult result)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		Group group = groupService.get(member.getGroupId());
		
		modelMap.put("member", member);
		modelMap.put("title", "Member Saved");
		modelMap.put("roleList", memberService.getMemberRole());
		modelMap.put("groupList", groupService.getAll());
		
		modelMap.put("serviceTime", group.getServiceTime());
		ModelAndView mav = new ModelAndView(CREATE_VIEW, modelMap);
		
		try
		{
			if(member.getMemberId() == 0)
			{
				memberService.addMember(member);
			}
			else
			{
				memberService.editMember(member);
			}
		}
		catch(Exception e)
		{
			modelMap.put("error", "Error has occured.  Please try again");
		}
		
		return mav;
		//return "redirect:/member/edit/"+ member.getMemberId();
	}
	
	@RequestMapping("/family/{familyId}")
	public ModelAndView viewByFamily(@PathVariable long familyId)
	{
		List<Member> members = memberService.getMembersByFamily(familyId);
		Map<String, Object> modelMap = new HashMap<String, Object>();

		modelMap.put("memberList", members);
		modelMap.put("title", "Edit Member");
		modelMap.put("type", "edit");
		modelMap.put("output", "");
		ModelAndView mav = new ModelAndView(MEMBER_LIST_VIEW, modelMap);
		return mav;
	}
	
}
